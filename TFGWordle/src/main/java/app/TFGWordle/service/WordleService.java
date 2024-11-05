package app.TFGWordle.service;

import app.TFGWordle.model.Wordle;
import app.TFGWordle.repository.WordleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WordleService {

    @Autowired
    private WordleRepository wordleRepository;

    public WordleService(WordleRepository wordleRepository) {
        this.wordleRepository = wordleRepository;
    }

    public void deleteByContestId(Long id) {
        wordleRepository.deleteByContestId(id);
    }

    public void saveAll(List<Wordle> wordles) {
        wordleRepository.saveAll(wordles);
    }
}
