package app.TFGWordle.controller;

import app.TFGWordle.security.entity.User;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Wordle;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.ContestStateService;
import app.TFGWordle.service.FolderService;
import app.TFGWordle.service.WordleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
class WordleControllerTest {

    private final String BASE_PATH = "/api/wordle";

    private final WordleService wordleService = mock(WordleService.class);

    private final ContestService contestService = mock(ContestService.class);

    private final UserService userService = mock(UserService.class);

    private final FolderService folderService = mock(FolderService.class);

    private final ContestStateService contestStateService = mock(ContestStateService.class);

    private final WordleController wordleController = new WordleController(wordleService, contestService, userService, folderService, contestStateService);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(wordleController).build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void createWordlesSuccess() throws Exception {
        Long contestId = 1L;
        Long folderId = 1L;
        String professorName = "Profesor";
        User professor = new User();
        professor.setUsername(professorName);

        Contest contest = new Contest();
        contest.setId(contestId);

        List<String> wordles = Arrays.asList("word1", "word2");
        List<Wordle> savedWordles = Arrays.asList(new Wordle(), new Wordle());

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(folderService.existsById(folderId)).thenReturn(true);
        when(userService.getByUserName(professorName)).thenReturn(Optional.of(professor));
        when(wordleService.saveAll(anyList())).thenReturn(savedWordles);
        when(contestService.save(any())).thenReturn(contest);

        mockMvc.perform(post(BASE_PATH + "/newWordles/" + contestId + "/" + professorName + "/" + folderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(roles = {"PROFESSOR"})
    void newWordles_ContestNotFound() throws Exception {
        Long contestId = 1L;
        Long folderId = 2L;
        String professorName = "professor123";
        List<String> wordles = Arrays.asList("word1", "word2");

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/newWordles/" + contestId + "/" + professorName + "/" + folderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());

        verify(contestService).existsById(contestId);
        verifyNoInteractions(folderService, userService, wordleService);
    }

    @Test
    @WithMockUser(roles = {"PROFESSOR"})
    void newWordles_FolderNotFound() throws Exception {
        Long contestId = 1L;
        Long folderId = 2L;
        String professorName = "professor123";
        List<String> wordles = Arrays.asList("word1", "word2");

        when(contestService.existsById(contestId)).thenReturn(true);
        when(folderService.existsById(folderId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/newWordles/" + contestId + "/" + professorName + "/" + folderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());

        verify(contestService).existsById(contestId);
        verify(folderService).existsById(folderId);
        verifyNoInteractions(userService, wordleService);
    }

    @Test
    @WithMockUser(roles = {"PROFESSOR"})
    void newWordles_ProfessorNotFound() throws Exception {
        Long contestId = 1L;
        Long folderId = 2L;
        String professorName = "professor123";
        List<String> wordles = Arrays.asList("word1", "word2");

        when(contestService.existsById(contestId)).thenReturn(true);
        when(folderService.existsById(folderId)).thenReturn(true);
        when(userService.getByUserName(professorName)).thenReturn(Optional.empty());

        mockMvc.perform(post(BASE_PATH + "/newWordles/" + contestId + "/" + professorName + "/" + folderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());

        verify(contestService).existsById(contestId);
        verify(folderService).existsById(folderId);
        verify(userService).getByUserName(professorName);
        verifyNoInteractions(wordleService);
    }

}