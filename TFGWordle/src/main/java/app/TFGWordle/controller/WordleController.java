package app.TFGWordle.controller;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Wordle;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.WordleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/wordle")
@CrossOrigin
public class WordleController {

    private final WordleService wordleService;
    private final ContestService contestService;

    public WordleController(WordleService wordleService, ContestService contestService) {
        this.wordleService = wordleService;
        this.contestService = contestService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newWordles/{contestName}")
    public ResponseEntity<List<Wordle>> createWordles(@RequestBody List<String> wordles, @PathVariable String contestName) {

        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Contest contest = contestService.getByName(contestName);
        List<Wordle> toSave = new ArrayList<>();
        for (String wordle : wordles) {
            Wordle newWordle = new Wordle(wordle, contest);
            toSave.add(newWordle);
        }
        contest.updateWordles(toSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(wordleService.saveAll(toSave));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/deleteWordles/{contestName}")
    public ResponseEntity<?> deleteWordles(@PathVariable String contestName) {

        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Contest contest = contestService.getByName(contestName);
        wordleService.deleteByContestId(contest.getId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT') || hasRole('ADMIN')")
    @GetMapping("/getWordles/{contestName}")
    public ResponseEntity<List<Wordle>> getWordles(@PathVariable String contestName) {

        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Contest contest = contestService.getByName(contestName);

        return ResponseEntity.status(HttpStatus.OK).body(wordleService.findByContestId(contest.getId()));
    }
}
