package app.TFGWordle.controller;

import app.TFGWordle.dto.UserState;
import app.TFGWordle.dto.WordleState;
import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.ContestState;
import app.TFGWordle.model.DictionaryExternal;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.ContestStateService;
import app.TFGWordle.service.DictionaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contests")
@CrossOrigin
public class ContestController {

    private final ContestService contestService;
    private final CompetitionService competitionService;
    private final UserService userService;
    private final ContestStateService contestStateService;
    private final DictionaryService dictionaryService;
    private final ObjectMapper objectMapper;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    public ContestController(ContestService contestService, CompetitionService competitionService, UserService userService, ContestStateService contestStateService, DictionaryService dictionaryService, ObjectMapper objectMapper) {
        this.contestService = contestService;
        this.competitionService = competitionService;
        this.userService = userService;
        this.contestStateService = contestStateService;
        this.dictionaryService = dictionaryService;
        this.objectMapper = objectMapper;
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
        if (contestStateService.existsByContest(contest.getId())) {
            List<ContestState> contestState = contestStateService.getByContest(contest.getId());
            for (ContestState cs : contestState) {
                contestStateService.deleteById(cs.getId());
            }
        }
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

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/newContestState/{contestName}/{userName}")
    public ResponseEntity<?> createContestState(@PathVariable String contestName, @PathVariable String userName, @RequestBody WordleState wordleState) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getByName(contestName);

        if (contestStateService.existsState(contest.getId(), user.getId()))
            return new ResponseEntity<>("Concurso ya existente", HttpStatus.CONFLICT);

        ContestState newContestState = new ContestState(contest, user);

        try {
            JsonNode jsonNode = objectMapper.valueToTree(wordleState);
            newContestState.setState(jsonNode);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar los datos" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(contestStateService.save(newContestState));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getContestState/{contestName}/{userName}")
    public ResponseEntity<WordleState> getContestState(@PathVariable String contestName, @PathVariable String userName) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getByName(contestName);

        if (!contestStateService.existsState(contest.getId(), user.getId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(contestStateService.getState(contest.getId(), user.getId()));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/updateContestState/{contestName}/{userName}")
    public ResponseEntity<?> updateContestState(@PathVariable String contestName, @PathVariable String userName, @RequestBody WordleState wordleState) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getByName(contestName);
        ContestState contestState = contestStateService.getContestState(contest.getId(), user.getId());
        try {
            JsonNode jsonNode = objectMapper.valueToTree(wordleState);
            contestState.setState(jsonNode);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar los datos" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(contestStateService.save(contestState));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT')")
    @GetMapping("/getUserAndContestState/{contestName}")
    public ResponseEntity<List<UserState>> getUserAndContestState(@PathVariable String contestName) throws JsonProcessingException {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);

        List<ContestState> contestStates = contestStateService.getByContest(contest.getId());

        List<UserState> toReturn = new ArrayList<>();
        for (ContestState contestState : contestStates) {
            UserState toAdd = new UserState(contestState.getUser().getUsername(), contestState.getState());
            toReturn.add(toAdd);
        }

        return ResponseEntity.ok(toReturn);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/existsInDictionary/{word}")
    public ResponseEntity<Boolean> existsInDictionary(@PathVariable String word) {
        return ResponseEntity.ok(dictionaryService.existsInGlobalDictionary(word));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/existsInExternalDictionary/{contestName}/{wordle}")
    public ResponseEntity<Boolean> existsInExternalDictionary(@PathVariable String contestName, @PathVariable String wordle) {
        if(!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);
        return ResponseEntity.ok(dictionaryService.existsInExternalDictionary(wordle, contest.getId()));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/saveExternalDictionary/{contestName}")
    public ResponseEntity<List<DictionaryExternal>> saveExternalDictionary(@PathVariable String contestName, @RequestBody List<String> words) {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Contest contestToLink = contestService.getByName(contestName);

        List<DictionaryExternal> toSave = new ArrayList<>();
        for (String word : words) {
            DictionaryExternal wordToSave = new DictionaryExternal(word);
            wordToSave.setContest(contestToLink);
            toSave.add(wordToSave);
        }

        return ResponseEntity.ok(dictionaryService.saveExternal(toSave));
    }
}