package app.TFGWordle.service;

import app.TFGWordle.dto.WordleState;
import app.TFGWordle.model.ContestState;
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
    private ObjectMapper objectMapper;

    public ContestState save(ContestState contestState) {
        return contestStateRepository.save(contestState);
    }

    public Boolean existsState(Long contestId, Long userId) {
        return contestStateRepository.existsByContestIdAndUserId(contestId, userId);
    }

    public WordleState getState(Long contestId, Long userId) {
        Object result = contestStateRepository.getState(contestId, userId);

        if (result instanceof String) {
            try {
                JsonNode jsonNode = objectMapper.readTree((String) result);
                return objectMapper.treeToValue(jsonNode, WordleState.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error al convertir el estado del concurso", e);
            }
        }

        throw new RuntimeException("El resultado no es un JSON válido");
    }


    public ContestState getContestState(Long contestId, Long userId) {
        return contestStateRepository.findByContestIdAndUserId(contestId, userId);
    }

    public List<ContestState> getByContest(Long contestId) {
        return contestStateRepository.findByContestId(contestId);
    }

    public boolean existsByContest(Long contestId) {
        return contestStateRepository.existsByContestId(contestId);
    }

    public void deleteById(Long id) {
        contestStateRepository.deleteById(id);
    }
}
