package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ContestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contests")
@CrossOrigin
public class ContestController {

    private final ContestService contestService;
    private final CompetitionService competitionService;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    public ContestController(ContestService contestService, CompetitionService competitionService) {
        this.contestService = contestService;
        this.competitionService = competitionService;
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/newContest/{competitionId}")
    public ResponseEntity<Contest> createContest(@RequestBody Contest contest, @PathVariable Long competitionId) {
        Competition competition = competitionService.getCompetitionById(competitionId);
        contest.setCompetition(competition);
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.save(contest));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/{competitionName}/contests")
    public ResponseEntity<List<Contest>> getContestsByCompetition(@PathVariable String competitionName) {
        Competition competition = competitionService.getCompetitionByName(competitionName);
        List<Contest> contests = contestService.getContestsByCompetition(competition);
        return ResponseEntity.ok(contests);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @DeleteMapping("/deleteContest/{contestName}")
    public ResponseEntity<?> deleteCompetition(@PathVariable("contestName") String contestName) {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);
        Contest contest = contestService.getByName(contestName);
        contestService.deleteCompetition(contest.getId());
        return ResponseEntity.ok(Map.of("message", "Competición eliminada"));
    }
}
