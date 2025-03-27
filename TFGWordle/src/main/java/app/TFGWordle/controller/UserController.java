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
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;
    private final ParticipationService participationService;


    public UserController(PasswordEncoder passwordEncoder, UserService userService, ParticipationService participationService) {
        this.passwordEncoder = passwordEncoder;
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
        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateUser/{oldUserName}")
    public ResponseEntity<?> updateUser(@PathVariable String oldUserName, @RequestBody NewUser uploadUser) {
        User userToUpdate = userService.getByUserName(oldUserName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(user.getEmail(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUserByName/{userName}")
    public ResponseEntity<?> deleteUserByName(@PathVariable String userName) {
        User userToDelete = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        userService.deleteUser(userToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getCompetitions/{userName}")
    public ResponseEntity<List<Competition>> getCompetitions(@PathVariable String userName) {
        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Competition> toReturn = participationService.findParticipationsByStudent(user.getId())
                .stream()
                .map(Participation::getCompetition)
                .collect(Collectors.toList());

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }
}
