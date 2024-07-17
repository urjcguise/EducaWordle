package app.TFGWordle.service;

import app.TFGWordle.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;
}
