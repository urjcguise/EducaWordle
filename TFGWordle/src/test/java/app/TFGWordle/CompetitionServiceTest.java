package app.TFGWordle;

import app.TFGWordle.model.Competition;
import app.TFGWordle.repository.CompetitionRepository;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.service.CompetitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionService competitionService;

    private User professor;
    private Competition competition;

    @BeforeEach
    void setUp() {

        Rol roleProfessor = new Rol();
        roleProfessor.setRolName(RolName.ROLE_PROFESSOR);

        professor = new User();
        Set<Rol> roles = new HashSet<>();
        roles.add(roleProfessor);
        professor.setRoles(roles);

        competition = new Competition();
        competition.setCompetitionName("Test Name");
        competition.setId(1L);
    }

    @Test
    void testSaveCompetitionByProfessor() {
        when(competitionRepository.save(competition)).thenReturn(competition);

        Competition savedCompetition = competitionService.save(competition, professor);

        assertNotNull(savedCompetition);
        assertEquals("Test Name", savedCompetition.getCompetitionName());
        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void testSaveCompetitionByNonProfessor() {
        User nonProfessor = new User();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            competitionService.save(competition, nonProfessor);
        });

        assertEquals("El usuario no tiene permisos para crear la competición", exception.getMessage());
        verify(competitionRepository, never()).save(competition);
    }

    @Test
    void testGetCompetitionByIdExists() {
        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));

        Competition foundCompetition = competitionService.getCompetitionById(1L);

        assertNotNull(foundCompetition);
        assertEquals(1L, foundCompetition.getId());
        verify(competitionRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCompetitionByIdNotExists() {
        when(competitionRepository.findById(1L)).thenReturn(Optional.empty());

        Competition foundCompetition = competitionService.getCompetitionById(1L);

        assertNull(foundCompetition);
        verify(competitionRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCompetitionByNameExisting() {
        when(competitionRepository.findByName("Test Name")).thenReturn(Optional.of(competition));

        Competition foundCompetition = competitionService.getCompetitionByName("Test Name");

        assertNotNull(foundCompetition);
        assertEquals("Test Name", foundCompetition.getCompetitionName());
    }

    @Test
    void testGetCompetitionByNameNotFound() {
        when(competitionRepository.findByName("Test Name Other")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            competitionService.getCompetitionByName("Test Name Other");
        });
    }

    @Test
    void testExistsCompetition() {
        when(competitionRepository.existsById(1L)).thenReturn(true);

        boolean exists = competitionService.existsCompetition(1L);

        assertTrue(exists);
        verify(competitionRepository, times(1)).existsById(1L);
    }

    @Test
    void testDeleteCompetition() {
        doNothing().when(competitionRepository).deleteById(1L);

        competitionService.deleteCompetition(1L);

        verify(competitionRepository, times(1)).deleteById(1L);
    }
}
