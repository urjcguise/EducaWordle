package app.TFGWordle.dto;

import java.util.List;

public class ResumeContestDTO {

    private int wordlePosition;
    private int tryPosition;
    private int charPosition;
    private List<Try> tries;
    private List<Integer> wordleOrder;
    private WordleState wordleState;

    public int getWordlePosition() {
        return wordlePosition;
    }

    public void setWordlePosition(int wordlePosition) {
        this.wordlePosition = wordlePosition;
    }

    public int getTryPosition() {
        return tryPosition;
    }

    public void setTryPosition(int tryPosition) {
        this.tryPosition = tryPosition;
    }

    public List<Integer> getWordleOrder() {
        return wordleOrder;
    }

    public void setWordleOrder(List<Integer> wordleOrder) {
        this.wordleOrder = wordleOrder;
    }

    public int getCharPosition() {
        return charPosition;
    }

    public void setCharPosition(int charPosition) {
        this.charPosition = charPosition;
    }

    public List<Try> getTries() {
        return tries;
    }

    public WordleState getWordleState() {
        return wordleState;
    }

    public void setWordleState(WordleState wordleState) {
        this.wordleState = wordleState;
    }

    public void setTries(List<Try> tries) {
        this.tries = tries;
    }

    public static class Try {
        private List<Letter> letters;

        public List<Letter> getLetters() {
            return letters;
        }

        public void setLetters(List<Letter> letters) {
            this.letters = letters;
        }

        public static class Letter {
            private char letter;
            private Integer state;

            public char getLetter() {
                return letter;
            }

            public void setLetter(char letter) {
                this.letter = letter;
            }

            public Integer getState() {
                return state;
            }

            public void setState(Integer state) {
                this.state = state;
            }
        }
    }
}
