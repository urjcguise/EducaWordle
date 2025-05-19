package app.TFGWordle.controller;

import app.TFGWordle.dto.WordleState;
import app.TFGWordle.dto.WordleStateLog;
import app.TFGWordle.model.*;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ContestControllerTest {

    private final String BASE_PATH = "/api/contests";

    private final ContestService contestService = mock(ContestService.class);

    private final CompetitionService competitionService = mock(CompetitionService.class);

    private final UserService userService = mock(UserService.class);

    private final WordleService wordleService = mock(WordleService.class);

    private final ContestStateService contestStateService = mock(ContestStateService.class);

    private final DictionaryService dictionaryService = mock(DictionaryService.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ContestController contestController = new ContestController(contestService, competitionService, userService, wordleService, contestStateService, dictionaryService, objectMapper);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(contestController).build();

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void createContestSuccess() throws Exception {
        Long id = 1L;
        Competition competition = new Competition();
        competition.setId(id);
        when(competitionService.existsCompetition(id)).thenReturn(true);
        when(competitionService.getCompetitionById(id)).thenReturn(competition);
        when(competitionService.save(competition)).thenReturn(competition);

        Contest contest = new Contest();
        contest.setId(id);

        when(contestService.save(contest)).thenReturn(contest);

        mockMvc.perform(post(BASE_PATH + "/newContest/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void createContestNoCompetition() throws Exception {
        Long id = 1L;
        Contest contest = new Contest();

        when(competitionService.existsCompetition(id)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/newContest/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contest)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void createContestConflictContest() throws Exception {
        Long id = 1L;
        Competition competition = new Competition();
        competition.setId(id);
        Contest contest = new Contest();

        competition.getContests().add(contest);

        when(competitionService.existsCompetition(id)).thenReturn(true);
        when(competitionService.getCompetitionById(id)).thenReturn(competition);

        mockMvc.perform(post(BASE_PATH + "/newContest/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contest)))
                .andExpect(status().isConflict());
    }

    @Test
    void getContestsByCompetitionSuccess() throws Exception {
        Long competitionId = 1L;
        Competition competition = new Competition();
        List<Contest> contests = List.of(new Contest());
        competition.setId(competitionId);
        competition.setContests(contests);

        when(competitionService.existsCompetition(competitionId)).thenReturn(true);
        when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);
        when(contestService.getContestsByCompetition(competition)).thenReturn(contests);

        mockMvc.perform(get(BASE_PATH + '/' + competitionId + "/contests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(contests.size()));
    }

    @Test
    void getContestsByCompetitionNotFound() throws Exception {
        Long competitionId = 1L;
        when(competitionService.existsCompetition(competitionId)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + '/' + competitionId + "/contests"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteContestSuccess() throws Exception {
        Long id = 1L;

        Contest contest = new Contest();
        contest.setId(id);
        contest.setUseExternalFile(true);

        List<ContestState> contestStates = new ArrayList<>();
        List<ContestStateLog> contestStateLogs = new ArrayList<>();
        contestStates.add(new ContestState());
        contestStateLogs.add(new ContestStateLog());

        when(contestService.existsById(id)).thenReturn(true);
        when(contestService.getById(id)).thenReturn(contest);
        when(contestStateService.existsByContest(id)).thenReturn(true);
        when(contestStateService.getAllByContest(id)).thenReturn(contestStates);
        when(contestStateService.getLogsByContestId(id)).thenReturn(contestStateLogs);

        doNothing().when(dictionaryService).deleteWordsByContest(contest);
        doNothing().when(contestStateService).deleteById(anyLong());
        doNothing().when(contestStateService).deleteByLogId(anyLong());
        doNothing().when(contestService).deleteContest(id);

        mockMvc.perform(delete(BASE_PATH + "/deleteContest/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteContestNotFound() throws Exception {
        Long id = 1L;

        when(contestService.existsById(id)).thenReturn(false);

        mockMvc.perform(delete(BASE_PATH + "/deleteContest/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editContestSuccess() throws Exception {
        Long contestId = 1L;

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);

        mockMvc.perform(post(BASE_PATH + "/editContest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contest)))
                .andExpect(status().isOk());

        verify(contestService, times(1)).save(contest);
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editContestNotFound() throws Exception {
        Long contestId = 1L;

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/editContest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contest)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editRandomModeSuccess() throws Exception {
        Long contestId = 1L;
        Boolean newMode = true;

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setRandomMode(false);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);

        mockMvc.perform(post(BASE_PATH + "/editRandomMode/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMode)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(newMode)));

        verify(contestService, times(1)).save(contest);
        assertTrue(contest.getRandomMode());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editRandomModeNotFound() throws Exception {
        Long contestId = 1L;
        Boolean newMode = true;

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setRandomMode(false);

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/editRandomMode/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMode)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editAccentModeSuccess() throws Exception {
        Long contestId = 1L;
        Boolean newMode = true;

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setAccentMode(false);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);

        mockMvc.perform(post(BASE_PATH + "/editAccentMode/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMode)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(newMode)));

        verify(contestService, times(1)).save(contest);
        assertTrue(contest.getAccentMode());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editAccentModeNotFound() throws Exception {
        Long contestId = 1L;
        Boolean newMode = true;

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setAccentMode(false);

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/editAccentMode/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMode)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteWordlesInContestSuccess() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        Contest contest = new Contest();
        contest.setId(contestId);

        Wordle wordle1 = new Wordle();
        wordle1.setWord(wordles.get(0));
        wordle1.setContests(new ArrayList<>(List.of(contest)));
        Wordle wordle2 = new Wordle();
        wordle2.setWord(wordles.get(1));
        wordle2.setContests(new ArrayList<>(List.of(contest)));

        contest.setWordles(new ArrayList<>(List.of(wordle1, wordle2)));

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(wordleService.existsByWord("Wordle1")).thenReturn(true);
        when(wordleService.getByWord("Wordle1")).thenReturn(wordle1);
        when(wordleService.existsByWord("Wordle2")).thenReturn(true);
        when(wordleService.getByWord("Wordle2")).thenReturn(wordle2);

        mockMvc.perform(post(BASE_PATH + "/deleteWordlesInContest/" + contestId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isOk());

        verify(contestService, times(1)).save(contest);
        verify(wordleService, times(2)).save(wordle1);
        assertEquals(contest.getWordles().size(), 0);
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteWordlesInContestContestNotFound() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/deleteWordlesInContest/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteWordlesInContestWordleNotFound() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);

        when(wordleService.existsByWord("Wordle1")).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/deleteWordlesInContest/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void addWordlesInContestSuccess() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        Contest contest = new Contest();
        contest.setId(contestId);

        Wordle wordle1 = new Wordle();
        wordle1.setWord(wordles.get(0));
        wordle1.setContests(new ArrayList<>());
        Wordle wordle2 = new Wordle();
        wordle2.setWord(wordles.get(1));
        wordle2.setContests(new ArrayList<>());

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(wordleService.existsByWord("Wordle1")).thenReturn(true);
        when(wordleService.getByWord("Wordle1")).thenReturn(wordle1);
        when(wordleService.existsByWord("Wordle2")).thenReturn(true);
        when(wordleService.getByWord("Wordle2")).thenReturn(wordle2);

        mockMvc.perform(post(BASE_PATH + "/addWordlesToContest/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isOk());

        verify(contestService, times(1)).save(contest);
        verify(wordleService, times(2)).save(wordle1);
        assertEquals(contest.getWordles().size(), 2);
        assertEquals(wordle1.getContests().size(), 1);
        assertEquals(wordle2.getContests().size(), 1);
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void addWordlesInContestContestNotFound() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/addWordlesToContest/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void addWordlesInContestWordleNotFound() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);

        when(wordleService.existsByWord("Wordle1")).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/addWordlesToContest/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void addWordlesInContestWordleConflict() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle");

        Contest contest = new Contest();
        contest.setId(contestId);

        Wordle wordle = new Wordle();
        wordle.setWord(wordles.get(0));
        wordle.setContests(new ArrayList<>(List.of(contest)));

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(wordleService.existsByWord("Wordle")).thenReturn(true);
        when(wordleService.getByWord("Wordle")).thenReturn(wordle);

        mockMvc.perform(post(BASE_PATH + "/addWordlesToContest/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void changeWordlesPositionSuccess() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        Contest contest = new Contest();
        contest.setId(contestId);

        Wordle wordle1 = new Wordle();
        wordle1.setWord(wordles.get(0));
        Wordle wordle2 = new Wordle();
        wordle2.setWord(wordles.get(1));

        contest.setWordles(new ArrayList<>(List.of(wordle2, wordle1)));

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(wordleService.existsByWord("Wordle1")).thenReturn(true);
        when(wordleService.getByWord("Wordle1")).thenReturn(wordle1);
        when(wordleService.existsByWord("Wordle2")).thenReturn(true);
        when(wordleService.getByWord("Wordle2")).thenReturn(wordle2);

        mockMvc.perform(post(BASE_PATH + "/changeWordlesPosition/" + contestId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isOk());

        verify(contestService, times(1)).save(contest);
        assertEquals(contest.getWordles().size(), 2);
        assertEquals(contest.getWordles().get(0).getWord(), wordle1.getWord());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void changeWordlesPositionContestNotFound() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/changeWordlesPosition/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void changeWordlesPositionWordleNotfound() throws Exception {
        Long contestId = 1L;
        List<String> wordles = List.of("Wordle1", "Wordle2");

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(wordleService.existsByWord("Wordle1")).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/changeWordlesPosition/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordles)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getContestByIdSuccess() throws Exception {
        Long id = 1L;

        Contest contest = new Contest();
        contest.setId(id);

        when(contestService.existsById(id)).thenReturn(true);
        when(contestService.getById(id)).thenReturn(contest);

        mockMvc.perform(get(BASE_PATH + "/" + id + "/contest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void getContestByIdNotFound() throws Exception {
        Long id = 1L;

        when(contestService.existsById(id)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/" + id + "/contest"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void copyContestSuccess() throws Exception {
        Long oldContestId = 1L;

        Contest oldContest = new Contest();
        oldContest.setId(oldContestId);
        oldContest.setContestName("ConcursoOriginal");
        Competition competition = new Competition();
        competition.setId(1L);
        oldContest.setCompetition(competition);
        oldContest.setNumTries(5);
        oldContest.setUseDictionary(true);
        oldContest.setUseExternalFile(false);

        List<Wordle> wordles = new ArrayList<>();
        wordles.add(new Wordle());
        wordles.add(new Wordle());
        oldContest.setWordles(wordles);

        when(contestService.existsById(oldContestId)).thenReturn(true);
        when(contestService.getById(oldContestId)).thenReturn(oldContest);
        when(contestService.save(any(Contest.class))).thenReturn(oldContest);

        mockMvc.perform(post(BASE_PATH + "/copyContest/" + oldContestId))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void copyContestNotFound() throws Exception {
        Long oldContestId = 1L;

        when(contestService.existsById(oldContestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/copyContest/" + oldContestId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createContestStateSuccess() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setAccentMode(false);

        User user = new User();
        user.setId(100L);
        user.setUsername(userName);

        List<Wordle> wordles = Arrays.asList(new Wordle("test1", List.of(contest), user), new Wordle("test2", List.of(contest), user));
        contest.setWordles(wordles);

        WordleState wordleState = new WordleState();
        JsonNode jsonState = objectMapper.valueToTree(wordleState);

        ContestState newContestState = new ContestState(contest, user);
        newContestState.setState(jsonState);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.existsState(contestId, user.getId())).thenReturn(false);
        when(contestStateService.save(any(ContestState.class))).thenReturn(newContestState);

        mockMvc.perform(post(BASE_PATH + "/newContestState/" + contestId + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordleState)))
                .andExpect(status().isCreated());
    }

    @Test
    void createContestStateContestNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        WordleState wordleState = new WordleState();

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/newContestState/" + contestId + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordleState)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createContestStateUserNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";
        WordleState wordleState = new WordleState();

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(null);

        mockMvc.perform(post(BASE_PATH + "/newContestState/" + contestId + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordleState)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createContestStateConflict() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";
        WordleState wordleState = new WordleState();

        Contest contest = new Contest();
        contest.setId(contestId);
        contest.setAccentMode(true);

        User user = new User();
        user.setUsername(userName);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.existsState(contestId, user.getId())).thenReturn(true);

        mockMvc.perform(post(BASE_PATH + "/newContestState/" + contestId + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordleState)))
                .andExpect(status().isConflict());
    }

    @Test
    void getContestStateSuccess() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        User user = new User();
        user.setId(100L);
        user.setUsername(userName);

        Contest contest = new Contest();
        contest.setId(contestId);

        WordleState wordleState = new WordleState();

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.existsState(contestId, user.getId())).thenReturn(true);
        when(contestStateService.getState(contestId, user.getId())).thenReturn(wordleState);

        mockMvc.perform(get(BASE_PATH + "/getContestState/" + contestId + "/" + userName))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(wordleState)));

    }

    @Test
    void getContestStateContestNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/getContestState/" + contestId + "/" + userName))
                .andExpect(status().isNotFound());
    }

    @Test
    void getContestStateUserNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_PATH + "/getContestState/" + contestId + "/" + userName))
                .andExpect(status().isNotFound());
    }

    @Test
    void getContestStateContestStateNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        User user = new User();
        Contest contest = new Contest();

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.existsState(contestId, user.getId())).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/getContestState/" + contestId + "/" + userName))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateContestStateSuccess() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        User user = new User();
        user.setId(100L);
        user.setUsername(userName);

        Contest contest = new Contest();
        contest.setId(contestId);

        WordleState wordleState = new WordleState();
        JsonNode jsonState = objectMapper.valueToTree(wordleState);

        ContestState contestState = new ContestState(contest, user);
        contestState.setState(jsonState);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.getContestState(contestId, user.getId())).thenReturn(contestState);
        when(contestStateService.save(contestState)).thenReturn(contestState);

        mockMvc.perform(post(BASE_PATH + "/updateContestState/" + contestId + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordleState)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(contestState))); // Verificamos el contenido

    }

    @Test
    void updateContestStateContestNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";
        WordleState wordleState = new WordleState();

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/updateContestState/" + contestId + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordleState)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateContestStateUserNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";
        WordleState wordleState = new WordleState();

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.empty());

        mockMvc.perform(post(BASE_PATH + "/updateContestState/" + contestId + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wordleState)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllContestStateSuccess() throws Exception {
        Long contestId = 1L;

        Contest contest = new Contest();
        contest.setId(contestId);

        User user1 = new User();
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setEmail("user2@example.com");

        JsonNode state1 = mock(JsonNode.class);
        JsonNode state2 = mock(JsonNode.class);

        ContestState contestState1 = new ContestState(contest, user1);
        contestState1.setState(state1);

        ContestState contestState2 = new ContestState(contest, user2);
        contestState2.setState(state2);

        List<ContestState> contestStates = Arrays.asList(contestState1, contestState2);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.getAllByContest(contestId)).thenReturn(contestStates);

        mockMvc.perform(get(BASE_PATH + "/getAllContestState/" + contestId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[1].email").value("user2@example.com"));

    }

    @Test
    void getAllContestStateContestNotFound() throws Exception {
        Long contestId = 1L;

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/getAllContestState/" + contestId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createContestLogSuccess() throws Exception {
        Long contestId = 1L;
        int wordlePosition = 0;
        String userName = "studentUser";

        Contest contest = new Contest();
        contest.setId(contestId);

        User user = new User();
        user.setUsername(userName);

        Wordle wordle = new Wordle();
        wordle.setWord("testword");
        contest.setWordles(Collections.singletonList(wordle));

        WordleStateLog wordleStateLog = new WordleStateLog();
        wordleStateLog.setWordleToGuess("testword");

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);

        mockMvc.perform(post(BASE_PATH + "/createContestLog/" + contestId + "/" + wordlePosition + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(wordleStateLog)))
                .andExpect(status().isCreated());

    }

    @Test
    void createContestLogContestNotFound() throws Exception {
        Long contestId = 1L;
        int wordlePosition = 0;
        String userName = "studentUser";
        WordleStateLog wordleStateLog = new WordleStateLog();

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/createContestLog/" + contestId + "/" + wordlePosition + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(wordleStateLog)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createContestLogUserNotFound() throws Exception {
        Long contestId = 1L;
        int wordlePosition = 0;
        String userName = "studentUser";
        WordleStateLog wordleStateLog = new WordleStateLog();

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.empty());

        mockMvc.perform(post(BASE_PATH + "/createContestLog/" + contestId + "/" + wordlePosition + "/" + userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(wordleStateLog)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getAllUserContestStateLogsSuccess() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        Contest contest = new Contest();
        contest.setId(contestId);

        User user = new User();
        user.setUsername(userName);
        user.setId(10L);

        List<String> contestLogsIds = Arrays.asList("log1", "log2");
        WordleStateLog log1 = new WordleStateLog();
        WordleStateLog log2 = new WordleStateLog();

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.getAllLogsByContestAndUser(contestId, user.getId())).thenReturn(contestLogsIds);
        when(contestStateService.getStateLog("log1")).thenReturn(log1);
        when(contestStateService.getStateLog("log2")).thenReturn(log2);

        mockMvc.perform(get(BASE_PATH + "/getAllUserContestStateLogs/" + contestId + "/" + userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // Verifica que hay dos logs

    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getAllUserContestStateLogsContestNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/getAllUserContestStateLogs/" + contestId + "/" + userName))
                .andExpect(status().isNotFound());

        verify(contestService).existsById(contestId);
        verifyNoInteractions(userService, contestStateService);
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getAllUserContestStateLogsUserNotFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_PATH + "/getAllUserContestStateLogs/" + contestId + "/" + userName))
                .andExpect(status().isNotFound());

        verify(contestService).existsById(contestId);
        verify(userService).getByUserName(userName);
        verifyNoInteractions(contestStateService);
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getAllUserContestStateLogsNoLogsFound() throws Exception {
        Long contestId = 1L;
        String userName = "studentUser";

        Contest contest = new Contest();
        contest.setId(contestId);

        User user = new User();
        user.setUsername(userName);
        user.setId(10L);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(userName)).thenReturn(Optional.of(user));
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.getAllLogsByContestAndUser(contestId, user.getId())).thenReturn(Collections.emptyList());

        mockMvc.perform(get(BASE_PATH + "/getAllUserContestStateLogs/" + contestId + "/" + userName))
                .andExpect(status().isNotFound());

        verify(contestService).existsById(contestId);
        verify(userService).getByUserName(userName);
        verify(contestStateService).getAllLogsByContestAndUser(contestId, user.getId());
        verifyNoMoreInteractions(contestStateService);
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void getAllContestStateLogsSuccess() throws Exception {
        Long contestId = 1L;
        Contest contest = new Contest();
        contest.setId(contestId);

        List<String> contestLogsIds = Arrays.asList("log1", "log2");
        WordleStateLog log1 = new WordleStateLog();
        WordleStateLog log2 = new WordleStateLog();

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(contestStateService.getAllLogsByContest(contestId)).thenReturn(contestLogsIds);
        when(contestStateService.getStateLog("log1")).thenReturn(log1);
        when(contestStateService.getStateLog("log2")).thenReturn(log2);

        mockMvc.perform(get(BASE_PATH + "/getAllContestStateLogs/" + contestId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void getAllContestStateLogsContestNotFound() throws Exception {
        Long contestId = 1L;

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/getAllContestStateLogs/" + contestId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void getAllContestStateLogsEmpty() throws Exception {
        Long contestId = 1L;

        List<String> contestStateLogs = new ArrayList<>();

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);

        mockMvc.perform(get(BASE_PATH + "/getAllContestStateLogs/" + contestId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteAllProfessorStateSuccess() throws Exception {
        Long contestId = 1L;
        Long professorId = 1L;
        String professorName = "professor";

        Contest contest = new Contest();
        contest.setId(contestId);

        User professor = new User();
        professor.setId(professorId);
        professor.setUsername(professorName);

        ContestState contestState = new ContestState();
        contestState.setId(1L);
        contestState.setUser(professor);
        contestState.setContest(contest);

        ContestStateLog contestStateLog = new ContestStateLog();
        contestStateLog.setContest(contest);
        contestStateLog.setUser(professor);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(userService.getByUserName(professorName)).thenReturn(Optional.of(professor));
        when(contestStateService.getContestState(contestId, professorId)).thenReturn(contestState);
        when(contestStateService.getLogsByContestIdAndUser(contestId, professorId)).thenReturn(List.of(contestStateLog));

        mockMvc.perform(post(BASE_PATH + "/deleteAllProfessorState/" + contestId + "/" + professorName))
                .andExpect(status().isOk());

        verify(contestStateService, times(1)).deleteAllLogs(List.of(contestStateLog));
        verify(contestStateService, times(1)).deleteById(contestState.getId());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteAllProfessorStateNotFound() throws Exception {
        Long contestId = 1L;
        String professorName = "professor";

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/deleteAllProfessorState/" + contestId + "/" + professorName))
                .andExpect(status().isNotFound());
    }

    @Test
    void existsInDictionaryTrue() throws Exception {
        String word = "word";

        when(dictionaryService.existsInGlobalDictionary(word)).thenReturn(true);

        mockMvc.perform(get(BASE_PATH + "/existsInDictionary/" + word))
                .andExpect(status().isOk());
    }

    @Test
    void existsInDictionaryFalse() throws Exception {
        String word = "word";

        when(dictionaryService.existsInGlobalDictionary(word)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/existsInDictionary/" + word))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void saveExternalDictionarySuccess() throws Exception {
        Long contestId = 1L;
        Contest contest = new Contest();
        contest.setId(contestId);

        List<String> words = Arrays.asList("palabra1", "palabra2");
        List<DictionaryExternal> savedWords = Arrays.asList(
                new DictionaryExternal("palabra1"),
                new DictionaryExternal("palabra2")
        );

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(dictionaryService.saveExternal(anyList())).thenReturn(savedWords);

        mockMvc.perform(post(BASE_PATH + "/saveExternalDictionary/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(words)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void saveExternalDictionaryContestNotFound() throws Exception {
        Long contestId = 1L;

        List<String> words = Arrays.asList("palabra1", "palabra2");
        List<DictionaryExternal> savedWords = Arrays.asList(
                new DictionaryExternal("palabra1"),
                new DictionaryExternal("palabra2")
        );

        when(contestService.existsById(contestId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/saveExternalDictionary/" + contestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(words)))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void existsInExternalDictionaryTrue() throws Exception {
        Long contestId = 1L;
        String wordle = "test";

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(dictionaryService.existsInExternalDictionary(wordle, contestId)).thenReturn(true);

        mockMvc.perform(get(BASE_PATH + "/existsInExternalDictionary/" + contestId + "/" + wordle))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void existsInExternalDictionaryFalse() throws Exception {
        Long contestId = 1L;
        String wordle = "test";

        Contest contest = new Contest();
        contest.setId(contestId);

        when(contestService.existsById(contestId)).thenReturn(true);
        when(contestService.getById(contestId)).thenReturn(contest);
        when(dictionaryService.existsInExternalDictionary(wordle, contestId)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/existsInExternalDictionary/" + contestId + "/" + wordle))
                .andExpect(status().isOk());
    }
}