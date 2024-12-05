import { WordleState } from "./wordle-state";

export interface UserState {
    userName: string;
    state: WordleState;
  }