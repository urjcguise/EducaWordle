package app.TFGWordle.service;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Wordle;
import app.TFGWordle.repository.WordleRepository;
import app.TFGWordle.security.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordleServiceTest {

    @Mock
    private WordleRepository wordleRepository;

    @InjectMocks
    private WordleService wordleService;


    @Test
    void saveAll() {
        Wordle wordle1 = new Wordle();
        wordle1.setWord("Palabra1");
        Wordle wordle2 = new Wordle();
        wordle2.setWord("Palabra2");
        List<Wordle> wordles = List.of(wordle1, wordle2);

        when(wordleRepository.saveAll(wordles)).thenReturn(wordles);

        List<Wordle> resultList = wordleService.saveAll(wordles);

        assertNotNull(resultList);
        assertEquals(wordles.size(), resultList.size());
        assertEquals(wordle1, resultList.get(0));
        assertEquals(wordle2, resultList.get(1));

        verify(wordleRepository, times(1)).saveAll(wordles);
    }

    @Test
    void findByContestId() {
        Long contestId = 1L;

        Wordle wordle1 = new Wordle();
        wordle1.setWord("Palabra1");
        Wordle wordle2 = new Wordle();
        wordle2.setWord("Palabra2");

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setWordles(List.of(wordle1, wordle2));

        when(wordleRepository.findByContestsId(contestId)).thenReturn(List.of(wordle1, wordle2));

        List<Wordle> resultList = wordleService.findByContestId(contestId);

        assertNotNull(resultList);
        assertEquals(resultList.size(), 2);
        assertEquals(wordle1, resultList.get(0));
        assertEquals(wordle2, resultList.get(1));
    }

    @Test
    void findByContestIdEmptyList() {
        Long contestId = 1L;

        List<Wordle> wordles = new ArrayList<>();

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setWordles(wordles);

        when(wordleRepository.findByContestsId(contestId)).thenReturn(wordles);

        List<Wordle> resultList = wordleService.findByContestId(contestId);

        assertNotNull(resultList);
        assertEquals(resultList.size(), 0);
    }

    @Test
    void findByProfessorId() {
        Long professorId = 1L;

        User professor = new User();
        professor.setId(professorId);

        Wordle wordle1 = new Wordle();
        wordle1.setWord("Palabra1");
        wordle1.setUser(professor);
        Wordle wordle2 = new Wordle();
        wordle2.setWord("Palabra2");
        wordle2.setUser(professor);

        when(wordleRepository.findByUserId(professorId)).thenReturn(List.of(wordle1, wordle2));

        List<Wordle> resultList = wordleService.findByProfessorId(professorId);

        assertNotNull(resultList);
        assertEquals(resultList.size(), 2);
        assertEquals(wordle1, resultList.get(0));
        assertEquals(wordle2, resultList.get(1));
    }

    @Test
    void findByProfessorIdEmptyList() {
        Long professorId = 1L;

        User professor = new User();
        professor.setId(professorId);

        when(wordleRepository.findByUserId(professorId)).thenReturn(new ArrayList<>());

        List<Wordle> resultList = wordleService.findByProfessorId(professorId);

        assertNotNull(resultList);
        assertEquals(resultList.size(), 0);
    }

    @Test
    void existsByWordExists() {
        String word = "Palabra";

        Wordle wordle = new Wordle();
        wordle.setWord(word);

        when(wordleRepository.findByWord(word)).thenReturn(Optional.of(wordle));

        assertTrue(wordleService.existsByWord(word));
    }

    @Test
    void existsByWordNotExists() {
        String word = "Palabra";

        when(wordleRepository.findByWord(word)).thenReturn(Optional.empty());

        assertFalse(wordleService.existsByWord(word));
    }

    @Test
    void existsByIdExists() {
        Long id = 1L;

        Wordle wordle = new Wordle();
        wordle.setId(id);

        when(wordleRepository.existsById(id)).thenReturn(true);

        assertTrue(wordleService.existsById(id));
    }
    @Test
    void existsByIdNotExists() {
        Long id = 1L;

        when(wordleRepository.existsById(id)).thenReturn(false);

        assertFalse(wordleService.existsById(id));
    }

    @Test
    void getByWord() {
        String word = "Palabra";

        Wordle wordle = new Wordle();
        wordle.setWord(word);

        when(wordleRepository.findByWord(word)).thenReturn(Optional.of(wordle));

        Wordle result = wordleService.getByWord(word);

        assertNotNull(result);
        assertEquals(wordle.getWord(), result.getWord());
    }

    @Test
    void save() {
        Wordle wordle = new Wordle();
        wordle.setWord("Palabra");

        wordleService.save(wordle);

        verify(wordleRepository, times(1)).save(wordle);
    }

    @Test
    void delete() {
        Long id = 1L;
        Wordle wordle = new Wordle();
        wordle.setId(id);

        wordleService.delete(wordle);

        verify(wordleRepository, times(1)).delete(wordle);
    }

    @Test
    void getById() {
        Long id = 1L;
        Wordle wordle = new Wordle();
        wordle.setId(id);

        when(wordleRepository.findById(id)).thenReturn(Optional.of(wordle));

        Wordle result = wordleService.getById(id);

        assertNotNull(result);
        assertEquals(wordle.getWord(), result.getWord());
    }
}