package app.TFGWordle.dto;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Wordle;

import java.util.List;

public class WordleDTO {

    private Long id;
    private String word;
    private List<Contest> contests;

    public WordleDTO(Wordle wordle) {
        this.id = wordle.getId();
        this.word = wordle.getWord();
        this.contests = wordle.getContests();
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

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }
}
