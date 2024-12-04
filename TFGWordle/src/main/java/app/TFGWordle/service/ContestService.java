package app.TFGWordle.service;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.repository.ContestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Boolean existsContest(String contestName) {
        return contestRepository.findByName(contestName).isPresent();
    }

    public Contest getByName(String contestName) {
        return contestRepository.findByName(contestName).get();
    }

    public Optional<Contest> getById(Long id) {
        return contestRepository.findById(id);
    }

    public void deleteContest(Long id) {
        contestRepository.deleteById(id);
    }

    public Boolean existById(Long id) {
        return contestRepository.existsById(id);
    }
}
