package app.TFGWordle.service;

import app.TFGWordle.model.Wordle;
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

    @PostConstruct
    public void init() {
        if (wordleRepository.count() == 0) {
            Wordle wordle = new Wordle("Agile");
            wordleRepository.save(wordle);

//            User admin = new User("Nombre Admin","user@gmail.com", passwordEncoder.encode("pass"), "ADMIN");
//            User user2 = new User();
//            user2.setUsername("Usuario 2");
//            user2.setEmail("user2@gmail.com");
//            user2.setPassword(passwordEncoder.encode("1234"));
//            user2.setRol("STUDENT");
//            userRepository.save(user2);
//            userRepository.save(admin);
        }

    }
}
