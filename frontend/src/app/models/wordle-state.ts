export class WordleState {
  numberOfWordle: number;
  games: Game[];
  wordleOrder: number[];

  constructor(numberOfWordle: number = 0, games: Game[] = [], isRandom: boolean) {
    this.numberOfWordle = numberOfWordle;
    this.games = games;
    this.wordleOrder = isRandom
      ? this.shuffle(Array.from({ length: numberOfWordle }, (_, i) => i))
      : Array.from( { length: numberOfWordle }, (_, i) => i);
  }

  private shuffle(array: number[]): number[] {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }
}

export class Game {
  finished: boolean;
  won: boolean;
  tryCount: number;
  state: State;
  startTime: string;
  finishTime: string;
  timeNeeded: number;
  lastWordle: string;
  timeGuess: string;

  constructor(finished: boolean = false, won: boolean = false, tryCount: number = 0, state: State = new State(), startTime: string = '', finishTime: string = '', timeNeeded: number = 0, lastWordle: string = "", timeGuess: string = "") {
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
