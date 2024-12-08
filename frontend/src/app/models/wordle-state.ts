export class WordleState {
  numberOfWordle: number;
  games: Game[];

  constructor(numberOfWordle: number = 0, games: Game[] = []) {
    this.numberOfWordle = numberOfWordle;
    this.games = games;
  }
}

export class Game {
  wordle: string;
  finished: boolean;
  tryCount: number;
  state: State;

  constructor(wordle: string = '', finished: boolean = false, tryCount: number = 0, state: State = new State()) {
    this.wordle = wordle;
    this.finished = finished;
    this.tryCount = tryCount;
    this.state = state;
  }
}

export class State {
  good: number;
  halfGood: number;
  wrong: number;

  constructor(good: number = 0, halfGood: number = 0, wrong: number = 0) {
    this.good = good;
    this.halfGood = halfGood;
    this.wrong = wrong;
  }
}
