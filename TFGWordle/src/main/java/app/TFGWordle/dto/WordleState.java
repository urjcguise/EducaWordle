package app.TFGWordle.dto;

import java.util.List;

public class WordleState {
    private int numberOfWordle;
    private List<Integer> wordleOrder;
    private List<Game> games;

    public int getNumberOfWordle() {
        return numberOfWordle;
    }

    public void setNumberOfWordle(int numberOfWordle) {
        this.numberOfWordle = numberOfWordle;
    }

    public List<Integer> getWordleOrder() {
        return wordleOrder;
    }

    public void setWordleOrder(List<Integer> wordleOrder) {
        this.wordleOrder = wordleOrder;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public static class Game {
        private boolean finished;
        private boolean won;
        private int tryCount;
        private String startTime;
        private String finishTime;
        private int timeNeeded;
        private State state;
        private String lastWordle;
        private String timeGuess;

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public boolean isWon() {
            return won;
        }

        public void setWon(boolean won) {
            this.won = won;
        }

        public int getTryCount() {
            return tryCount;
        }

        public void setTryCount(int tryCount) {
            this.tryCount = tryCount;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public int getTimeNeeded() {
            return timeNeeded;
        }

        public void setTimeNeeded(int timeNeeded) {
            this.timeNeeded = timeNeeded;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public String getLastWordle() {
            return lastWordle;
        }

        public void setLastWordle(String lastWordle) {
            this.lastWordle = lastWordle;
        }

        public String getTimeGuess() {
            return timeGuess;
        }

        public void setTimeGuess(String timeGuess) {
            this.timeGuess = timeGuess;
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
