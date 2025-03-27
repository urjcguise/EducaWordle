package app.TFGWordle.service;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Dictionary;
import app.TFGWordle.model.DictionaryExternal;
import app.TFGWordle.repository.DictionaryExternalRepository;
import app.TFGWordle.repository.DictionaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DictionaryServiceTest {

    @Mock
    private DictionaryRepository dictionaryRepository;

    @Mock
    private DictionaryExternalRepository dictionaryExternalRepository;

    @InjectMocks
    private DictionaryService dictionaryService;

    @Test
    void saveGlobalDictionary() {
        Dictionary dictionary = new Dictionary();
        dictionaryService.saveGlobalDictionary(dictionary);
        verify(dictionaryRepository, times(1)).save(dictionary);
    }

    @Test
    void existsInGlobalDictionaryExists() {
        String word = "word";

        when(dictionaryRepository.existsWordle(word)).thenReturn(true);

        assertTrue(dictionaryService.existsInGlobalDictionary(word));
    }

    @Test
    void existsInGlobalDictionaryNotExists() {
        String word = "word";

        when(dictionaryRepository.existsWordle(word)).thenReturn(false);

        assertFalse(dictionaryService.existsInGlobalDictionary(word));
    }

    @Test
    void saveExternal() {
        DictionaryExternal dictionaryExternal1 = new DictionaryExternal();
        dictionaryExternal1.setWordle("word1");
        DictionaryExternal dictionaryExternal2 = new DictionaryExternal();
        dictionaryExternal2.setWordle("word2");
        List<DictionaryExternal> dictionaryExternalList = List.of(dictionaryExternal1, dictionaryExternal2);

        when(dictionaryExternalRepository.saveAll(anyList())).thenReturn(dictionaryExternalList);

        List<DictionaryExternal> result = dictionaryService.saveExternal(dictionaryExternalList);

        assertNotNull(result);
        assertEquals(dictionaryExternalList.size(), result.size());
        assertEquals(dictionaryExternalList.get(0).getWordle(), result.get(0).getWordle());
        assertEquals(dictionaryExternalList.get(1).getWordle(), result.get(1).getWordle());
    }

    @Test
    void existsInExternalDictionaryExists() {
        String word = "word";
        Long contestId = 1L;

        when(dictionaryExternalRepository.existsWord(word, contestId)).thenReturn(true);

        assertTrue(dictionaryService.existsInExternalDictionary(word, contestId));
    }

    @Test
    void existsInExternalDictionaryNotExists() {
        String word = "word";
        Long contestId = 1L;

        when(dictionaryExternalRepository.existsWord(word, contestId)).thenReturn(false);

        assertFalse(dictionaryService.existsInExternalDictionary(word, contestId));
    }

    @Test
    void deleteWordsByContest() {
        Long contestId = 1L;
        Contest contest = new Contest();
        contest.setId(contestId);
        dictionaryService.deleteWordsByContest(contest);
        verify(dictionaryExternalRepository, times(1)).deleteByContest(contest);
    }
}