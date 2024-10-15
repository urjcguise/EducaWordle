package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/competitions")
@CrossOrigin
public class CompetitionController {

    private final CompetitionService competitionService;
    private final UserService userService;

    public CompetitionController(CompetitionService competitionService, UserService userService) {
        this.competitionService = competitionService;
        this.userService = userService;
    }

    @PostMapping("/newCompetition")
    public ResponseEntity<?> createCompetition(@RequestBody Competition competition) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User professor = userService.getByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        competition.setProfessor(professor);
        competitionService.save(competition, professor);

        return new ResponseEntity<>(Map.of("message", "Competición creada exitosamente"), HttpStatus.CREATED);
    }

    @GetMapping("/getCompetitions")
    public ResponseEntity<List<Competition>> getCompetitions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User professor = userService.getByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Competition> competitions = competitionService.getCompetitionsByProfesor(professor);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

}
