import { Contest } from "./contest";

export class Wordle {
    id!: number;
    word!: string;
    contests!: Contest[];

    constructor(word: string = '') {
        this.word = word;
    }
}
