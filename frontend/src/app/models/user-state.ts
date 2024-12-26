import { WordleState } from "./wordle-state";

export interface UserState {
  userName: string;
  email: string;
  state: WordleState;
}