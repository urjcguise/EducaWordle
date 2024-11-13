package app.TFGWordle;

import app.TFGWordle.controller.ContestController;
import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ContestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ContestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ContestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContestService contestService;

    @MockBean
    private CompetitionService competitionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Contest contest;
    private User professor;

    @BeforeEach
    void setUp() {
        contest = new Contest();
        contest.setContestName("Contest Test");

        professor = new User();
    }

    @Test
    @WithMockUser(roles = {"PROFESSOR"})
    void testCreateContestSuccess() throws Exception {

        Competition competition = new Competition();
        competition.setId(1L);
        when(competitionService.getCompetitionById(1L)).thenReturn(competition);
        contest.setId(1L);
        contest.setCompetition(competition);
        when(contestService.save(any(Contest.class))).thenReturn(contest);

        ResultActions resultActions = mockMvc.perform(post("/api/contests/newContest/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contest)));
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void testCreateContestCompetitionNotFound() throws Exception {
        when(competitionService.getCompetitionById(1L)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(post("/newContest/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void testGetContestsByCompetitionSuccess() throws Exception {

        Competition competition = new Competition();
        competition.setCompetitionName("Competition");
        when(competitionService.getCompetitionByName("Competition")).thenReturn(competition);

        Contest contest1 = new Contest();
        contest1.setId(1L);
        Contest contest2 = new Contest();
        contest2.setId(2L);
        List<Contest> mockContests = Arrays.asList(contest1, contest2);
        when(contestService.getContestsByCompetition(competition)).thenReturn(mockContests);

        mockMvc.perform(get("/api/contests/Competition/contests")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void testGetContestsByCompetitionNotFound() throws Exception {
        when(competitionService.getCompetitionByName("Competition")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/api/contests/Competition/contests")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void testDeleteCompetitionSuccess() throws Exception {
        when(contestService.existsContest("Contest")).thenReturn(true);

        Contest contest = new Contest();
        contest.setId(1L);
        when(contestService.getByName("Contest")).thenReturn(contest);

        doNothing().when(contestService).deleteContest(1L);

        mockMvc.perform(delete("/api/contests/deleteContest/Contest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Competición eliminada"));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void testDeleteCompetitionNotFound() throws Exception {
        Contest contest = new Contest();
        contest.setId(1L);
        when(contestService.existsContest("Contest")).thenReturn(false);

        mockMvc.perform(delete("/api/contests/deleteContest/Contest"))
                .andExpect(content().string("Concurso no encontrado"));
        verify(competitionService, never()).deleteCompetition(1L);
    }

}
