import { Component, ElementRef, HostListener, QueryList, ViewChildren } from '@angular/core';
import { WordleService } from '../service/wordle.service';
import { ContestService } from '../service/contest.service';
import { Contest } from '../models/contest';
import { TokenService } from '../service/token.service';
import { Game, State, WordleState } from '../models/wordle-state';
import { differenceInSeconds, format } from 'date-fns';
import { WordleStateLog } from '../models/wordle-state-log';
import { NavigationStart, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { Subscription } from 'rxjs';


interface Try {
  letters: Letter[];
}

interface Letter {
  text: string;
  state: LetterState;
}

enum LetterState {
  WRONG,
  PARTIAL_MATCH,
  FULL_MATCH,
  PENDING,
}

@Component({
  selector: 'wordle',
  templateUrl: './play-wordle.component.html',
  styleUrls: ['./play-wordle.component.css'],
})
export class PlayWordleComponent {

  private subscription: Subscription = new Subscription;

  @ViewChildren('tryContainer') tryContainers!: QueryList<ElementRef>;

  competitionName: string = '';
  competitionId: number = 0;

  readonly tries: Try[] = [];
  readonly LetterState = LetterState;

  readonly keyboardRows = [
    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ñ'],
    ['Enviar', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Backspace'],
  ];

  readonly keyboardRowsAccent = [
    ['Á', 'É', 'Í', 'Ó', 'Ú'],
    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ñ'],
    ['Enviar', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Backspace'],
  ];

  readonly curLetterStates: { [key: string]: LetterState } = {};
  infoMsg = '';
  fadeOutInfoMessage = false;

  private curLetterIndex = 0;

  wordleLength: number[] = [];
  wordleOrder: number[] = [];

  isAccentMode: boolean = false;

  numSubmittedTries = 0;
  currentWordleIndex = 0;
  finished = false;
  won = false;
  hasMoreWords = true;
  lastWordle = "";

  numWordles: number = 0;

  private intervalId: any;
  contest!: Contest;
  endTime: Date = new Date();
  timeToFinish: string = '';
  rightNow: Date = new Date();

  games: Game[] = [];

  wordleState!: WordleState;
  wordleStateLog!: WordleStateLog;

  numTries!: number;

  userEmail: string = '';
  userName: string = '';

  isProfessor: boolean = false;
  professorName: string = '';

  constructor(private wordleService: WordleService, private contestService: ContestService, private tokenService: TokenService, private router: Router, private userService: UserService) {
    this.competitionName = history.state.competitionName;
    this.competitionId = history.state.competitionId;
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });

    this.isProfessor = history.state.isProfessor;
    this.professorName = history.state.professorName;

    this.contestService.getContestById(history.state.contestId).subscribe({
      next: (cont) => {
        this.contest = cont;
        this.numTries = cont.numTries;
        this.endTime = new Date(cont.endDate);

        this.updateTimeToFinish();
        this.intervalId = setInterval(() => {
          this.rightNow = new Date();
          this.updateTimeToFinish();
        }, 1000); 

        this.isAccentMode = cont.accentMode;

        this.numWordles = cont.wordlesLength.length;
        this.wordleLength = cont.wordlesLength;

        for (let i = 0; i < this.numTries; i++) {
          const letters: Letter[] = [];
          for (let j = 0; j < this.wordleLength[this.currentWordleIndex]; j++) {
            letters.push({ text: '', state: LetterState.PENDING });
          }
          this.tries.push({ letters });
        }

        this.userName = this.tokenService.getUserName() || '';
        this.initializeState();
        this.setTargetWord();
      },
      error: (error) => {
        console.error('Error consiguiendo el concurso', error);
      },
    });
  }

  ngDestroy() {
    clearInterval(this.intervalId);
  }

  private initializeState() {

    this.userService.getEmail(this.userName).subscribe({
      next: (email) => {
        this.userEmail = email;
      },
      error: (e) => {
        console.error('Error obteniendo el email', e)
      }
    });

    for (var i = 0; i < this.numWordles; i++) {
      const newGame = new Game();
      this.games.push(newGame);
    }

    this.wordleState = new WordleState(this.numWordles, this.games, this.contest.randomMode);

    this.wordleOrder = this.wordleState.wordleOrder;
    this.games[this.wordleOrder[this.currentWordleIndex]].startTime = format(new Date(), 'yyyy-MM-dd HH:mm:ss');

    this.contestService.createContestState(history.state.contestId, this.userName, this.wordleState).subscribe({
      next: () => {
        console.log('Estado del concurso creado correctamente');
      },
      error: (error) => {
        if (error.status === 409)
          this.resumeGame();
        else
          console.error('Error creando el estado del concurso', error);
      },
    });
  }

  private setTargetWord() {
    if (this.currentWordleIndex < this.numWordles) {
      this.resetKeyboard();
      this.won = false;

      for (let tryData of this.tries) {
        tryData.letters = Array.from({ length: this.wordleLength[this.wordleOrder[this.currentWordleIndex]] }, () => ({
          text: '',
          state: LetterState.PENDING,
        }));
      }

    } else {
      this.showInfoMessage('¡Has completado todas las palabras!');
      this.finished = true;
    }
  }

  resetKeyboard() {
    for (const key in this.curLetterStates) {
      delete this.curLetterStates[key];
    }
  }

  @HostListener('document:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    this.handleClickKey(event.key);
  }

  getKeyClass(key: string): string {
    const state = this.curLetterStates[key.toLowerCase()];
    switch (state) {
      case LetterState.FULL_MATCH:
        return 'match key';
      case LetterState.PARTIAL_MATCH:
        return 'partial key';
      case LetterState.WRONG:
        return 'wrong key';
      default:
        return 'key';
    }
  }

  handleClickKey(key: string) {
    if (this.finished) return;
    if (key.length === 1 && this.isAccentMode && /^[a-zñáéíóúÁÉÍÓÚ]$/i.test(key)) {

      if (this.curLetterIndex < (this.numSubmittedTries + 1) * this.wordleLength[this.wordleOrder[this.currentWordleIndex]]) {
        this.setLetter(key);
        this.curLetterIndex++;
      }
    } else if (key.length === 1 && !this.isAccentMode && /^[a-zñ]$/i.test(key)) {

      if (this.curLetterIndex < (this.numSubmittedTries + 1) * this.wordleLength[this.wordleOrder[this.currentWordleIndex]]) {
        this.setLetter(key);
        this.curLetterIndex++;
      }
    }
    else if (key === 'Backspace') {
      const wordleLength = this.wordleLength[this.wordleOrder[this.currentWordleIndex]];
      const minIndex = this.numSubmittedTries * wordleLength;
      const maxIndex = (this.numSubmittedTries + 1) * wordleLength;

      if (this.curLetterIndex === minIndex) {
        this.setLetter('');
      } else if (this.curLetterIndex === maxIndex) {
        this.curLetterIndex--;
        this.setLetter('');
      } else {
        const tryIndex = Math.floor(this.curLetterIndex / wordleLength);
        const letterIndex = this.curLetterIndex % wordleLength;
        const currentLetter = this.tries[tryIndex].letters[letterIndex].text;

        if (currentLetter === '') {
          this.curLetterIndex--;
          this.setLetter('');
        } else {
          this.setLetter('');
          this.curLetterIndex--;
        }
      }
    } else if (key === 'Enviar' || key === 'Enter') {
      this.checkCurrentTry();
    }
  }

  selectCell(tryIndex: number, letterIndex: number) {
    const wordleLength = this.wordleLength[this.wordleOrder[this.currentWordleIndex]];
    const isInCurrentTry = tryIndex === this.numSubmittedTries;

    if (isInCurrentTry)
      this.curLetterIndex = tryIndex * wordleLength + letterIndex;
  }

  private setLetter(letter: string) {
    const tryIndex = Math.floor(this.curLetterIndex / this.wordleLength[this.wordleOrder[this.currentWordleIndex]]);
    const letterIndex = this.curLetterIndex % this.wordleLength[this.wordleOrder[this.currentWordleIndex]];

    this.tries[tryIndex].letters[letterIndex].text = letter;
  }

  private checkCurrentTry() {
    const curTry = this.tries[this.numSubmittedTries];
    const wordFromCurTry = curTry.letters.map((letter) => letter.text).join('').toUpperCase();
    if (!this.checkWordLenght(wordFromCurTry))
      return;

    if (this.contest.useDictionary) {
      const checkDictionary = this.contest.useExternalFile
        ? () => this.contestService.existsInExternalDictionary(wordFromCurTry, this.contest.id)
        : () => this.contestService.existsInDictionary(wordFromCurTry);

      checkDictionary().subscribe({
        next: (exists) => this.handleDictionaryCheckResult(exists, wordFromCurTry, curTry),
        error: (e) => {
          console.log("Error comprobando si existe la palabra", e);
          this.continueGameLogic(false, wordFromCurTry, curTry);
        }
      });
    } else {
      this.continueGameLogic(true, wordFromCurTry, curTry);
    }
  }

  private checkWordLenght(word: string) {
    if (word.length < this.wordleLength[this.wordleOrder[this.currentWordleIndex]]) {
      this.showInfoMessage('No hay suficientes letras');
      this.shakeTryContainer(this.numSubmittedTries);
      return false;
    }
    return true;
  }

  private handleDictionaryCheckResult(exists: Boolean, wordFromCurTry: string, curTry: any) {
    if (!exists) {
      this.showInfoMessage('La palabra no está en el diccionario');
      this.shakeTryContainer(this.numSubmittedTries);
      this.continueGameLogic(false, wordFromCurTry, curTry);
      return;
    }
    this.continueGameLogic(true, wordFromCurTry, curTry);
  }

  private shakeTryContainer(tryIndex: number) {
    const tryContainer = this.tryContainers.get(tryIndex)?.nativeElement as HTMLElement;
    if (tryContainer) {
      tryContainer.classList.add('shake');
      setTimeout(() => tryContainer.classList.remove('shake'), 500);
    }
  }

  private continueGameLogic(continueGame: boolean, wordFromCurTry: string, curTry: any) {
    if (!continueGame) {
      return;
    }

    this.lastWordle = wordFromCurTry.toUpperCase();

    this.wordleService.checkWordleAttempt(this.contest.id, wordFromCurTry, this.wordleOrder[this.currentWordleIndex], this.userEmail).subscribe({
      next: (checkStates) => {
        for (let i = 0; i < this.wordleLength[this.wordleOrder[this.currentWordleIndex]]; i++) {
          curTry.letters[i].state = checkStates[i];
          const key = curTry.letters[i].text.toLowerCase();
          const currentStoredState = this.curLetterStates[key];
          if (currentStoredState == null || checkStates[i] > currentStoredState) {
            this.curLetterStates[key] = checkStates[i];
          }
        }

        this.numSubmittedTries++;
        if (checkStates.every((state) => state === LetterState.FULL_MATCH)) {
          const currentGame = this.wordleState.games[this.wordleOrder[this.currentWordleIndex]];
          currentGame.finishTime = format(new Date(), 'yyyy-MM-dd HH:mm:ss');
          currentGame.timeNeeded = differenceInSeconds(currentGame.finishTime, currentGame.startTime);
          this.showInfoMessage('¡CORRECTO!');
          this.finished = true;
          this.won = true;
          this.hasMoreWords = this.currentWordleIndex < this.numWordles - 1;
        }

        if (this.numSubmittedTries === this.numTries && !this.won) {
          this.hasMoreWords = this.currentWordleIndex < this.numWordles - 1;
          this.finished = true;
          this.won = false;
          const currentGame = this.wordleState.games[this.wordleOrder[this.currentWordleIndex]];

          currentGame.finishTime = format(new Date(), 'yyyy-MM-dd HH:mm:ss');
          currentGame.timeNeeded = differenceInSeconds(currentGame.finishTime, currentGame.startTime);
          this.updateContestState();
          this.uploadNewLog();
        } else {
          this.updateContestState();
          this.uploadNewLog();
        }

      },
      error: (e) => {
        console.error('Error comprobando el wordle', e);
      }
    })
  }

  lostGame() {
    this.wordleService.getWordleInContest(this.contest.id, this.wordleOrder[this.currentWordleIndex]).subscribe({
      next: (wordle) => {
        this.showInfoMessage(`La palabra era: ${wordle.word.toUpperCase()}`);
      },
      error: (e) => {
        if (e.status === 404)
          console.error('No existe ese concurso', e);
        if (e.status === 401)
          console.error('No puedes realizar esa llamada', e);
        if (e.status === 400)
          console.error('No existe el usuario', e);
      }
    });
  }

  handleNextWord() {
    if (this.currentWordleIndex + 1 < this.numWordles) {
      this.wordleState.games[this.wordleOrder[this.currentWordleIndex + 1]].startTime = format(new Date(), 'yyyy-MM-dd HH:mm:ss');
      this.updateContestState();
      this.currentWordleIndex++;
      this.setTargetWord();
    }

    this.numSubmittedTries = 0;
    this.curLetterIndex = 0;
    this.finished = false;
  }

  private showInfoMessage(message: string, autoDismiss: boolean = true) {
    this.infoMsg = message;
    this.fadeOutInfoMessage = false;

    if (autoDismiss) {
      setTimeout(() => (this.fadeOutInfoMessage = true), 1500);
      setTimeout(() => (this.infoMsg = ''), 2000);
    }
  }

  private updateContestState(): void {
    const currentGame = this.wordleState.games[this.wordleOrder[this.currentWordleIndex]];

    currentGame.finished = this.finished;
    currentGame.won = this.won;
    currentGame.tryCount = this.numSubmittedTries;
    currentGame.lastWordle = this.lastWordle.toUpperCase();
    currentGame.timeGuess = format(new Date(), 'yyyy-MM-dd HH:mm:ss');

    const newState = new State();
    Object.values(this.curLetterStates).forEach((state) => {
      switch (state) {
        case LetterState.FULL_MATCH:
          newState.good += 1;
          break;
        case LetterState.PARTIAL_MATCH:
          newState.halfGood += 1;
          break;
        default:
          newState.wrong += 1;
          break;
      }
    });
    currentGame.state = newState;

    this.contestService.updateContestState(history.state.contestId, this.userName, this.wordleState).subscribe({
      next: () => {
        console.log('Estado del concurso actualizado correctamente');
        if (this.finished && !this.won)
          this.lostGame();
      },
      error: (error) => {
        console.error('Error actualizando el estado del concurso', error);
      },
    });
  }

  private uploadNewLog(): void {
    const counts = Object.values(this.curLetterStates).reduce(
      (acc, value) => {
        if (value === LetterState.WRONG) acc.wrong++;
        else if (value === LetterState.PARTIAL_MATCH) acc.partialMatch++;
        else if (value === LetterState.FULL_MATCH) acc.fullMatch++;
        return acc;
      },
      { wrong: 0, partialMatch: 0, fullMatch: 0 }
    );

    this.wordleStateLog = {
      userName: this.userName,
      email: this.userEmail,
      dateLog: format(new Date(), 'yyyy-MM-dd HH:mm:ss'),
      wordleToGuess: '',
      wordleInserted: this.lastWordle.toUpperCase(),
      numTry: this.numSubmittedTries,
      wordlePosition: this.wordleOrder[this.currentWordleIndex] + 1,
      correct: counts.fullMatch,
      wrongPosition: counts.partialMatch,
      wrong: counts.wrong,
      state: this.won
    };
    this.contestService.createContestLog(this.contest.id, this.wordleOrder[this.currentWordleIndex], this.userName, this.wordleStateLog).subscribe({
      next: () => {
        console.log('Creado nuevo log correctamente');
        if (this.finished && !this.won)
          this.lostGame();
      },
      error: (error) => {
        console.error('Error creando el nuevo log del concurso', error);
      },
    });
  }

  deleteProfessorState() {
    this.contestService.deleteProfessorState(this.contest.id, this.userName).subscribe({
      next: () => {
        console.log('Estado y logs eliminados correctamente');
        this.router.navigate([this.contest.id + '/concurso'], { state: { competitionName: this.competitionName, professorName: this.professorName } });
      },
      error: (e) => {
        console.error('Error borrando el estado y los logs', e);
      }
    });
  }

  resumeGame() {
    this.contestService.resumeContest(this.contest.id, this.userName).subscribe({
      next: (resumeContestDTO) => {
        this.curLetterIndex = resumeContestDTO.charPosition;
        this.numSubmittedTries = resumeContestDTO.tryPosition;
        this.currentWordleIndex = resumeContestDTO.wordlePosition;
        this.wordleOrder = resumeContestDTO.wordleOrder;
        this.wordleState = resumeContestDTO.wordleState;

        this.setTargetWord();
        if (resumeContestDTO.tries.length > 0) {
          for (let i = 0; i < resumeContestDTO.tries.length; i++) {
            const letters = resumeContestDTO.tries[i].letters;
            for (let j = 0; j < this.wordleLength[this.wordleOrder[this.currentWordleIndex]]; j++) {
              this.tries[i].letters[j].text = letters[j].letter;
              this.tries[i].letters[j].state = letters[j].state;
              this.curLetterStates[letters[j].letter.toLowerCase()] = letters[j].state;
            }
          }
        }
      },
      error: (e) => {
        console.error('Error reanudando el concurso', e);
      }
    });
  }

  isAccentKey(key: string): boolean {
    return ['Á', 'É', 'Í', 'Ó', 'Ú'].includes(key)
  }

  getCurLetterIndex(): number {
    return this.curLetterIndex;
  }

  updateTimeToFinish() {
    const difference = this.endTime.getTime() - this.rightNow.getTime();

    if (difference > 0) {
      const totalSeconds = Math.floor(difference / 1000);
      const min = Math.floor(totalSeconds / 60);
      const sec = totalSeconds % 60;

      this.timeToFinish = `${this.padZero(min)}:${this.padZero(sec)}`;
    } else {
      this.timeToFinish = '00:00';
      clearInterval(this.intervalId);
      alert('¡Se acabó el tiempo!');
      this.navigateToStatistics();
    }
  }

  padZero(num: number): string {
    return num < 10 ? '0' + num : num.toString();
  }

  navigateToStatistics() {
    this.router.navigate([this.contest.id + '/concurso'], { state: { competitionName: this.competitionName, activeTab: 'stats', professorName: this.professorName, competitionId: this.competitionId } });
  }

  goBack() {
    this.router.navigate([this.contest.id + '/concurso'], { state: { competitionName: this.competitionName, professorName: this.professorName, competitionId: this.competitionId } });
  }
}