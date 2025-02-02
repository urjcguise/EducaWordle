package app.TFGWordle.controller;

import app.TFGWordle.dto.UserState;
import app.TFGWordle.dto.WordleState;
import app.TFGWordle.dto.WordleStateLog;
import app.TFGWordle.model.*;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
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

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newContest/{competitionId}")
    public ResponseEntity<Contest> createContest(@RequestBody Contest contest, @PathVariable Long competitionId) {
        if (!competitionService.existsCompetition(competitionId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Competition competition = competitionService.getCompetitionById(competitionId);
        if (competition.getContests().contains(contest))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        competition.getContests().add(contest);
        contest.setCompetition(competition);
        contest.setNumTries(6);
        contest.setUseDictionary(false);
        contest.setUseExternalFile(false);

        competitionService.save(competition);
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.save(contest));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT') || hasRole('ADMIN')")
    @GetMapping("/{competitionName}/contests")
    public ResponseEntity<List<Contest>> getContestsByCompetition(@PathVariable String competitionName) {
        if (!competitionService.existsCompetitionByName(competitionName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Competition competition = competitionService.getCompetitionByName(competitionName);
        List<Contest> contests = contestService.getContestsByCompetition(competition);
        return ResponseEntity.ok(contests);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @DeleteMapping("/deleteContest/{contestId}")
    public ResponseEntity<?> deleteContest(@PathVariable Long contestId) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

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

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editContest/{contestId}")
    public ResponseEntity<?> updateContest(@PathVariable Long contestId, @RequestBody Contest contest) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        Contest oldContest = contestService.getById(contestId);
        contest.setCompetition(oldContest.getCompetition());
        contest.setWordles(oldContest.getWordles());

        return ResponseEntity.ok(contestService.save(contest));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT') || hasRole('ADMIN')")
    @GetMapping("/{contestId}/contest")
    public ResponseEntity<Contest> getContestById(@PathVariable Long contestId) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(contestService.getById(contestId));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/copyContest/{oldContestId}")
    public ResponseEntity<Contest> copyContest(@RequestBody Contest newContest, @PathVariable Long oldContestId) {
        if (contestService.existsByName(newContest.getContestName()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        newContest.setCompetition(contestService.getById(oldContestId).getCompetition());
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.save(newContest));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/newContestState/{contestId}/{userName}")
    public ResponseEntity<?> createContestState(@PathVariable Long contestId, @PathVariable String userName, @RequestBody WordleState wordleState) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getById(contestId);

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
    @GetMapping("/getContestState/{contestId}/{userName}")
    public ResponseEntity<WordleState> getContestState(@PathVariable Long contestId, @PathVariable String userName) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getById(contestId);

        if (!contestStateService.existsState(contest.getId(), user.getId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(contestStateService.getState(contest.getId(), user.getId()));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/updateContestState/{contestId}/{userName}")
    public ResponseEntity<?> updateContestState(@PathVariable Long contestId, @PathVariable String userName, @RequestBody WordleState wordleState) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getById(contestId);
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
    @PostMapping("/createContestLog/{contestId}/{userName}")
    public ResponseEntity<?> createContestLog(@PathVariable Long contestId, @PathVariable String userName, @RequestBody WordleStateLog wordleStateLog) {
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName).get();
        Contest contest = contestService.getById(contestId);

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

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT') || hasRole('ADMIN')")
    @GetMapping("/getAllContestState/{contestId}")
    public ResponseEntity<List<UserState>> getAllContestState(@PathVariable Long contestId) throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

        List<ContestState> contestStates = contestStateService.getAllByContest(contest.getId());

        List<UserState> toReturn = new ArrayList<>();
        for (ContestState contestState : contestStates) {
            UserState toAdd = new UserState(contestState.getUser().getUsername(), contestState.getUser().getEmail(), contestState.getState());
            toReturn.add(toAdd);
        }

        return ResponseEntity.ok(toReturn);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getAllContestStateLogs/{contestId}")
    public ResponseEntity<List<WordleStateLog>> getAllContestStateLogs(@PathVariable Long contestId) throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

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
    @GetMapping("/getAllUserContestStateLogs/{contestId}/{userName}")
    public ResponseEntity<List<WordleStateLog>> getAllUserContestStateLogs(@PathVariable Long contestId, @PathVariable String userName) throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!userService.existsByUserName(userName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);
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
    @GetMapping("/existsInExternalDictionary/{contestId}/{wordle}")
    public ResponseEntity<Boolean> existsInExternalDictionary(@PathVariable Long contestId, @PathVariable String wordle) {
        if(!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);
        return ResponseEntity.ok(dictionaryService.existsInExternalDictionary(wordle, contest.getId()));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/saveExternalDictionary/{contestId}")
    public ResponseEntity<List<DictionaryExternal>> saveExternalDictionary(@PathVariable Long contestId, @RequestBody List<String> words) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Contest contestToLink = contestService.getById(contestId);

        List<DictionaryExternal> toSave = new ArrayList<>();
        for (String word : words) {
            DictionaryExternal wordToSave = new DictionaryExternal(word);
            wordToSave.setContest(contestToLink);
            toSave.add(wordToSave);
        }

        return ResponseEntity.ok(dictionaryService.saveExternal(toSave));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT') || hasRole('ADMIN')")
    @GetMapping("/getWordles/{contestId}")
    public ResponseEntity<List<Wordle>> getWordles(@PathVariable Long contestId) {
        if(!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);
        return ResponseEntity.ok(wordleService.findByContestId(contest.getId()));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getLogsInExcel/{contestId}")
    public ResponseEntity<Resource> getLogsInExcel(@PathVariable Long contestId) {
        if(!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getaLLProfessors")
    public ResponseEntity<List<User>> getAllProfessors() {
        List<User> userList = userService.getAll();
        List<User> toReturn = new ArrayList<>();
        for (User user : userList) {
            if (user.getRoles().contains(RolName.ROLE_ADMIN))
                toReturn.add(user);
        }
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }
}