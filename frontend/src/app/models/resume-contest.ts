import { WordleState } from "./wordle-state";

export interface ResumeContestDTO {
    wordlePosition: number;
    tryPosition: number;
    charPosition: number;
    tries: Try[];

    wordleState: WordleState;
}

export interface Try {
    letters: Letter[];
}

export interface Letter {
    letter: string;
    state: number;
}