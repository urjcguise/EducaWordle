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
        if (contestService.existsContest(contest.getContestName()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Competition competition = competitionService.getCompetitionById(competitionId);
        contest.setCompetition(competition);
        contest.setUseDictionary(false);
        contest.setUseExternalFile(false);
        contest.setFileRoute("");
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.save(contest));
    }

    @PreAuthorize("hasRole('PROFESSOR')|| hasRole('STUDENT')")
    @GetMapping("/{competitionName}/contests")
    public ResponseEntity<List<Contest>> getContestsByCompetition(@PathVariable String competitionName) {
        if (!competitionService.existsCompetitionByName(competitionName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
        contestService.deleteContest(contest.getId());
        return ResponseEntity.ok(Map.of("message", "Concurso eliminada"));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/editContest/{contestName}")
    public ResponseEntity<?> updateContest(@PathVariable String contestName, @RequestBody Contest contest) {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        Contest oldContest = contestService.getByName(contestName);
        contest.setCompetition(oldContest.getCompetition());

        return ResponseEntity.ok(contestService.save(contest));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT')")
    @GetMapping("/{contestName}/contest")
    public ResponseEntity<Contest> getContestByName(@PathVariable String contestName) {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);
        return ResponseEntity.ok(contest);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/copyContest/{oldContestName}")
    public ResponseEntity<Contest> copyContest(@RequestBody Contest newContest, @PathVariable String oldContestName) {
        if (contestService.existsContest(newContest.getContestName()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        newContest.setCompetition(contestService.getByName(oldContestName).getCompetition());
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.save(newContest));
    }
}
