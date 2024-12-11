package app.TFGWordle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class DictionaryExternal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wordle;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    @JsonIgnore
    private Contest contest;

    public DictionaryExternal() {}

    public DictionaryExternal(String wordle) {
        this.wordle = wordle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getWordle() {
        return wordle;
    }

    public void setWordle(String wordle) {
        this.wordle = wordle;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
}
