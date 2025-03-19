package app.TFGWordle.service;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Participation;
import app.TFGWordle.repository.CompetitionRepository;
import app.TFGWordle.security.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionService competitionService;

    @Test
    void saveCompetition() {
        Competition competition = new Competition();
        competitionService.save(competition);
        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void getCompetitionByProfessor() {
        User professor = new User("user", "user@gmail.com", "password");
        Competition competition = new Competition();

        when(competitionRepository.findByProfessor(professor)).thenReturn(Collections.singletonList(competition));

        List<Competition> result = competitionService.getCompetitionsByProfesor(professor);

        assertEquals(1, result.size());
        assertEquals(competition, result.get(0));
    }

    @Test
    void getCompetitionByNameSuccess() {
        String competitionName = "test";
        Competition competition = new Competition();
        competition.setCompetitionName(competitionName);

        when(competitionRepository.findByName(competitionName)).thenReturn(Optional.of(competition));

        Competition result = competitionService.getCompetitionByName(competitionName);

        assertEquals(competition.getCompetitionName(), result.getCompetitionName());
    }

    @Test
    void getCompetitionByNameNotFound() {
        String competitionName = "test";

        when(competitionRepository.findByName(competitionName)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> competitionService.getCompetitionByName(competitionName));

        assertEquals("Competicion con nombre: test no existe", exception.getMessage());
    }

    @Test
    void getCompetitionByIdSuccess() {
        Long id = 1L;
        Competition competition = new Competition();
        competition.setId(id);

        when(competitionRepository.findById(id)).thenReturn(Optional.of(competition));

        Competition result = competitionService.getCompetitionById(id);

        assertEquals(competition.getCompetitionName(), result.getCompetitionName());
    }

    @Test
    void getCompetitionByIdNotFound() {
        Long id = 1L;

        when(competitionRepository.findById(id)).thenReturn(Optional.empty());

        Competition result = competitionService.getCompetitionById(id);

        assertNull(result);
    }

    @Test
    void deleteCompetition() {
        Long id = 1L;
        Competition competition = new Competition();
        competition.setId(id);

        competitionService.deleteCompetition(competition.getId());

        verify(competitionRepository, times(1)).deleteById(id);
    }

    @Test
    void existsCompetitionTrue() {
        Long id = 1L;
        Competition competition = new Competition();
        competition.setId(id);

        when(competitionRepository.existsById(id)).thenReturn(true);

        assertTrue(competitionService.existsCompetition(id));
    }

    @Test
    void existsCompetitionFalse() {
        Long id = 1L;

        when(competitionRepository.existsById(id)).thenReturn(false);

        assertFalse(competitionService.existsCompetition(id));
    }

    @Test
    void existsCompetitionByNameTrue() {
        String name = "test";
        Competition competition = new Competition();
        competition.setCompetitionName(name);

        when(competitionRepository.existsByName(name)).thenReturn(true);

        assertTrue(competitionService.existsCompetitionByName(name));
    }

    @Test
    void existsCompetitionByNameFalse() {
        String name = "test";

        when(competitionRepository.existsByName(name)).thenReturn(false);

        assertFalse(competitionService.existsCompetitionByName(name));
    }

    @Test
    void getParticipations() {
        Long id = 1L;
        Competition competition = new Competition();
        competition.setId(id);
        Participation participation = new Participation();
        participation.setId(id);
        competition.setParticipations(new HashSet<>(List.of(participation)));

        when(competitionRepository.getParticipationsById(id)).thenReturn(List.of(participation));
        List<Participation> result = competitionService.getParticipations(id);

        assertEquals(1, result.size());
        assertEquals(participation.getId(), result.get(0).getId());
    }
}