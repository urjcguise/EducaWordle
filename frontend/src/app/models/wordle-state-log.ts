export class WordleStateLog {
    userName: string;
    email: string;
    dateLog: string;
    wordleToGuess: string;
    wordleInserted: string;
    numTry: number;
    wordlePosition: number;
    correct: number;
    wrongPosition: number;
    wrong: number;
    state: boolean;

    constructor(
        userName: string = '',
        email: string = '',
        dateLog: string = '',
        wordleToGuess: string = '',
        wordleInserted: string = '',
        numTry: number = 0,
        wordlePosition: number = 0,
        correct: number = 0,
        wrongPosition: number = 0,
        wrong: number = 0,
        state: boolean = false
    ) {
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

}