package app.TFGWordle.service;

import app.TFGWordle.dto.WordleState;
import app.TFGWordle.dto.WordleStateLog;
import app.TFGWordle.model.ContestState;
import app.TFGWordle.model.ContestStateLog;
import app.TFGWordle.repository.ContestStateLogRepository;
import app.TFGWordle.repository.ContestStateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestStateService {

    @Autowired
    private ContestStateRepository contestStateRepository;

    @Autowired
    private ContestStateLogRepository contestStateLogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ContestState save(ContestState contestState) {
        return contestStateRepository.save(contestState);
    }

    public Boolean existsState(Long contestId, Long userId) {
        return contestStateRepository.existsByContestIdAndUserId(contestId, userId);
    }

    public WordleState getState(Long contestId, Long userId) {
        Object result = contestStateRepository.getState(contestId, userId);

        if (result != null) {
            try {
                JsonNode jsonNode = objectMapper.readTree((String) result);
                return objectMapper.treeToValue(jsonNode, WordleState.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error al convertir el estado del concurso", e);
            }
        }

        throw new RuntimeException("El resultado no es un JSON válido");
    }
    
    public WordleStateLog getStateLog(String contestStateLog) {
        try {
            JsonNode jsonNode = objectMapper.readTree(contestStateLog);
            return objectMapper.treeToValue(jsonNode, WordleStateLog.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir el estado del concurso", e);
        }
    }

    public ContestState getContestState(Long contestId, Long userId) {
        return contestStateRepository.findByContestIdAndUserId(contestId, userId);
    }

    public List<ContestState> getAllByContest(Long contestId) {
        return contestStateRepository.findByContestId(contestId);
    }

    public boolean existsByContest(Long contestId) {
        return contestStateRepository.existsByContestId(contestId);
    }

    public void deleteById(Long id) {
        contestStateRepository.deleteById(id);
    }

    public void deleteByLogId(Long id) {
        contestStateLogRepository.deleteById(id);
    }

    public void saveLog(ContestStateLog contestStateLog) {
        contestStateLogRepository.save(contestStateLog);
    }

    public List<String> getAllLogsByContestAndUser(Long contestId, Long userId) {
        return contestStateLogRepository.findByContestIdAndUserId(contestId, userId);
    }

    public List<String> getAllLogsByContest(Long contestId) {
        return contestStateLogRepository.findAllStateLogs(contestId);
    }

    public List<ContestStateLog> getLogsByContestId(Long contestId) {
        return contestStateLogRepository.findByContestId(contestId);
    }
}
