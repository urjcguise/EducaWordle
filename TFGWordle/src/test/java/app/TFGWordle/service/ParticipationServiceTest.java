package app.TFGWordle.service;

import app.TFGWordle.model.Participation;
import app.TFGWordle.repository.ParticipationRepository;
import app.TFGWordle.security.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipationServiceTest {

    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ParticipationService participationService;

    @Test
    void save() {
        Participation participation = new Participation();
        participationService.save(participation);
        verify(participationRepository, times(1)).save(participation);
    }

    @Test
    void findStudentsByCompetition() {
        Long competitionId = 1L;

        User user1 = new User();
        user1.setUsername("username1");
        User user2 = new User();
        user2.setUsername("username2");
        List<User> users = List.of(user1, user2);

        when(participationRepository.findByCompetitionId(competitionId)).thenReturn(users);

        List<User> resultList = participationService.findStudentsByCompetition(competitionId);

        assertNotNull(resultList);
        assertEquals(users.size(), resultList.size());
        assertEquals(user1.getId(), resultList.get(0).getId());
        assertEquals(user2.getId(), resultList.get(1).getId());
    }

    @Test
    void findStudentsByCompetitionEmptyList() {
        Long competitionId = 1L;

        when(participationRepository.findByCompetitionId(competitionId)).thenReturn(new ArrayList<>());

        List<User> resultList = participationService.findStudentsByCompetition(competitionId);

        assertNotNull(resultList);
        assertEquals(0, resultList.size());
    }

    @Test
    void findParticipationsByStudent() {
        Long studentId = 1L;

        Participation participation1 = new Participation();
        participation1.setId(1L);
        Participation participation2 = new Participation();
        participation2.setId(2L);
        List<Participation> participations = List.of(participation1, participation2);

        when(participationRepository.findByUserId(studentId)).thenReturn(participations);

        List<Participation> resultList = participationService.findParticipationsByStudent(studentId);

        assertNotNull(resultList);
        assertEquals(participations.size(), resultList.size());
        assertEquals(participation1.getId(), resultList.get(0).getId());
        assertEquals(participation2.getId(), resultList.get(1).getId());
    }

    @Test
    void findParticipationsByStudentEmptyList() {
        Long studentId = 1L;

        when(participationRepository.findByUserId(studentId)).thenReturn(new ArrayList<>());

        List<Participation> resultList = participationService.findParticipationsByStudent(studentId);

        assertNotNull(resultList);
        assertEquals(0, resultList.size());
    }
}