package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.ParticipationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final ParticipationService participationService;

    public UserController(UserService userService, ParticipationService participationService) {
        this.userService = userService;
        this.participationService = participationService;
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
