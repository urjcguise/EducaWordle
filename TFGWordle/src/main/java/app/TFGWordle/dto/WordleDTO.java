package app.TFGWordle.dto;

import app.TFGWordle.model.Wordle;

public class WordleDTO {

    private Long id;
    private String word;

    public WordleDTO(Wordle wordle) {
        this.id = wordle.getId();
        this.word = wordle.getWord();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
