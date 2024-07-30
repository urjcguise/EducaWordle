package app.TFGWordle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Wordle {
    @Id
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
