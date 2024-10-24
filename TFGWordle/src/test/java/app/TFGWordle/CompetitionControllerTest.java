package app.TFGWordle;

import app.TFGWordle.controller.CompetitionController;
import app.TFGWordle.model.Competition;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CompetitionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CompetitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompetitionService competitionService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private Competition competition;
    private User professor;

    @BeforeEach
    void setUp() {
        competition = new Competition();
        competition.setCompetitionName("Competition Test");

        professor = new User();
    }

    @Test
    @WithMockUser(username = "professor", roles = {"PROFESSOR"})
    public void testCreateCompetition_Success() throws Exception {
        when(userService.getByUserName("professor")).thenReturn(Optional.of(professor));
        when(competitionService.save(any(Competition.class), any(User.class))).thenReturn(competition);

        ResultActions resultActions = mockMvc.perform(post("/api/competitions/newCompetition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competition)));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "professor", roles = {"PROFESSOR"})
    public void testCreateCompetition_UserNotFound() throws Exception {
        when(userService.getByUserName("other")).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/competitions/newCompetition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(competition)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "professor")
    void testGetCompetitions_Success() throws Exception {
        when(userService.getByUserName("professor")).thenReturn(Optional.of(professor));
        when(competitionService.getCompetitionsByProfesor(professor)).thenReturn(List.of(competition));

        mockMvc.perform(get("/api/competitions/getCompetitions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].competitionName").value("Competition Test"));
    }

    @Test
    @WithMockUser(username = "professor")
    void testGetCompetitionsUserNotFound() throws Exception {
        when(userService.getByUserName("professor")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/competitions/getCompetitions")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    void testDeleteCompetition_Success() throws Exception {
        when(competitionService.existsCompetition(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/competitions/deleteCompetition/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Competición eliminada"));

        verify(competitionService, times(1)).deleteCompetition(1L);
    }

    @Test
    void testDeleteCompetition_UserNotFound() throws Exception {
        when(competitionService.existsCompetition(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/competitions/deleteCompetition/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Competición no encontrada"));

        verify(competitionService, never()).deleteCompetition(1L);
    }
}
