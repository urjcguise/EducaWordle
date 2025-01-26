package app.TFGWordle.model;

import app.TFGWordle.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
}
