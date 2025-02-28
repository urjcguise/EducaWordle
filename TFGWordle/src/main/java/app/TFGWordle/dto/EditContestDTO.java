package app.TFGWordle.dto;

import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Wordle;

import java.util.List;

public class EditContestDTO {
    private Contest contest;
    private List<Wordle> wordles;

    public EditContestDTO(Contest contest, List<Wordle> wordles) {
        this.contest = contest;
        this.wordles = wordles;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<Wordle> getWordles() {
        return wordles;
    }

    public void setWordles(List<Wordle> wordles) {
        this.wordles = wordles;
    }
}
