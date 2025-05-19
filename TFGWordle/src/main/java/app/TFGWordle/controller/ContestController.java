package app.TFGWordle.controller;

import app.TFGWordle.dto.*;
import app.TFGWordle.model.*;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

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

    public ContestController(ContestService contestService, CompetitionService competitionService,
            UserService userService, WordleService wordleService, ContestStateService contestStateService,
            DictionaryService dictionaryService, ObjectMapper objectMapper) {
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
        contest.setRandomMode(false);
        contest.setAccentMode(false);

        competitionService.save(competition);
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.save(contest));
    }

    @GetMapping("/{competitionId}/contests")
    public ResponseEntity<List<Contest>> getContestsByCompetition(@PathVariable Long competitionId) {
        if (!competitionService.existsCompetition(competitionId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Competition competition = competitionService.getCompetitionById(competitionId);
        List<Contest> contests = contestService.getContestsByCompetition(competition);
        return new ResponseEntity<>(contests, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @DeleteMapping("/deleteContest/{contestId}")
    public ResponseEntity<?> deleteContest(@PathVariable Long contestId) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

        if (contest.getUseExternalFile())
            dictionaryService.deleteWordsByContest(contest);

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

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editContest")
    public ResponseEntity<?> editContest(@RequestBody Contest contest) {
        if (!contestService.existsById(contest.getId()))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        contest.setCompetition(contestService.getById(contest.getId()).getCompetition());

        contestService.save(contest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editRandomMode/{contestId}")
    public ResponseEntity<Boolean> editRandomMode(@RequestBody Boolean newMode, @PathVariable Long contestId) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);
        contest.setRandomMode(newMode);
        contestService.save(contest);

        return new ResponseEntity<>(newMode, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editAccentMode/{contestId}")
    public ResponseEntity<Boolean> editAccentMode(@RequestBody Boolean newMode, @PathVariable Long contestId) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);
        contest.setAccentMode(newMode);
        contestService.save(contest);

        return new ResponseEntity<>(newMode, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/deleteWordlesInContest/{contestId}")
    public ResponseEntity<?> deleteWordlesInContest(@PathVariable Long contestId, @RequestBody List<String> wordles) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

        for (String word : wordles) {
            if (!wordleService.existsByWord(word))
                return new ResponseEntity<>("Wordle no encontrado", HttpStatus.NOT_FOUND);

            Wordle wordle = wordleService.getByWord(word);
            contest.getWordles().remove(wordle);
            wordle.getContests().remove(contest);

            wordleService.save(wordle);
        }
        contest.calculateWordlesLength();
        contestService.save(contest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/addWordlesToContest/{contestId}")
    public ResponseEntity<?> addWordlesToContest(@PathVariable Long contestId, @RequestBody List<String> wordles) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

        for (String word : wordles) {
            if (!wordleService.existsByWord(word))
                return new ResponseEntity<>("Wordle no encontrado", HttpStatus.NOT_FOUND);

            Wordle wordle = wordleService.getByWord(word);

            if (wordle.getContests().contains(contest))
                return new ResponseEntity<>("Wordle ya existente en el concurso", HttpStatus.CONFLICT);

            contest.getWordles().add(wordle);
            contest.calculateWordlesLength();

            wordle.getContests().add(contest);
            wordleService.save(wordle);
        }

        contestService.save(contest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/changeWordlesPosition/{contestId}")
    public ResponseEntity<?> changeWordlesPosition(@PathVariable Long contestId, @RequestBody List<String> wordles) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

        List<Wordle> newOrder = new ArrayList<>();

        for (String word : wordles) {
            if (!wordleService.existsByWord(word))
                return new ResponseEntity<>("Wordle no encontrado", HttpStatus.NOT_FOUND);

            Wordle wordle = wordleService.getByWord(word);

            newOrder.add(wordle);
        }

        contest.setWordles(newOrder);
        contest.calculateWordlesLength();

        contestService.save(contest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{contestId}/contest")
    public ResponseEntity<Contest> getContestById(@PathVariable Long contestId) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(contestService.getById(contestId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/copyContest/{oldContestId}")
    public ResponseEntity<?> copyContest(@PathVariable Long oldContestId) {
        if (!contestService.existsById(oldContestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest oldContest = contestService.getById(oldContestId);
        Contest newContest = new Contest();

        newContest.setContestName(oldContest.getContestName() + "_copia");
        newContest.setCompetition(oldContest.getCompetition());

        Date actualDate = new Date((new Date().getTime() / 1000) * 1000);
        newContest.setStartDate(actualDate);
        long year = 365L * 24 * 60 * 60 * 1000;
        newContest.setEndDate(new Date(actualDate.getTime() + year));

        newContest.setNumTries(oldContest.getNumTries());
        newContest.setUseDictionary(oldContest.getUseDictionary());
        newContest.setUseExternalFile(oldContest.getUseExternalFile());
        newContest.setRandomMode(oldContest.getRandomMode());
        newContest.setAccentMode(oldContest.getAccentMode());
        newContest.setWordlesLength(oldContest.getWordlesLength());
        List<Wordle> copiedWordles = new ArrayList<>(oldContest.getWordles());
        newContest.setWordles(copiedWordles);

        contestService.save(newContest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/resumeContest/{contestId}/{userName}")
    public ResponseEntity<ResumeContestDTO> resumeContest(@PathVariable Long contestId, @PathVariable String userName)
            throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Contest contest = contestService.getById(contestId);

        if (!contestStateService.existsState(contest.getId(), user.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        WordleState wordleState = contestStateService.getState(contest.getId(), user.getId());
        List<WordleState.Game> games = wordleState.getGames();
        int firstUnfinished = -1;
        int posFirstUnfinished = -1;

        if (contest.getRandomMode()) {
            List<Integer> wordleOrder = wordleState.getWordleOrder();
            for (int i = 0; i < wordleOrder.size(); i++) {
                int realIndex = wordleOrder.get(i);
                WordleState.Game game = games.get(realIndex);
                if (!game.isFinished()) {
                    firstUnfinished = realIndex;
                    posFirstUnfinished = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < games.size(); i++) {
                WordleState.Game game = games.get(i);
                boolean finished = game.isFinished();

                if (!finished) {
                    firstUnfinished = i;
                    break;
                }
            }
        }

        List<String> logs = contestStateService.getAllLogsByContestAndUser(contest.getId(), user.getId());

        ResumeContestDTO resumeContestDTO = new ResumeContestDTO();
        resumeContestDTO.setWordleOrder(wordleState.getWordleOrder());
        if (contest.getRandomMode())
            resumeContestDTO.setWordlePosition(posFirstUnfinished);
        else
            resumeContestDTO.setWordlePosition(firstUnfinished);
        resumeContestDTO.setWordleState(wordleState);

        if (logs.isEmpty()) {
            resumeContestDTO.setTryPosition(0);
            resumeContestDTO.setCharPosition(0);
            resumeContestDTO.setTries(new ArrayList<>());
            return new ResponseEntity<>(resumeContestDTO, HttpStatus.OK);
        }

        List<WordleStateLog> logsInWordle = new ArrayList<>();
        for (String csLog : logs) {
            WordleStateLog toAdd = contestStateService.getStateLog(csLog);
            if (toAdd.getWordlePosition() == (firstUnfinished + 1))
                logsInWordle.add(toAdd);
        }

        resumeContestDTO.setTries(new ArrayList<>());

        if (logsInWordle.isEmpty()) {
            resumeContestDTO.setTryPosition(0);
            resumeContestDTO.setCharPosition(0);
            return new ResponseEntity<>(resumeContestDTO, HttpStatus.OK);
        }

        resumeContestDTO.setTryPosition(logsInWordle.size());

        final String wordleToSolve = contest.getWordles().get(firstUnfinished).getWord();
        final int charPosition = logsInWordle.size() * wordleToSolve.length();
        resumeContestDTO.setCharPosition(charPosition);

        List<ResumeContestDTO.Try> tries = new ArrayList<>();

        Map<Character, Integer> originalMap = contestStateService
                .getContestState(contestId, user.getId())
                .getLetterCountsList()
                .get(firstUnfinished);

        for (WordleStateLog log : logsInWordle) {
            ResumeContestDTO.Try newTry = new ResumeContestDTO.Try();
            Map<Character, Integer> letterCounts = new HashMap<>(originalMap);
            List<ResumeContestDTO.Try.Letter> letters = new ArrayList<>();

            String wordleInserted = log.getWordleInserted();

            for (int i = 0; i < wordleToSolve.length(); i++) {
                char expected = wordleToSolve.charAt(i);
                char got = wordleInserted.charAt(i);

                ResumeContestDTO.Try.Letter newLetter = new ResumeContestDTO.Try.Letter();
                newLetter.setLetter(got);

                if (expected == got && letterCounts.containsKey(got) && letterCounts.get(got) > 0) {
                    newLetter.setState(2);
                    letterCounts.put(got, letterCounts.get(got) - 1);
                } else
                    newLetter.setState(3);

                letters.add(newLetter);
            }

            for (int i = 0; i < wordleToSolve.length(); i++) {
                char got = wordleInserted.charAt(i);

                if (letters.get(i).getState() == 3 && wordleToSolve.contains(String.valueOf(got))
                        && letterCounts.containsKey(got) && letterCounts.get(got) > 0) {
                    letters.get(i).setState(1);
                    letterCounts.put(got, letterCounts.get(got) - 1);
                }
            }

            for (int i = 0; i < wordleToSolve.length(); i++) {
                if (letters.get(i).getState() == 3)
                    letters.get(i).setState(0);
            }

            newTry.setLetters(letters);
            tries.add(newTry);
        }

        resumeContestDTO.setTries(tries);

        return new ResponseEntity<>(resumeContestDTO, HttpStatus.OK);
    }

    @PostMapping("/newContestState/{contestId}/{userName}")
    public ResponseEntity<?> createContestState(@PathVariable Long contestId, @PathVariable String userName,
            @RequestBody WordleState wordleState) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Contest contest = contestService.getById(contestId);

        boolean isAccentMode = contest.getAccentMode();

        List<Map<Character, Integer>> letterCounts = new ArrayList<>();
        for (Wordle wordle : contest.getWordles()) {
            Map<Character, Integer> letterCount = new HashMap<>();
            for (char c : wordle.getWord().toCharArray()) {
                char charToSave = isAccentMode ? c : deleteAccent(c);
                letterCount.put(charToSave, letterCount.getOrDefault(charToSave, 0) + 1);
            }
            letterCounts.add(letterCount);
        }

        if (contestStateService.existsState(contest.getId(), user.getId()))
            return new ResponseEntity<>("Estado ya creado", HttpStatus.CONFLICT);

        ContestState newContestState = new ContestState(contest, user);
        newContestState.setLetterCountsList(letterCounts);

        try {
            JsonNode jsonNode = objectMapper.valueToTree(wordleState);
            newContestState.setState(jsonNode);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar los datos" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contestStateService.save(newContestState), HttpStatus.CREATED);
    }

    @GetMapping("/getContestState/{contestId}/{userName}")
    public ResponseEntity<WordleState> getContestState(@PathVariable Long contestId, @PathVariable String userName) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Contest contest = contestService.getById(contestId);

        if (!contestStateService.existsState(contest.getId(), user.getId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(contestStateService.getState(contest.getId(), user.getId()), HttpStatus.OK);
    }

    @PostMapping("/updateContestState/{contestId}/{userName}")
    public ResponseEntity<?> updateContestState(@PathVariable Long contestId, @PathVariable String userName,
            @RequestBody WordleState wordleState) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Contest contest = contestService.getById(contestId);
        ContestState contestState = contestStateService.getContestState(contest.getId(), user.getId());
        try {
            JsonNode jsonNode = objectMapper.valueToTree(wordleState);
            contestState.setState(jsonNode);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar los datos" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contestStateService.save(contestState), HttpStatus.OK);
    }

    @GetMapping("/getAllContestState/{contestId}")
    public ResponseEntity<List<UserState>> getAllContestState(@PathVariable Long contestId)
            throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

        List<ContestState> contestStates = contestStateService.getAllByContest(contest.getId());

        List<UserState> toReturn = new ArrayList<>();
        for (ContestState contestState : contestStates) {
            UserState toAdd = new UserState(contestState.getUser().getUsername(), contestState.getUser().getEmail(),
                    contestState.getState());
            toReturn.add(toAdd);
        }

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PostMapping("/createContestLog/{contestId}/{wordlePosition}/{userName}")
    public ResponseEntity<?> createContestLog(@PathVariable Long contestId, @PathVariable Integer wordlePosition,
            @PathVariable String userName, @RequestBody WordleStateLog wordleStateLog) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>("Concurso no encontrado", HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Contest contest = contestService.getById(contestId);
        Wordle wordle = contest.getWordles().get(wordlePosition);

        ContestStateLog contestStateLog = new ContestStateLog(contest, user);
        wordleStateLog.setWordleToGuess(wordle.getWord().toUpperCase());
        try {
            JsonNode jsonNode = objectMapper.valueToTree(wordleStateLog);
            contestStateLog.setState(jsonNode);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar los datos" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        contestStateService.saveLog(contestStateLog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getAllUserContestStateLogs/{contestId}/{userName}")
    public ResponseEntity<List<WordleStateLog>> getAllUserContestStateLogs(@PathVariable Long contestId,
            @PathVariable String userName) throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Contest contest = contestService.getById(contestId);

        List<String> contestStatesLogs = contestStateService.getAllLogsByContestAndUser(contest.getId(), user.getId());

        if (contestStatesLogs.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<WordleStateLog> toReturn = new ArrayList<>();
        for (String csLog : contestStatesLogs) {
            WordleStateLog toAdd = contestStateService.getStateLog(csLog);
            toReturn.add(toAdd);
        }

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getAllContestStateLogs/{contestId}")
    public ResponseEntity<List<WordleStateLog>> getAllContestStateLogs(@PathVariable Long contestId)
            throws JsonProcessingException {
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

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/deleteAllProfessorState/{contestId}/{professorName}")
    public ResponseEntity<?> deleteAllProfessorState(@PathVariable Long contestId, @PathVariable String professorName) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ContestState toDelete = contestStateService.getContestState(contestId, professor.getId());
        contestStateService.deleteById(toDelete.getId());

        List<ContestStateLog> logsToDelete = contestStateService.getLogsByContestIdAndUser(contestId,
                professor.getId());
        contestStateService.deleteAllLogs(logsToDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/existsInDictionary/{word}")
    public ResponseEntity<Boolean> existsInDictionary(@PathVariable String word) {
        return new ResponseEntity<>(dictionaryService.existsInGlobalDictionary(word.toLowerCase()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/saveExternalDictionary/{contestId}")
    public ResponseEntity<List<DictionaryExternal>> saveExternalDictionary(@PathVariable Long contestId,
            @RequestBody List<String> words) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contestToLink = contestService.getById(contestId);

        List<DictionaryExternal> toSave = new ArrayList<>();
        for (String word : words) {
            DictionaryExternal wordToSave = new DictionaryExternal(word);
            wordToSave.setContest(contestToLink);
            toSave.add(wordToSave);
        }

        return new ResponseEntity<>(dictionaryService.saveExternal(toSave), HttpStatus.OK);
    }

    @GetMapping("/existsInExternalDictionary/{contestId}/{wordle}")
    public ResponseEntity<Boolean> existsInExternalDictionary(@PathVariable Long contestId,
            @PathVariable String wordle) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);
        return ResponseEntity.ok(dictionaryService.existsInExternalDictionary(wordle, contest.getId()));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/exportLogsInExcel/{contestId}")
    public ResponseEntity<Resource> exportLogsInExcel(@PathVariable Long contestId) {
        if (!contestService.existsById(contestId))
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

    private char deleteAccent(char c) {
        switch (c) {
            case 'Á':
            case 'á':
                return 'A';
            case 'É':
            case 'é':
                return 'E';
            case 'Í':
            case 'í':
                return 'I';
            case 'Ó':
            case 'ó':
                return 'O';
            case 'Ú':
            case 'ú':
                return 'U';
            default:
                return c;
        }
    }

    private void writeDataToExcel(List<String> logs, ByteArrayOutputStream out) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Logs");

            Row headerRow = sheet.createRow(0);
            String[] headers = { "Alumno", "Correo", "Tiempo", "Palabra adivinar", "Posición palabra",
                    "Intento palabra", "Intento", "Estado" };

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