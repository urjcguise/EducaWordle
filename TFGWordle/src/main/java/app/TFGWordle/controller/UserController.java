package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.dto.NewUser;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserService userService;
    private final ParticipationService participationService;


    public UserController(UserService userService, ParticipationService participationService) {
        this.userService = userService;
        this.participationService = participationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllProfessors")
    public ResponseEntity<List<User>> getAllProfessors() {
        List<User> toReturn = userService.getAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getRolName().equals(RolName.ROLE_PROFESSOR)))
                .collect(Collectors.toList());
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<User>> getAllStudents() {
        List<User> toReturn = userService.getAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getRolName().equals(RolName.ROLE_STUDENT)))
                .collect(Collectors.toList());
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUserData/{userName}")
    public ResponseEntity<User> getUserData(@PathVariable String userName) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userService.getByUserName(userName).get(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateUser/{oldUserName}")
    public ResponseEntity<?> updateUser(@PathVariable String oldUserName, @RequestBody NewUser uploadUser) {
        if (!userService.existsByUserName(oldUserName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);

        User userToUpdate = userService.getByUserName(oldUserName).get();
        userToUpdate.setUsername(uploadUser.getName());
        userToUpdate.setEmail(uploadUser.getEmail());
        if (!uploadUser.getPassword().isEmpty())
            userToUpdate.setPassword(passwordEncoder.encode(uploadUser.getPassword()));
        userService.save(userToUpdate);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getUserEmail/{userName}")
    public ResponseEntity<String> getUserEmail(@PathVariable String userName) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userService.getByUserName(userName).get().getEmail(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteUserByName/{userName}")
    public ResponseEntity<?> deleteUserByName(@PathVariable String userName) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        User userToDelete = userService.getByUserName(userName).get();
        userService.deleteUser(userToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getCompetitions/{userName}")
    public ResponseEntity<List<Competition>> getCompetition(@PathVariable String userName) {
        if (userService.existsByUserName(userName)) {
            User user = userService.getByUserName(userName).get();
            List<Competition> toReturn = new ArrayList<>();
            for (Participation participation : participationService.findCompetitionsByStudent(user.getId())) {
                toReturn.add(participation.getCompetition());
            }
            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
