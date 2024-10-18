package app.TFGWordle.service;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.repository.ContestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestService {

    private ContestRepository contestRepository;

    public ContestService(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public Contest save(Contest contest) {
        return contestRepository.save(contest);
    }

    public List<Contest> getContestsByCompetition(Competition competition) {
        return contestRepository.findByCompetition(competition);
    }
}
