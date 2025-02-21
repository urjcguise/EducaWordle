package app.TFGWordle.model;

import app.TFGWordle.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "contestState")
public class ContestState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Column(name = "state", columnDefinition = "TEXT")
    private String state;

    @Convert(converter = ListOfMapConverter.class)
    @Column(name = "letter_counts_list", columnDefinition = "TEXT")
    private List<Map<Character, Integer>> letterCountsList = new ArrayList<>();

    public ContestState() {}

    public ContestState(Contest contest, User user) {
        this.contest = contest;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JsonNode getState() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(this.state);
    }

    public void setState(JsonNode state) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.state = mapper.writeValueAsString(state);
    }

    public List<Map<Character, Integer>> getLetterCountsList() {
        return letterCountsList;
    }

    public void setLetterCountsList(List<Map<Character, Integer>> letterCountsList) {
        this.letterCountsList = letterCountsList;
    }


    @Converter
    public static class ListOfMapConverter implements AttributeConverter<List<Map<Character, Integer>>, String> {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(List<Map<Character, Integer>> attribute) {
            try {
                return objectMapper.writeValueAsString(attribute);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Error al convertir lista de mapas a JSON", e);
            }
        }

        @Override
        public List<Map<Character, Integer>> convertToEntityAttribute(String dbData) {
            try {
                return objectMapper.readValue(dbData, new TypeReference<List<Map<Character, Integer>>>(){});
            } catch (IOException e) {
                throw new IllegalArgumentException("Error al convertir JSON a lista de mapas", e);
            }
        }
    }
}
