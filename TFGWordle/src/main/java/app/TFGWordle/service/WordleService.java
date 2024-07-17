package app.TFGWordle.service;

import app.TFGWordle.repository.WordleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordleService {
    @Autowired
    private WordleRepository wordleRepository;
}
