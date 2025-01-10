package app.TFGWordle.controller;

import app.TFGWordle.dto.UserState;
import app.TFGWordle.dto.WordleState;
import app.TFGWordle.dto.WordleStateLog;
import app.TFGWordle.model.*;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private final WordleService wordleService;
    private final ContestStateService contestStateService;
    private final DictionaryService dictionaryService;
    private final ObjectMapper objectMapper;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    public ContestController(ContestService contestService, CompetitionService competitionService, UserService userService, WordleService wordleService, ContestStateService contestStateService, DictionaryService dictionaryService, ObjectMapper objectMapper) {
        this.contestService = contestService;
        this.competitionService = competitionService;
        this.userService = userService;
        this.wordleService = wordleService;
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
        contest.setNumTries(3);
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
    public ResponseEntity<?> deleteContest(@PathVariable("contestName") String contestName) {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);
        Contest contest = contestService.getByName(contestName);
        if (contestStateService.existsByContest(contest.getId())) {
            List<ContestState> contestState = contestStateService.getAllByContest(contest.getId());
            List<ContestStateLog> contestStateLogs = contestStateService.getLogsByContestId(contest.getId());
            for (ContestState cs : contestState) {
                contestStateService.deleteById(cs.getId());
            }
            for (ContestStateLog cs : contestStateLogs) {
                contestStateService.deleteByLogId(cs.getId());
            }
        }
        contestService.deleteContest(contest.getId());
        return ResponseEntity.ok(Map.of("message", "Concurso eliminado"));
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
            return new ResponseEntity<>("Estado ya creado", HttpStatus.CONFLICT);

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

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/createContestLog/{contestName}/{userName}")
    public ResponseEntity<?> createContestLog(@PathVariable String contestName, @PathVariable String userName, @RequestBody WordleStateLog wordleStateLog) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getByName(contestName);

        ContestStateLog contestStateLog = new ContestStateLog(contest, user);
        try {
            JsonNode jsonNode = objectMapper.valueToTree(wordleStateLog);
            contestStateLog.setState(jsonNode);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar los datos" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        contestStateService.saveLog(contestStateLog);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT')")
    @GetMapping("/getAllContestState/{contestName}")
    public ResponseEntity<List<UserState>> getAllContestState(@PathVariable String contestName) throws JsonProcessingException {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);

        List<ContestState> contestStates = contestStateService.getAllByContest(contest.getId());

        List<UserState> toReturn = new ArrayList<>();
        for (ContestState contestState : contestStates) {
            UserState toAdd = new UserState(contestState.getUser().getUsername(), contestState.getUser().getEmail(), contestState.getState());
            toReturn.add(toAdd);
        }

        return ResponseEntity.ok(toReturn);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/getAllContestStateLogs/{contestName}")
    public ResponseEntity<List<WordleStateLog>> getAllContestStateLogs(@PathVariable String contestName) throws JsonProcessingException {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);

        List<String> contestStateLogs = contestStateService.getAllLogsByContest(contest.getId());

        if (contestStateLogs.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<WordleStateLog> toReturn = new ArrayList<>();
        for (String csLog : contestStateLogs) {
            WordleStateLog toAdd = contestStateService.getStateLog(csLog);
            toReturn.add(toAdd);
        }

        return ResponseEntity.ok(toReturn);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getAllUserContestStateLogs/{contestName}/{userName}")
    public ResponseEntity<List<WordleStateLog>> getAllUserContestStateLogs(@PathVariable String contestName, @PathVariable String userName) throws JsonProcessingException {
        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);
        User user = userService.getByUserName(userName).get();

        List<String> contestStatesLogs = contestStateService.getAllLogsByContestAndUser(contest.getId(), user.getId());

        if (contestStatesLogs.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<WordleStateLog> toReturn = new ArrayList<>();
        for (String csLog : contestStatesLogs) {
            WordleStateLog toAdd = contestStateService.getStateLog(csLog);
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

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT')")
    @GetMapping("/getWordles/{contestName}")
    public ResponseEntity<List<Wordle>> getWordles(@PathVariable String contestName) {
        if(!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);
        return ResponseEntity.ok(wordleService.findByContestId(contest.getId()));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/getLogsInExcel/{contestName}")
    public ResponseEntity<Resource> getLogsInExcel(@PathVariable String contestName) {
        if(!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getByName(contestName);

        List<String> logs = contestStateService.getAllLogsByContest(contest.getId());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeDataToExcel(logs, out);
        ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=logs.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    private void writeDataToExcel(List<String> logs, ByteArrayOutputStream out) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Logs");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"Alumno", "Correo", "Tiempo", "Palabra adivinar", "Posición palabra", "Intento palabra", "Intento", "Estado"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                CellStyle cellStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);
            }

            int rowNum = 1;
            for (String log : logs) {
                WordleStateLog wsl = contestStateService.getStateLog(log);
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(wsl.getUserName());
                row.createCell(1).setCellValue(wsl.getEmail());
                row.createCell(2).setCellValue(wsl.getDateLog());
                row.createCell(3).setCellValue(wsl.getWordleToGuess());
                row.createCell(4).setCellValue(wsl.getWordlePosition());
                row.createCell(5).setCellValue(wsl.getWordleInserted());
                row.createCell(6).setCellValue(wsl.getNumTry());
                row.createCell(7).setCellValue(wsl.isState() ? "Correcta" : "Incorrecta");
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}