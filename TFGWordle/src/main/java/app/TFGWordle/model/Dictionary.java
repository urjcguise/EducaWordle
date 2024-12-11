package app.TFGWordle.model;

import jakarta.persistence.*;

@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wordle;

    public Dictionary() {}

    public Dictionary(String wordle) {
        this.wordle = wordle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setWordle(String wordle) {}

    public String getWordle() {
        return wordle;
    }
}
