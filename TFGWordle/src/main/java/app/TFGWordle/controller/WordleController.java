package app.TFGWordle.controller;

import app.TFGWordle.dto.FolderDTO;
import app.TFGWordle.dto.WordleDTO;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Folder;
import app.TFGWordle.model.Wordle;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.ContestStateService;
import app.TFGWordle.service.FolderService;
import app.TFGWordle.service.WordleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wordle")
@CrossOrigin
public class WordleController {

    private final WordleService wordleService;
    private final ContestService contestService;
    private final UserService userService;
    private final FolderService folderService;
    private final ContestStateService contestStateService;

    public WordleController(WordleService wordleService, ContestService contestService, UserService userService, FolderService folderService, ContestStateService contestStateService) {
        this.wordleService = wordleService;
        this.contestService = contestService;
        this.userService = userService;
        this.folderService = folderService;
        this.contestStateService = contestStateService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newWordles/{contestId}/{professorName}/{folderId}")
    public ResponseEntity<List<Wordle>> newWordles(@RequestBody List<String> wordles, @PathVariable Long contestId, @PathVariable String professorName, @PathVariable Long folderId) {
        if (contestId != 0 & !contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (folderId != 0 && !folderService.existsById(folderId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Contest> contests = new ArrayList<>();

        if (contestId != 0)
            contests.add(contestService.getById(contestId));

        List<Wordle> toSave = new ArrayList<>();

        for (String wordle : wordles) {
            Wordle newWordle = new Wordle(wordle.toUpperCase(), contests, professor);
            if (folderId != 0)
                newWordle.setFolder(folderService.getById(folderId));
            toSave.add(newWordle);
        }
        wordleService.saveAll(toSave);
        if (!contests.isEmpty()) {
            Contest contestToSave = contests.get(0);
            contestToSave.updateWordles(toSave);
            contestToSave.calculateWordlesLength();
            contestService.save(contestToSave);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @DeleteMapping("/deleteWordles")
    public ResponseEntity<?> deleteWordles(@RequestBody List<Wordle> wordles) {

        for (Wordle wordle : wordles) {
            if (!wordleService.existsById(wordle.getId()))
                return new ResponseEntity<>("No existe la palabra " + wordle.getWord(), HttpStatus.NOT_FOUND);
            Wordle wordleToEdit = wordleService.getById(wordle.getId());
            if (!wordleToEdit.getContests().isEmpty()) {
                List<Contest> contests = wordleToEdit.getContests();
                for (Contest contest : contests) {
                    int wordlePosition = contest.getWordles().indexOf(wordleToEdit);
                    contest.getWordlesLength().remove(wordlePosition);
                    contest.getWordles().remove(wordleToEdit);
                    contest.setCompetition(contestService.getById(contest.getId()).getCompetition());
                    contestService.save(contest);
                }
            }
            wordleService.delete(wordleToEdit);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editWordle/{wordle}")
    public ResponseEntity<?> editWordle(@PathVariable String wordle, @RequestBody String newWordle) {
        if (!wordleService.existsByWord(wordle))
            return new ResponseEntity<>("No existe la palabra " + wordle, HttpStatus.NOT_FOUND);

        Wordle wordleToEdit = wordleService.getByWord(wordle);
        wordleToEdit.setWord(newWordle.toUpperCase());
        wordleService.save(wordleToEdit);

        for (Contest contest : wordleToEdit.getContests()) {
            contest.calculateWordlesLength();
            contestService.save(contest);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/updateWordle/{wordInitial}/{wordUpdated}")
    public ResponseEntity<?> updateWordles(@PathVariable String wordInitial, @PathVariable String wordUpdated, @RequestBody List<Contest> contests) {

        if (!wordleService.existsByWord(wordInitial)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Wordle wordle = wordleService.getByWord(wordInitial);
        List<Contest> oldContests = new ArrayList<>(wordle.getContests());

        wordle.setWord(wordUpdated.toUpperCase());
        wordle.setContests(contests);

        for (Contest oldContest : oldContests) {
            if (!contests.contains(oldContest)) {
                int index = oldContest.getWordles().indexOf(wordle);
                oldContest.getWordles().remove(index);
                oldContest.getWordlesLength().remove(index);
                contestService.save(oldContest);
            }
        }

        for (Contest contest : contests) {
            if (!contestService.existsById(contest.getId())) {
                return new ResponseEntity<>("El concurso " + contest.getContestName() + " no existe", HttpStatus.NOT_FOUND);
            }

            if (contest.getCompetition() == null) {
                Contest existingContest = contestService.getById(contest.getId());
                contest.setCompetition(existingContest.getCompetition());
            }

            if (!contest.getWordles().contains(wordle)) {
                contest.getWordles().add(wordle);
                contest.getWordlesLength().add(wordle.getWord().length());
                contestService.save(contest);
            }
        }

        wordleService.save(wordle);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getWordlesByContest/{contestId}")
    public ResponseEntity<List<Wordle>> getWordlesByContest(@PathVariable Long contestId) throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contest contest = contestService.getById(contestId);

        boolean isFinished = contest.getEndDate().before(new Date());

        if (!isFinished) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            boolean isStudent = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"));
            if (isStudent) {
                User user = userService.getByUserName(((UserDetails) authentication.getPrincipal()).getUsername())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                JsonNode state = contestStateService.getContestState(contestId, user.getId()).getState();
                for (int i = 0; i < contest.getWordles().size(); i++) {
                    if (!state.get("games").get(i).get("finished").asBoolean())
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }

        return new ResponseEntity<>(contest.getWordles(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getWordlesByProfessor/{professorName}")
    public ResponseEntity<List<WordleDTO>> getWordlesByProfessor(@PathVariable String professorName) {
        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<WordleDTO> toReturn = new ArrayList<>();

        for (Wordle w : wordleService.findByProfessorId(professor.getId())) {
            if (w.getFolder() == null) {
                WordleDTO wordleToReturn = new WordleDTO(w);
                toReturn.add(wordleToReturn);
            }
        }

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getContestsWhereIsUsed/{word}")
    public ResponseEntity<List<Contest>> getContestsWhereIsUsed(@PathVariable String word) {
        if(!wordleService.existsByWord(word))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Wordle wordle = wordleService.getByWord(word);
        return new ResponseEntity<>(wordle.getContests(), HttpStatus.OK);
    }

    @GetMapping("/checkWordleAttempt/{contestId}/{wordleIndex}/{word}/{userEmail}")
    public ResponseEntity<List<Integer>> checkWordleAttempt(@PathVariable Long contestId, @PathVariable Integer wordleIndex, @PathVariable String word, @PathVariable String userEmail) {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userService.getByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Map<Character, Integer> letterCounts = contestStateService.getContestState(contestId, user.getId()).getLetterCountsList().get(wordleIndex);

        List<Integer> toReturn = new ArrayList<>();
        Contest contest = contestService.getById(contestId);
        String wordle = contest.getWordles().get(wordleIndex).getWord();

        boolean isAccentMode = contest.getAccentMode();

        for (int i = 0; i < wordle.length(); i++) {
            char expected = wordle.charAt(i);
            char got = Character.toUpperCase(word.charAt(i));

            if (!isAccentMode)
                expected = deleteAccent(expected);

            if (expected == got && letterCounts.containsKey(got) && letterCounts.get(got) > 0) {
                toReturn.add(2);
                letterCounts.put(got, letterCounts.get(got) - 1);
            }
            else
                toReturn.add(3);
        }

        for (int i = 0; i < wordle.length(); i++) {
            char got = Character.toUpperCase(word.charAt(i));
            if (toReturn.get(i) == 3 && wordle.contains(String.valueOf(got)) && letterCounts.containsKey(got) && letterCounts.get(got) > 0) {
                toReturn.set(i, 1);
                letterCounts.put(got, letterCounts.get(got) - 1);
            }
        }

        for (int i = 0; i < wordle.length(); i++) {
            if (toReturn.get(i) == 3)
                toReturn.set(i, 0);
        }

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @GetMapping("/getWordleInContest/{contestId}/{wordleIndex}")
    public ResponseEntity<Wordle> getWordleInContest(@PathVariable Long contestId, @PathVariable Integer wordleIndex) throws JsonProcessingException {
        if (!contestService.existsById(contestId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getByUserName(((UserDetails) authentication.getPrincipal()).getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        JsonNode state = contestStateService.getContestState(contestId, user.getId()).getState();
        if (!state.get("games").get(wordleIndex).get("finished").asBoolean())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(contestService.getById(contestId).getWordles().get(wordleIndex), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newFolder/{folderName}")
    public ResponseEntity<?> createFolder(@PathVariable String folderName, @RequestBody String professorName) {
        if (folderService.existsByName(folderName))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Folder newFolder = new Folder(folderName, professor);
        newFolder.setWordles(new ArrayList<>());
        newFolder.setFolders(new ArrayList<>());

        folderService.save(newFolder);

        return new ResponseEntity<>(newFolder, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @DeleteMapping("/deleteFolders")
    public ResponseEntity<?> deleteFolders(@RequestBody List<Long> foldersIds) {
        for (Long folderId : foldersIds) {
            if (!folderService.existsById(folderId))
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            Folder folder = folderService.getById(folderId);
            folderService.delete(folder);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newFolderInsideFolder/{newFolderName}/{folderId}")
    public ResponseEntity<?> createFolderInsideFolder(@PathVariable String newFolderName, @PathVariable Long folderId, @RequestBody String professorName) {
        if (!folderService.existsById(folderId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Folder parentFolder = folderService.getById(folderId);

        if (parentFolder.getFolders().stream().anyMatch(folder -> folder.getName().equals(newFolderName)))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Folder newFolder = new Folder(newFolderName, professor);
        newFolder.setParentFolder(parentFolder);
        newFolder.setWordles(new ArrayList<>());
        newFolder.setFolders(new ArrayList<>());

        parentFolder.getFolders().add(newFolder);

        folderService.save(parentFolder);

        return new ResponseEntity<>(newFolder, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getFoldersByProfessor/{professorName}")
    public ResponseEntity<List<FolderDTO>> getFoldersByProfessor(@PathVariable String professorName) {
        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<FolderDTO> toReturn = new ArrayList<>();

        for (Folder f : folderService.getByProfessor(professor)) {
            if (f.getParentFolder() == null)
                toReturn.add(new FolderDTO(f));
        }
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editFolder/{oldFolderId}")
    public ResponseEntity<?> editFolder(@PathVariable Long oldFolderId, @RequestBody String newFolderName) {
        if (!folderService.existsById(oldFolderId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Folder folder = folderService.getById(oldFolderId);
        if (!folder.getName().equals(newFolderName)) {
            folder.setName(newFolderName);
            folderService.save(folder);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getFolder/{folderId}")
    public ResponseEntity<FolderDTO> getFolder(@PathVariable Long folderId) {
        if(!folderService.existsById(folderId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Folder folder = folderService.getById(folderId);
        return ResponseEntity.status(HttpStatus.OK).body(new FolderDTO(folder));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getFoldersInsideFolder/{folderId}")
    public ResponseEntity<List<Folder>> getFoldersByFolderId(@PathVariable Long folderId) {
        if (!folderService.existsById(folderId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Folder parentFolder = folderService.getById(folderId);

        return new ResponseEntity<>(parentFolder.getFolders(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getWordlesInsideFolder/{folderId}")
    public ResponseEntity<List<Wordle>> getWordlesByFolderId(@PathVariable Long folderId) {
        if(!folderService.existsById(folderId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(folderService.getById(folderId).getWordles(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/moveToFolder/{folderId}")
    public ResponseEntity<?> moveToFolder(@PathVariable Long folderId, @RequestBody List<String> wordles) {
        if (folderId == 0) {
            for (String wordle : wordles) {
                if (!wordleService.existsByWord(wordle))
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                Wordle wordleToMove = wordleService.getByWord(wordle);
                wordleToMove.setFolder(null);
                wordleService.save(wordleToMove);
            }
        } else {
            if (!folderService.existsById(folderId))
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            Folder folder = folderService.getById(folderId);

            for (String wordle : wordles) {
                if (!wordleService.existsByWord(wordle))
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                Wordle wordleToMove = wordleService.getByWord(wordle);
                wordleToMove.setFolder(folder);
                wordleService.save(wordleToMove);
                folder.getWordles().add(wordleToMove);
            }
            folderService.save(folder);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private char deleteAccent(char c) {
        switch (c) {
            case 'Á': case 'á': return 'A';
            case 'É': case 'é': return 'E';
            case 'Í': case 'í': return 'I';
            case 'Ó': case 'ó': return 'O';
            case 'Ú': case 'ú': return 'U';
            default: return c;
        }
    }
}
