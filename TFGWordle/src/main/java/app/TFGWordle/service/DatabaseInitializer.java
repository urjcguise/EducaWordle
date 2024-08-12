package app.TFGWordle.service;

import app.TFGWordle.model.User;
import app.TFGWordle.model.Wordle;
import app.TFGWordle.repository.UserRepository;
import app.TFGWordle.repository.WordleRepository;
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
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (wordleRepository.count() == 0) {
            Wordle wordle = new Wordle("Agile");
            wordleRepository.save(wordle);

            User admin = new User("user@gmail.com", passwordEncoder.encode("pass"), "ADMIN");
            userRepository.save(admin);
        }

    }
}
