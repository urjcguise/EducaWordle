package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.ParticipationService;
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
    private final ContestService contestService;
    private final UserService userService;
    private final ParticipationService participationService;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    public CompetitionController(CompetitionService competitionService, ContestService contestService, UserService userService, ParticipationService participationService) {
        this.competitionService = competitionService;
        this.contestService = contestService;
        this.userService = userService;
        this.participationService = participationService;
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/newCompetition")
    public ResponseEntity<?> createCompetition(@RequestBody Competition competition) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (competitionService.existsCompetitionByName(competition.getCompetitionName()))
            return new ResponseEntity<>("Nombre ya utilizado", HttpStatus.CONFLICT);

        User professor = userService.getByUserName(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));

        competition.setProfessor(professor);
        competitionService.save(competition, professor);

        return new ResponseEntity<>(Map.of("message", "Competición creada exitosamente"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getCompetitions/{professorName}")
    public ResponseEntity<List<Competition>> getCompetitions(@PathVariable String professorName) {
        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profesor no encontrado"));

        List<Competition> competitions = competitionService.getCompetitionsByProfesor(professor);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/getCompetitionById/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @DeleteMapping("/deleteCompetition/{id}")
    public ResponseEntity<?> deleteCompetition(@PathVariable("id") Long id) {
        if (!competitionService.existsCompetition(id))
            return new ResponseEntity<>("Competición no encontrada", HttpStatus.NOT_FOUND);
        List<Contest> contestInCompetition = contestService.getContestsByCompetition(competitionService.getCompetitionById(id));
        for (Contest contest : contestInCompetition) {
            contestService.deleteContest(contest.getId());
        }
        competitionService.deleteCompetition(id);
        return ResponseEntity.ok(Map.of("message", "Competición eliminada"));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/getStudents/{competitionId}")
    public ResponseEntity<List<User>> getStudents(@PathVariable Long competitionId) {
        if (competitionService.existsCompetition(competitionId)) {
            return ResponseEntity.ok(participationService.findStudentsByCompetition(competitionId));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/linkStudentToCompetition/{competitionId}/{userId}")
    public ResponseEntity<?> linkStudent(@PathVariable Long competitionId, @PathVariable Long userId ) {
        if (competitionService.existsCompetition(competitionId) && userService.existsById(userId)) {
            Competition competition = competitionService.getCompetitionById(competitionId);
            User user = userService.getById(userId).get();
            Participation newParticipation = new Participation(user, competition);

            competition.getParticipations().add(newParticipation);
            user.getParticipations().add(newParticipation);

            participationService.save(newParticipation);
            return new ResponseEntity<>(Map.of("message", "Alumno asignado correctamente"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Competición o usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
