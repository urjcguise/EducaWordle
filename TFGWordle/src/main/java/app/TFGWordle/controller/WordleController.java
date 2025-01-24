package app.TFGWordle.controller;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Folder;
import app.TFGWordle.model.Wordle;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.FolderService;
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
    private final UserService userService;
    private final FolderService folderService;

    public WordleController(WordleService wordleService, ContestService contestService, UserService userService, FolderService folderService) {
        this.wordleService = wordleService;
        this.contestService = contestService;
        this.userService = userService;
        this.folderService = folderService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newWordles/{contestName}/{professorName}/{folderName}")
    public ResponseEntity<List<Wordle>> createWordles(@RequestBody List<String> wordles, @PathVariable String contestName, @PathVariable String professorName, @PathVariable String folderName) {

        if (!userService.existsByUserName(professorName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!contestName.equals( "empty") & !contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!folderName.equals("empty") && !folderService.existsByName(folderName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User professor = userService.getByUserName(professorName).get();
        List<Contest> contests = new ArrayList<>();

        if (!contestName.equals( "empty"))
            contests.add(contestService.getByName(contestName));

        List<Wordle> toSave = new ArrayList<>();

        for (String wordle : wordles) {
            Wordle newWordle = new Wordle(wordle, contests, professor);
            if (!folderName.equals("empty"))
                newWordle.setFolder(folderService.getByName(folderName));
            toSave.add(newWordle);
        }
        if (!contests.isEmpty())
            contests.get(0).updateWordles(toSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(wordleService.saveAll(toSave));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/deleteWordles")
    public ResponseEntity<?> deleteWordles(@RequestBody List<String> wordlesName) {

        for (String word : wordlesName) {
            if (!wordleService.existsByWord(word))
                return new ResponseEntity<>("No existe la palabra " + word, HttpStatus.NOT_FOUND);

            Wordle wordle = wordleService.getByWord(word);
            if (!wordle.getContests().isEmpty()) {
                List<Contest> contests = wordle.getContests();
                for (Contest contest : contests) {
                    contest.getWordles().remove(wordle);
                    contestService.save(contest);
                }
            }
            wordleService.delete(wordle);
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/updateWordle/{wordInitial}/{wordUpdated}")
    public ResponseEntity<?> updateWordles(@PathVariable String wordInitial, @PathVariable String wordUpdated, @RequestBody List<Contest> contests) {

        if (!wordleService.existsByWord(wordInitial)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Wordle wordle = wordleService.getByWord(wordInitial);
        List<Contest> oldContests = new ArrayList<>(wordle.getContests());

        wordle.setWord(wordUpdated);
        wordle.setContests(contests);

        for (Contest oldContest : oldContests) {
            if (!contests.contains(oldContest)) {
                oldContest.getWordles().remove(wordle);
                contestService.save(oldContest);
            }
        }

        for (Contest contest : contests) {
            if (!contestService.existsContest(contest.getContestName())) {
                return new ResponseEntity<>("El concurso " + contest.getContestName() + " no existe", HttpStatus.NOT_FOUND);
            }

            if (contest.getCompetition() == null) {
                Contest existingContest = contestService.getByName(contest.getContestName());
                contest.setCompetition(existingContest.getCompetition());
            }

            if (!contest.getWordles().contains(wordle)) {
                contest.getWordles().add(wordle);
                contestService.save(contest);
            }
        }

        wordleService.save(wordle);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('STUDENT') || hasRole('ADMIN')")
    @GetMapping("/getWordlesByContest/{contestName}")
    public ResponseEntity<List<Wordle>> getWordlesByContest(@PathVariable String contestName) {

        if (!contestService.existsContest(contestName))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Contest contest = contestService.getByName(contestName);

        return ResponseEntity.status(HttpStatus.OK).body(wordleService.findByContestId(contest.getId()));
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getWordlesByProfessor/{professorName}")
    public ResponseEntity<List<Wordle>> getWordlesByProfessor(@PathVariable String professorName) {

        if (!userService.existsByUserName(professorName))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        User professor = userService.getByUserName(professorName).get();
        List<Wordle> toReturn = new ArrayList<>();

        for (Wordle w : wordleService.findByProfessorId(professor.getId())) {
            if (w.getFolder() == null)
                toReturn.add(w);
        }

        return ResponseEntity.status(HttpStatus.OK).body(toReturn);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getContestsWhereIsUsed/{word}")
    public ResponseEntity<List<Contest>> getContestsWhereIsUsed(@PathVariable String word) {
        if(!wordleService.existsByWord(word))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Wordle wordle = wordleService.getByWord(word);
        return ResponseEntity.status(HttpStatus.OK).body(wordle.getContests());
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newFolder/{folderName}")
    public ResponseEntity<?> createFolder(@PathVariable String folderName, @RequestBody String professorName) {
        if (!userService.existsByUserName(professorName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User professor = userService.getByUserName(professorName).get();

        Folder newFolder = new Folder(folderName, professor);
        newFolder.setWordles(new ArrayList<>());
        newFolder.setFolders(new ArrayList<>());

        folderService.save(newFolder);

        return ResponseEntity.status(HttpStatus.CREATED).body(newFolder);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/deleteFolders")
    public ResponseEntity<?> deleteFolders(@RequestBody List<String> foldersName) {
        for (String folderName : foldersName) {
            if (!folderService.existsByName(folderName))
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            Folder folder = folderService.getByName(folderName);
            folderService.delete(folder);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newFolderInsideFolder/{newFolderName}/{folderName}")
    public ResponseEntity<?> createFolderInsideFolder(@PathVariable String newFolderName, @PathVariable String folderName, @RequestBody String professorName) {
        if (!userService.existsByUserName(professorName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!folderService.existsByName(folderName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Folder parentFolder = folderService.getByName(folderName);
        User professor = userService.getByUserName(professorName).get();

        Folder newFolder = new Folder(newFolderName, professor);
        newFolder.setParentFolder(parentFolder);
        newFolder.setWordles(new ArrayList<>());
        newFolder.setFolders(new ArrayList<>());

        parentFolder.getFolders().add(newFolder);

        folderService.save(parentFolder);

        return ResponseEntity.status(HttpStatus.CREATED).body(newFolder);
    }


    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getFoldersByProfessor/{professorName}")
    public ResponseEntity<List<Folder>> getFoldersByProfessor(@PathVariable String professorName) {
        if(!userService.existsByUserName(professorName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User professor = userService.getByUserName(professorName).get();
        List<Folder> toReturn = new ArrayList<>();

        for (Folder f : folderService.getByProfessor(professor)) {
            if (f.getParentFolder() == null)
                toReturn.add(f);
        }
        return ResponseEntity.status(HttpStatus.OK).body(toReturn);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editFolder/{oldFolderName}")
    public ResponseEntity<?> editFolder(@PathVariable String oldFolderName, @RequestBody String newFolderName) {
        if (!folderService.existsByName(oldFolderName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!oldFolderName.equals(newFolderName)) {
            Folder folder = folderService.getByName(oldFolderName);
            folder.setName(newFolderName);
            folderService.save(folder);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getFoldersByFolderName/{folderName}")
    public ResponseEntity<List<Folder>> getFoldersByFolderName(@PathVariable String folderName) {
        if(!folderService.existsByName(folderName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.OK).body(folderService.getByName(folderName).getFolders());
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getWordlesByFolderName/{folderName}")
    public ResponseEntity<List<Wordle>> getWordlesByFolderName(@PathVariable String folderName) {
        if(!folderService.existsByName(folderName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.OK).body(folderService.getByName(folderName).getWordles());
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/moveToFolder/{folderName}")
    public ResponseEntity<?> moveToFolder(@PathVariable String folderName, @RequestBody List<String> wordles) {
        if (!folderService.existsByName(folderName))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Folder folder = folderService.getByName(folderName);

        for (String wordle : wordles) {
            if (!wordleService.existsByWord(wordle))
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            Wordle wordleToMove = wordleService.getByWord(wordle);
            wordleToMove.setFolder(folder);
            wordleService.save(wordleToMove);
            folder.getWordles().add(wordleToMove);
        }
        folderService.save(folder);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
