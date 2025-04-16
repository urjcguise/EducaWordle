package app.TFGWordle.dto;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Wordle;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WordlesStudentDTO {

    private Competition competition;
    private List<ContestInfo> contestsInfo;

    public WordlesStudentDTO(Competition competition) {
        this.competition = competition;
        Date now = new Date();
        this.contestsInfo = competition.getContests().stream()
                .filter(contest -> contest.getEndDate() != null && contest.getEndDate().before(now))
                .map(contest -> new ContestInfo(contest, contest.getWordles()))
                .collect(Collectors.toList());
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<ContestInfo> getContestsInfo() {
        return contestsInfo;
    }

    public void setContestsInfo(List<ContestInfo> contestsInfo) {
        this.contestsInfo = contestsInfo;
    }

    public static class ContestInfo {

        private Contest contest;
        private List<Wordle> wordles;

        public ContestInfo(Contest contest, List<Wordle> wordles) {
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
}
