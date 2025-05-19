package app.TFGWordle.service;

import app.TFGWordle.dto.WordleState;
import app.TFGWordle.dto.WordleStateLog;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.ContestState;
import app.TFGWordle.model.ContestStateLog;
import app.TFGWordle.repository.ContestStateLogRepository;
import app.TFGWordle.repository.ContestStateRepository;
import app.TFGWordle.security.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContestStateServiceTest {

    @Mock
    private ContestStateRepository contestStateRepository;

    @Mock
    private ContestStateLogRepository contestStateLogRepository;

    @InjectMocks
    private ContestStateService contestStateService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void save() {
        ContestState contestState = new ContestState();
        contestStateService.save(contestState);
        verify(contestStateRepository, times(1)).save(contestState);
    }

    @Test
    void existsStateExists() {
        Long contestId = 1L;
        Long userId = 2L;

        when(contestStateRepository.existsByContestIdAndUserId(contestId, userId)).thenReturn(true);

        assertTrue(contestStateService.existsState(contestId, userId));
    }

    @Test
    void existsStateNotExists() {
        Long contestId = 1L;
        Long userId = 2L;

        when(contestStateRepository.existsByContestIdAndUserId(contestId, userId)).thenReturn(false);

        assertFalse(contestStateService.existsState(contestId, userId));
    }

    @Test
    void getStateSuccess() throws JsonProcessingException {
        Long contestId = 1L;
        Long userId = 10L;
        String jsonState = "{\"numberOfWordle\":1,\"games\":[{\"finished\":true,\"won\":true,\"tryCount\":2,\"startTime\":\"2025-03-25 11:21:49\",\"finishTime\":\"2025-03-25 11:21:57\",\"timeNeeded\":8,\"state\":{\"good\":6,\"halfGood\":0,\"wrong\":5},\"lastWordle\":\"EJEMPLO\",\"timeGuess\":\"2025-03-25 11:21:57\"}]}";
        JsonNode jsonNode = mock(JsonNode.class);
        WordleState expectedWordleState = new WordleState();
        WordleState.Game game = new WordleState.Game();
        WordleState.Game.State state = new WordleState.Game.State();
        state.setGood(6);
        state.setHalfGood(0);
        state.setWrong(5);
        game.setState(state);
        game.setFinished(true);
        game.setWon(true);
        game.setTryCount(2);
        game.setStartTime("2025-03-25 11:21:49");
        game.setFinishTime("2025-03-25 11:21:57");
        game.setTimeNeeded(8);
        game.setLastWordle("EJEMPLO");
        game.setTimeGuess("2025-03-25 11:21:57");
        expectedWordleState.setNumberOfWordle(1);
        expectedWordleState.setGames(List.of(game));

        when(contestStateRepository.getState(contestId, userId)).thenReturn(jsonState);
        when(objectMapper.readTree(jsonState)).thenReturn(jsonNode);
        when(objectMapper.treeToValue(jsonNode, WordleState.class)).thenReturn(expectedWordleState);

        WordleState actualWordleState = contestStateService.getState(contestId, userId);

        assertEquals(expectedWordleState.getNumberOfWordle(), actualWordleState.getNumberOfWordle());
        assertEquals(expectedWordleState.getGames().get(0).getTryCount(), actualWordleState.getGames().get(0).getTryCount());
        assertEquals(expectedWordleState.getGames().get(0).getState().getGood(), actualWordleState.getGames().get(0).getState().getGood());

        verify(contestStateRepository, times(1)).getState(contestId, userId);
        verify(objectMapper, times(1)).readTree(jsonState);
        verify(objectMapper, times(1)).treeToValue(jsonNode, WordleState.class);
    }

    @Test
    void getStateInvalidJson() throws JsonProcessingException {
        Long contestId = 1L;
        Long userId = 10L;
        String invalidJson = "invalid json";

        when(contestStateRepository.getState(contestId, userId)).thenReturn(invalidJson);
        when(objectMapper.readTree(invalidJson)).thenThrow(JsonProcessingException.class);

        assertThrows(RuntimeException.class, () -> contestStateService.getState(contestId, userId));

        verify(contestStateRepository, times(1)).getState(contestId, userId);
        verify(objectMapper, times(1)).readTree(invalidJson);
        verify(objectMapper, never()).treeToValue(any(), eq(WordleState.class));
    }

    @Test
    void getStateNullResult() throws JsonProcessingException {
        Long contestId = 1L;
        Long userId = 10L;

        when(contestStateRepository.getState(contestId, userId)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> contestStateService.getState(contestId, userId));

        verify(contestStateRepository, times(1)).getState(contestId, userId);
        verify(objectMapper, never()).readTree(anyString());
        verify(objectMapper, never()).treeToValue(any(), eq(WordleState.class));
    }

    @Test
    void getStateLogSuccess() throws JsonProcessingException {
        String contestStateLogJson = "{\"userName\":\"Ana Torres\",\"email\":\"anatorres@gmail.com\",\"dateLog\":\"2025-03-25 11:21:52\",\"wordleToGuess\":\"ejemplo\",\"wordleInserted\":\"PRUEBAS\",\"numTry\":1,\"wordlePosition\":1,\"correct\":0,\"wrongPosition\":2,\"wrong\":5,\"state\":false}";
        JsonNode jsonNode = mock(JsonNode.class);
        WordleStateLog expectedLog = new WordleStateLog();
        expectedLog.setUserName("Ana Torres");
        expectedLog.setEmail("anatorres@gmail.com");
        expectedLog.setDateLog("2025-03-25 11:21:52");
        expectedLog.setWordleToGuess("ejemplo");
        expectedLog.setWordleInserted("PRUEBAS");
        expectedLog.setNumTry(1);
        expectedLog.setWordlePosition(1);
        expectedLog.setCorrect(0);
        expectedLog.setWrongPosition(2);
        expectedLog.setWrong(5);
        expectedLog.setState(false);

        when(objectMapper.readTree(contestStateLogJson)).thenReturn(jsonNode);
        when(objectMapper.treeToValue(jsonNode, WordleStateLog.class)).thenReturn(expectedLog);

        WordleStateLog actualLog = contestStateService.getStateLog(contestStateLogJson);

        assertEquals(expectedLog.getUserName(), actualLog.getUserName());
        assertEquals(expectedLog.getEmail(), actualLog.getEmail());
        assertEquals(expectedLog.getDateLog(), actualLog.getDateLog());
        assertEquals(expectedLog.getWordleToGuess(), actualLog.getWordleToGuess());
        assertEquals(expectedLog.getWordleInserted(), actualLog.getWordleInserted());
        assertEquals(expectedLog.getNumTry(), actualLog.getNumTry());
        assertEquals(expectedLog.getWordlePosition(), actualLog.getWordlePosition());
        assertEquals(expectedLog.getCorrect(), actualLog.getCorrect());
        assertEquals(expectedLog.getWrongPosition(), actualLog.getWrongPosition());
        assertEquals(expectedLog.getWrong(), actualLog.getWrong());
        assertEquals(expectedLog.isState(), actualLog.isState());

        verify(objectMapper, times(1)).readTree(contestStateLogJson);
        verify(objectMapper, times(1)).treeToValue(jsonNode, WordleStateLog.class);
    }

    @Test
    public void getStateLogInvalidJson() throws JsonProcessingException {
        String invalidJson = "invalid json";

        when(objectMapper.readTree(invalidJson)).thenThrow(JsonProcessingException.class);

        assertThrows(RuntimeException.class, () -> contestStateService.getStateLog(invalidJson));

        verify(objectMapper, times(1)).readTree(invalidJson);
        verify(objectMapper, never()).treeToValue(any(), eq(WordleStateLog.class));
    }

    @Test
    void getContestState() {
        Long contestId = 1L;
        Long userId = 10L;

        ContestState expectedContestState = new ContestState();
        expectedContestState.setId(1L);

        when(contestStateRepository.findByContestIdAndUserId(contestId, userId)).thenReturn(expectedContestState);

        ContestState actualContestState = contestStateService.getContestState(contestId, userId);

        assertEquals(expectedContestState.getId(), actualContestState.getId());
    }

    @Test
    void getAllByContest() {
        Long contestId = 1L;

        ContestState contestState1 = new ContestState();
        contestState1.setId(1L);
        ContestState contestState2 = new ContestState();
        contestState2.setId(2L);
        List<ContestState> contestStates = List.of(contestState1, contestState2);

        when(contestStateRepository.findByContestId(contestId)).thenReturn(contestStates);

        List<ContestState> resultContests = contestStateService.getAllByContest(contestId);

        assertEquals(contestStates.size(), resultContests.size());
        assertEquals(contestStates.get(0).getId(), resultContests.get(0).getId());
        assertEquals(contestStates.get(1).getId(), resultContests.get(1).getId());
    }

    @Test
    void getAllByContestEmptyList() {
        Long contestId = 1L;

        when(contestStateRepository.findByContestId(contestId)).thenReturn(new ArrayList<>());

        List<ContestState> resultContests = contestStateService.getAllByContest(contestId);

        assertEquals(0, resultContests.size());
    }

    @Test
    void existsByContestTrue() {
        Long contestId = 1L;

        when(contestStateRepository.existsByContestId(contestId)).thenReturn(true);

        assertTrue(contestStateService.existsByContest(contestId));
    }

    @Test
    void existsByContestFase() {
        Long contestId = 1L;

        when(contestStateRepository.existsByContestId(contestId)).thenReturn(false);

        assertFalse(contestStateService.existsByContest(contestId));
    }

    @Test
    void deleteById() {
        Long contestStateId = 1L;
        ContestState contestState = new ContestState();
        contestState.setId(contestStateId);

        contestStateService.deleteById(contestStateId);

        verify(contestStateRepository, times(1)).deleteById(contestStateId);
    }

    @Test
    void deleteByLogId() {
        Long contestStateLogId = 1L;
        ContestStateLog contestStateLog = new ContestStateLog();
        contestStateLog.setId(contestStateLogId);

        contestStateService.deleteById(contestStateLogId);

        verify(contestStateRepository, times(1)).deleteById(contestStateLogId);
    }

    @Test
    void deleteAllLogs() {
        ContestStateLog contestStateLog1 = new ContestStateLog();
        ContestStateLog contestStateLog2 = new ContestStateLog();

        List<ContestStateLog> contestStateLogs = new ArrayList<>(List.of(contestStateLog1, contestStateLog2));

        contestStateService.deleteAllLogs(contestStateLogs);

        verify(contestStateLogRepository, times(1)).deleteAll(contestStateLogs);
    }

    @Test
    void saveLog() {
        ContestStateLog contestStateLog = new ContestStateLog();
        contestStateService.saveLog(contestStateLog);
        verify(contestStateLogRepository, times(1)).save(contestStateLog);
    }

    @Test
    void getAllLogsByContestAndUser() {
        Long contestId = 1L;
        Long userId = 10L;
        List<String> expectedLogs = Arrays.asList(
                "{\"userName\":\"Ana Torres\",\"email\":\"anatorres@gmail.com\",\"dateLog\":\"2025-03-25 11:21:52\",\"wordleToGuess\":\"EJEMPLO\",\"wordleInserted\":\"PRUEBAS\",\"numTry\":1,\"wordlePosition\":1,\"correct\":0,\"wrongPosition\":2,\"wrong\":5,\"state\":false}",
                "{\"userName\":\"Ana Torres\",\"email\":\"anatorres@gmail.com\",\"dateLog\":\"2025-03-25 11:22:52\",\"wordleToGuess\":\"EJEMPLO\",\"wordleInserted\":\"EJEMPLO\",\"numTry\":2,\"wordlePosition\":6,\"correct\":6,\"wrongPosition\":0,\"wrong\":0,\"state\":true}"
        );

        when(contestStateLogRepository.findAllLogsByContestIdAndUserId(contestId, userId)).thenReturn(expectedLogs);

        List<String> actualLogs = contestStateService.getAllLogsByContestAndUser(contestId, userId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertEquals(expectedLogs.get(0), actualLogs.get(0));
        assertEquals(expectedLogs.get(1), actualLogs.get(1));

        verify(contestStateLogRepository, times(1)).findAllLogsByContestIdAndUserId(contestId, userId);
    }

    @Test
    public void getAllLogsByContestAndUserEmptyList() {
        Long contestId = 1L;
        Long userId = 10L;
        List<String> expectedLogs = new ArrayList<>();

        when(contestStateLogRepository.findAllLogsByContestIdAndUserId(contestId, userId)).thenReturn(expectedLogs);

        List<String> actualLogs = contestStateService.getAllLogsByContestAndUser(contestId, userId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertTrue(actualLogs.isEmpty());

        verify(contestStateLogRepository, times(1)).findAllLogsByContestIdAndUserId(contestId, userId);
    }

    @Test
    public void getAllLogsByContest() {
        Long contestId = 1L;
        List<String> expectedLogs = Arrays.asList(
                "{\"userName\":\"Ana Torres\",\"email\":\"anatorres@gmail.com\",\"dateLog\":\"2025-03-25 11:21:52\",\"wordleToGuess\":\"ejemplo\",\"wordleInserted\":\"PRUEBAS\",\"numTry\":1,\"wordlePosition\":1,\"correct\":0,\"wrongPosition\":2,\"wrong\":5,\"state\":false}",
                "{\"userName\":\"Ana Torres\",\"email\":\"anatorres@gmail.com\",\"dateLog\":\"2025-03-25 11:22:52\",\"wordleToGuess\":\"ejemplo\",\"wordleInserted\":\"EJEMPLO\",\"numTry\":2,\"wordlePosition\":6,\"correct\":6,\"wrongPosition\":0,\"wrong\":0,\"state\":true}"
        );

        when(contestStateLogRepository.findAllStateLogs(contestId)).thenReturn(expectedLogs);

        List<String> actualLogs = contestStateService.getAllLogsByContest(contestId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertEquals(expectedLogs.get(0), actualLogs.get(0));
        assertEquals(expectedLogs.get(1), actualLogs.get(1));

        verify(contestStateLogRepository, times(1)).findAllStateLogs(contestId);
    }

    @Test
    public void getAllLogsByContestEmptyList() {
        Long contestId = 1L;
        List<String> expectedLogs = new ArrayList<>();

        when(contestStateLogRepository.findAllStateLogs(contestId)).thenReturn(expectedLogs);

        List<String> actualLogs = contestStateService.getAllLogsByContest(contestId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertTrue(actualLogs.isEmpty());

        verify(contestStateLogRepository, times(1)).findAllStateLogs(contestId);
    }

    @Test
    public void getLogsByContestId() throws JsonProcessingException {
        Long contestId = 1L;
        Contest contest = new Contest();
        contest.setId(contestId);
        User user = new User();
        user.setId(10L);

        ContestStateLog log1 = new ContestStateLog(contest, user);
        JsonNode jsonNode1 = mock(JsonNode.class);
        log1.setState(jsonNode1);

        ContestStateLog log2 = new ContestStateLog(contest, user);
        JsonNode jsonNode2 = mock(JsonNode.class);
        log2.setState(jsonNode2);

        List<ContestStateLog> expectedLogs = Arrays.asList(log1, log2);

        when(contestStateLogRepository.findByContestId(contestId)).thenReturn(expectedLogs);

        List<ContestStateLog> actualLogs = contestStateService.getLogsByContestId(contestId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertEquals(expectedLogs.get(0), actualLogs.get(0));
        assertEquals(expectedLogs.get(1), actualLogs.get(1));

        verify(contestStateLogRepository, times(1)).findByContestId(contestId);
    }

    @Test
    public void getLogsByContestIdEmptyList() {
        Long contestId = 1L;
        List<ContestStateLog> expectedLogs = new ArrayList<>();

        when(contestStateLogRepository.findByContestId(contestId)).thenReturn(expectedLogs);

        List<ContestStateLog> actualLogs = contestStateService.getLogsByContestId(contestId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertTrue(actualLogs.isEmpty());

        verify(contestStateLogRepository, times(1)).findByContestId(contestId);
    }

    @Test
    public void getLogsByContestIdAndUser() throws JsonProcessingException {
        Long contestId = 1L;
        Long userId = 10L;
        Contest contest = new Contest();
        contest.setId(contestId);
        User user = new User();
        user.setId(userId);

        ContestStateLog log1 = new ContestStateLog(contest, user);
        JsonNode jsonNode1 = mock(JsonNode.class);
        log1.setState(jsonNode1);

        ContestStateLog log2 = new ContestStateLog(contest, user);
        JsonNode jsonNode2 = mock(JsonNode.class);
        log2.setState(jsonNode2);

        List<ContestStateLog> expectedLogs = Arrays.asList(log1, log2);

        when(contestStateLogRepository.findByContestIdAndUserId(contestId, userId)).thenReturn(expectedLogs);

        List<ContestStateLog> actualLogs = contestStateService.getLogsByContestIdAndUser(contestId, userId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertEquals(expectedLogs.get(0), actualLogs.get(0));
        assertEquals(expectedLogs.get(1), actualLogs.get(1));

        verify(contestStateLogRepository, times(1)).findByContestIdAndUserId(contestId, userId);
    }

    @Test
    public void getLogsByContestIdAndUserEmptyList() {
        Long contestId = 1L;
        Long userId = 10L;
        List<ContestStateLog> expectedLogs = new ArrayList<>();

        when(contestStateLogRepository.findByContestIdAndUserId(contestId, userId)).thenReturn(expectedLogs);

        List<ContestStateLog> actualLogs = contestStateService.getLogsByContestIdAndUser(contestId, userId);

        assertEquals(expectedLogs.size(), actualLogs.size());
        assertTrue(actualLogs.isEmpty());

        verify(contestStateLogRepository, times(1)).findByContestIdAndUserId(contestId, userId);
    }
}