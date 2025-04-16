import { Competition } from "./competition";
import { Contest } from "./contest";
import { Wordle } from "./wordle";

export interface WordlesStudentDTO {
    competition: Competition;
    contestsInfo: ContestInfo[];
}

export interface ContestInfo {
    contest: Contest;
    wordles: Wordle[];
}