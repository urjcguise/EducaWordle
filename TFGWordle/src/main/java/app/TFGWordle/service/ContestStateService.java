package app.TFGWordle.service;

import app.TFGWordle.dto.WordleState;
import app.TFGWordle.model.ContestState;
import app.TFGWordle.repository.ContestStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestStateService {

    @Autowired
    private ContestStateRepository contestStateRepository;

    public ContestState save(ContestState contestState) {
        return contestStateRepository.save(contestState);
    }

    public Boolean existsState(Long contestId, Long userId) {
        return contestStateRepository.existsByContestIdAndUserId(contestId, userId);
    }

    public WordleState getState(Long contestId, Long userId) {
        return contestStateRepository.getState(contestId, userId);
    }

    public ContestState getContestState(Long contestId, Long userId) {
        return contestStateRepository.findByContestIdAndUserId(contestId, userId);
    }

    public List<ContestState> getByContest(Long contestId) {
        return contestStateRepository.findByContestId(contestId);
    }
}
