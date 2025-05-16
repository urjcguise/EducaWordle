package app.TFGWordle.service;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Dictionary;
import app.TFGWordle.model.DictionaryExternal;
import app.TFGWordle.repository.DictionaryExternalRepository;
import app.TFGWordle.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    private DictionaryRepository dictionaryRepository;
    private DictionaryExternalRepository externalRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository,
            DictionaryExternalRepository externalRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.externalRepository = externalRepository;
    }

    public void saveGlobalDictionary(Dictionary dictionary) {
        this.dictionaryRepository.save(dictionary);
    }

    public Boolean existsInGlobalDictionary(String word) {
        return dictionaryRepository.existsWordle(word);
    }

    public List<DictionaryExternal> saveExternal(List<DictionaryExternal> dictionary) {
        return externalRepository.saveAll(dictionary);
    }

    public Boolean existsInExternalDictionary(String wordle, Long contestId) {
        return externalRepository.existsWord(wordle, contestId);
    }

    public void deleteWordsByContest(Contest contest) {
        externalRepository.deleteByContest(contest);
    }
}
