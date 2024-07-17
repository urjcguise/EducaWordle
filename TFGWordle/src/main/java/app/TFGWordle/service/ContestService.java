package app.TFGWordle.service;

import app.TFGWordle.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;
}
