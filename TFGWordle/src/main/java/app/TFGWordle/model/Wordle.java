package app.TFGWordle.model;

import jakarta.persistence.*;

@Entity
@Table(name="wordle")
public class Wordle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    public Wordle(String word, Contest contest) {
        this.word = word;
        this.contest = contest;
    }

    public Wordle() {

    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }
    public String getWord() {
        return word;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
}
