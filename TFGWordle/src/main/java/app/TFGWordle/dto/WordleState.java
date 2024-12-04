package app.TFGWordle.dto;

import java.util.List;

public class WordleState {
    private int numberOfWordle;
    private List<Game> games;

    public int getNumberOfWordle() {
        return numberOfWordle;
    }

    public void setNumberOfWordle(int numberOfWordle) {
        this.numberOfWordle = numberOfWordle;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public static class Game {
        private String wordle;
        private boolean finished;
        private int tryCount;
        private State state;

        public String getWordle() {
            return wordle;
        }

        public void setWordle(String wordle) {
            this.wordle = wordle;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public int getTryCount() {
            return tryCount;
        }

        public void setTryCount(int tryCount) {
            this.tryCount = tryCount;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public static class State {
            private int good;
            private int halfGood;
            private int wrong;

            public int getGood() {
                return good;
            }

            public void setGood(int good) {
                this.good = good;
            }

            public int getHalfGood() {
                return halfGood;
            }

            public void setHalfGood(int halfGood) {
                this.halfGood = halfGood;
            }

            public int getWrong() {
                return wrong;
            }

            public void setWrong(int wrong) {
                this.wrong = wrong;
            }
        }
    }
}
