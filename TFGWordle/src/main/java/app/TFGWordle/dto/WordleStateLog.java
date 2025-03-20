package app.TFGWordle.dto;

public class WordleStateLog {

    private String userName;
    private String email;
    private String dateLog;
    private String wordleToGuess;
    private String wordleInserted;
    private int numTry;
    private int wordlePosition;
    private int correct;
    private int wrongPosition;
    private int wrong;
    private boolean state;

    public WordleStateLog(String userName, String email, String dateLog, String wordleToGuess, String wordleInserted, int numTry, int wordlePosition, int correct, int wrongPosition, int wrong, boolean state) {
        this.userName = userName;
        this.email = email;
        this.dateLog = dateLog;
        this.wordleToGuess = wordleToGuess;
        this.wordleInserted = wordleInserted;
        this.numTry = numTry;
        this.wordlePosition = wordlePosition;
        this.correct = correct;
        this.wrongPosition = wrongPosition;
        this.wrong = wrong;
        this.state = state;
    }

    public WordleStateLog() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateLog() {
        return dateLog;
    }

    public void setDateLog(String dateLog) {
        this.dateLog = dateLog;
    }

    public String getWordleToGuess() {
        return wordleToGuess;
    }

    public void setWordleToGuess(String wordleToGuess) {
        this.wordleToGuess = wordleToGuess;
    }

    public String getWordleInserted() {
        return wordleInserted;
    }

    public void setWordleInserted(String wordleInserted) {
        this.wordleInserted = wordleInserted;
    }

    public int getNumTry() {
        return numTry;
    }

    public void setNumTry(int numTry) {
        this.numTry = numTry;
    }

    public int getWordlePosition() {
        return wordlePosition;
    }

    public void setWordlePosition(int wordlePosition) {
        this.wordlePosition = wordlePosition;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrongPosition() {
        return wrongPosition;
    }

    public void setWrongPosition(int wrongPosition) {
        this.wrongPosition = wrongPosition;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
