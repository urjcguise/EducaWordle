package app.TFGWordle.service;

import app.TFGWordle.model.Wordle;
import app.TFGWordle.repository.WordleRepository;
import app.TFGWordle.security.service.RolService;
import app.TFGWordle.security.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInitializer {

    @Autowired
    private WordleRepository wordleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;

    @PostConstruct
    public void init() {
        if (wordleRepository.count() == 0) {
            Wordle wordle = new Wordle("Agile");
            wordleRepository.save(wordle);
        }
    }
}
