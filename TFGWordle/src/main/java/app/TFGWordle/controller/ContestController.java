package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ContestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contests")
@CrossOrigin
public class ContestController {

    private final ContestService contestService;
    private final CompetitionService competitionService;
    private final UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    public ContestController(ContestService contestService, CompetitionService competitionService, UserService userService) {
        this.contestService = contestService;
        this.competitionService = competitionService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/newContest")
    public ResponseEntity<Contest> createContest(@RequestBody Contest contest) {
        logger.info("Creating a new contest");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        userService.getByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.save(contest));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/{competitionId}/contest")
    public ResponseEntity<List<Contest>> getContestsByCompetition(@PathVariable Long competitionId) {
        Competition competition = competitionService.getCompetitionById(competitionId);
        List<Contest> contests = contestService.getContestsByCompetition(competition);
        return ResponseEntity.ok(contests);
    }
}
