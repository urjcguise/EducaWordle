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

    public void deleteContest(Long id) {
        contestRepository.deleteById(id);
    }

    public List<Contest> getContestsByCompetition(Competition competition) {
        return contestRepository.findByCompetition(competition);
    }

    public boolean existsById(Long id) {
        return contestRepository.existsById(id);
    }

    public Contest getById(Long id) {
        return contestRepository.findById(id).get();
    }
}
