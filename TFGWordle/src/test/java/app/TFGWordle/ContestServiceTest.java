package app.TFGWordle;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Competition;
import app.TFGWordle.repository.ContestRepository;
import app.TFGWordle.service.ContestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContestServiceTest {

    @Mock
    private ContestRepository contestRepository;

    @InjectMocks
    private ContestService contestService;

    private Contest contest;
    private Competition competition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        contest = new Contest();
        contest.setId(1L);
        contest.setContestName("Test Contest");

        competition = new Competition();
        competition.setId(1L);
        competition.setCompetitionName("Test Competition");
    }

    @Test
    void testSaveContest() {
        when(contestRepository.save(contest)).thenReturn(contest);

        Contest savedContest = contestService.save(contest);

        assertNotNull(savedContest);
        assertEquals("Test Contest", savedContest.getContestName());
        verify(contestRepository, times(1)).save(contest);
    }

    @Test
    void testGetContestsByCompetition() {
        List<Contest> contests = List.of(contest);
        when(contestRepository.findByCompetition(competition)).thenReturn(contests);

        List<Contest> result = contestService.getContestsByCompetition(competition);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(contest.getContestName(), result.get(0).getContestName());
        verify(contestRepository, times(1)).findByCompetition(competition);
    }

    @Test
    void testExistsContestWhenPresent() {
        when(contestRepository.findByName("Test Contest")).thenReturn(Optional.of(contest));

        Boolean exists = contestService.existsContest("Test Contest");

        assertTrue(exists);
        verify(contestRepository, times(1)).findByName("Test Contest");
    }

    @Test
    void testExistsContestWhenNotPresent() {
        when(contestRepository.findByName("NonExistent")).thenReturn(Optional.empty());

        Boolean exists = contestService.existsContest("NonExistent");

        assertFalse(exists);
        verify(contestRepository, times(1)).findByName("NonExistent");
    }

    @Test
    void testGetByNameWhenPresent() {
        when(contestRepository.findByName("Test Contest")).thenReturn(Optional.of(contest));

        Contest foundContest = contestService.getByName("Test Contest");

        assertNotNull(foundContest);
        assertEquals("Test Contest", foundContest.getContestName());
        verify(contestRepository, times(1)).findByName("Test Contest");
    }

    @Test
    void testGetByNameWhenNotPresentThrowsException() {
        when(contestRepository.findByName("NonExistent")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                contestService.getByName("NonExistent")
        );

        verify(contestRepository, times(1)).findByName("NonExistent");
    }

    @Test
    void testDeleteCompetition() {
        contestService.deleteContest(1L);

        verify(contestRepository, times(1)).deleteById(1L);
    }
}
