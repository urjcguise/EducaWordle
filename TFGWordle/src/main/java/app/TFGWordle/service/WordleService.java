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

    public List<Wordle> saveAll(List<Wordle> wordles) {
        return wordleRepository.saveAll(wordles);
    }

    public List<Wordle> findByContestId(Long id) {
        return wordleRepository.findByContestsId(id);
    }

    public List<Wordle> findByProfessorId(Long id) {
        return wordleRepository.findByUserId(id);
    }

    public boolean existsByWord(String word) {
        return wordleRepository.findByWord(word).isPresent();
    }

    public boolean existsById(Long id) {
        return wordleRepository.existsById(id);
    }

    public Wordle getByWord(String word) {
        return wordleRepository.findByWord(word).get();
    }

    public void save(Wordle wordle) {
        wordleRepository.save(wordle);
    }

    public void delete(Wordle wordle) {
        wordleRepository.delete(wordle);
    }

    public Wordle getById(Long id) {
        return wordleRepository.findById(id).get();
    }
}
