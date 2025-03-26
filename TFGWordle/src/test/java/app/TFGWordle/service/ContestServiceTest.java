package app.TFGWordle.service;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.repository.ContestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContestServiceTest {

    @Mock
    private ContestRepository contestRepository;

    @InjectMocks
    private ContestService contestService;

    @Test
    void saveContest() {
        Contest contest = new Contest();
        contestService.save(contest);
        verify(contestRepository, times(1)).save(contest);
    }

    @Test
    void deleteContest() {
        Long contestId = 1L;
        Contest contest = new Contest();
        contest.setId(contestId);

        contestService.deleteContest(contestId);

        verify(contestRepository, times(1)).deleteById(contestId);
    }

    @Test
    void getContestsByCompetition() {
        Competition competition = new Competition();
        Contest contest = new Contest();
        competition.setContests(List.of(contest));

        when(contestRepository.findByCompetition(competition)).thenReturn(Collections.singletonList(contest));

        List<Contest> contests = contestService.getContestsByCompetition(competition);

        assertEquals(1, contests.size());
        assertEquals(contest, contests.get(0));
    }

    @Test
    void existsByIdTrue() {
        Long contestId = 1L;
        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestRepository.existsById(contestId)).thenReturn(true);

        assertTrue(contestService.existsById(contestId));
    }

    @Test
    void existsByIdTFalse() {
        Long contestId = 1L;

        when(contestRepository.existsById(contestId)).thenReturn(false);

        assertFalse(contestService.existsById(contestId));
    }

    @Test
    void getById() {
        Long contestId = 1L;
        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestRepository.findById(contestId)).thenReturn(Optional.of(contest));

        Contest result = contestService.getById(contestId);

        assertEquals(contest.getId(), result.getId());
    }
}