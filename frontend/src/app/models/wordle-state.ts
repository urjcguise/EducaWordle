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
  startTime: string;
  finishTime: string;
  timeNeeded: number;

  constructor(wordle: string = '', finished: boolean = false, tryCount: number = 0, state: State = new State(), startTime: string = '', finishTime: string = '', timeNeeded: number = 0) {
    this.wordle = wordle;
    this.finished = finished;
    this.tryCount = tryCount;
    this.state = state;
    this.startTime = startTime;
    this.finishTime = finishTime;
    this.timeNeeded = timeNeeded;
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
