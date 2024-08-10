package app.TFGWordle.service;

import app.TFGWordle.model.Wordle;
import app.TFGWordle.repository.WordleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInitializer {

    @Autowired
    private WordleRepository wordleRepository;

    @PostConstruct
    public void init() {
        Wordle wordle = new Wordle("Agile");
        wordleRepository.save(wordle);
    }
}
