package app.TFGWordle.model;

import jakarta.persistence.*;

@Entity
@Table(name="wordle")
public class Wordle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;

    public Wordle(String word) {
        this.word = word;
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
}
