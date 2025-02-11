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
  won: boolean;
  tryCount: number;
  state: State;
  startTime: string;
  finishTime: string;
  timeNeeded: number;
  lastWordle: string;
  timeGuess: string;

  constructor(wordle: string = '', finished: boolean = false, won: boolean = false, tryCount: number = 0, state: State = new State(), startTime: string = '', finishTime: string = '', timeNeeded: number = 0, lastWordle: string = "", timeGuess: string = "") {
    this.wordle = wordle;
    this.finished = finished;
    this.won = won;
    this.tryCount = tryCount;
    this.state = state;
    this.startTime = startTime;
    this.finishTime = finishTime;
    this.timeNeeded = timeNeeded;
    this.lastWordle = lastWordle;
    this.timeGuess = timeGuess;
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
