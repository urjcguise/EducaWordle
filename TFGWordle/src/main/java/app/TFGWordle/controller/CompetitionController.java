package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/competitions")
@CrossOrigin
public class CompetitionController {

    private final CompetitionService competitionService;
    private final UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    public CompetitionController(CompetitionService competitionService, UserService userService) {
        this.competitionService = competitionService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/newCompetition")
    public ResponseEntity<?> createCompetition(@RequestBody Competition competition) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User professor = userService.getByUserName(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));

        competition.setProfessor(professor);
        competitionService.save(competition, professor);

        return new ResponseEntity<>(Map.of("message", "Competición creada exitosamente"), HttpStatus.CREATED);
    }

    @GetMapping("/getCompetitions")
    public ResponseEntity<List<Competition>> getCompetitions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User professor = userService.getByUserName(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));

        List<Competition> competitions = competitionService.getCompetitionsByProfesor(professor);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @DeleteMapping("/deleteCompetition/{id}")
    public ResponseEntity<?> deleteCompetition(@PathVariable("id") Long id) {
        if (!competitionService.existsCompetition(id))
            return new ResponseEntity<>("Competición no encontrada", HttpStatus.NOT_FOUND);
        competitionService.deleteCompetition(id);
        return ResponseEntity.ok(Map.of("message", "Competición eliminada"));
    }

}
