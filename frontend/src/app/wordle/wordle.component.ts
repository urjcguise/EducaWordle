import {Component, ElementRef, HostListener, QueryList, ViewChild, ViewChildren} from '@angular/core';

import {WORDS} from './words';


const WORD_LENGTH = 5;

const NUM_TRIES = 6;

// Letter map.
const LETTERS = (() => {
  const ret: {[key: string]: boolean} = {};
  for (let charCode = 97; charCode < 97 + 26; charCode++) {
    ret[String.fromCharCode(charCode)] = true;
  }
  return ret;
})();

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
  templateUrl: './wordle.component.html',
  styleUrls: ['./wordle.component.css'],
})
export class WordleComponent {
  @ViewChildren('tryContainer') tryContainers!: QueryList<ElementRef>;

  readonly tries: Try[] = [];

  readonly LetterState = LetterState;

  readonly keyboardRows = [
    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ñ'],
    ['Enviar', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Backspace'],
  ];

  readonly curLetterStates: {[key: string]: LetterState} = {};

  infoMsg = '';

  fadeOutInfoMessage = false;

  private curLetterIndex = 0;

  private numSubmittedTries = 0;

  private targetWord = '';

  private won = false;

  private targetWordLetterCounts: {[letter: string]: number} = {};

  constructor() {
    for (let i = 0; i < NUM_TRIES; i++) {
      const letters: Letter[] = [];
      for (let j = 0; j < WORD_LENGTH; j++) {
        letters.push({text: '', state: LetterState.PENDING});
      }
      this.tries.push({letters});
    }

    const numWords = WORDS.length;
    while (true) {
      const index = Math.floor(Math.random() * numWords);
      const word = WORDS[index];
      if (word.length === WORD_LENGTH) {
        this.targetWord = word.toLowerCase();
        break;
      }
    }
    // Eliminar luego, esto es para saber la palabra
    console.log('target word: ', this.targetWord);

    // Obtain letters in the word
    for (const letter of this.targetWord) {
      const count = this.targetWordLetterCounts[letter];
      if (count == null) {
        this.targetWordLetterCounts[letter] = 0;
      }
      this.targetWordLetterCounts[letter]++;
    }
    console.log(this.targetWordLetterCounts);
  }

  @HostListener('document:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    this.handleClickKey(event.key);
  }

  // Returns the classes for the given keyboard key based on its state.
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
    if (this.won) {
      return;
    }

    if (LETTERS[key.toLowerCase()]) {
      if (this.curLetterIndex < (this.numSubmittedTries + 1) * WORD_LENGTH) {
        this.setLetter(key);
        this.curLetterIndex++;
      }
    }
    
    else if (key === 'Backspace') {
      if (this.curLetterIndex > this.numSubmittedTries * WORD_LENGTH) {
        this.curLetterIndex--;
        this.setLetter('');
      }
    }
    
    else if (key === 'Enviar' || key === "Enter") {
      this.checkCurrentTry();
    }
  }

  private setLetter(letter: string) {
    const tryIndex = Math.floor(this.curLetterIndex / WORD_LENGTH);
    const letterIndex = this.curLetterIndex - tryIndex * WORD_LENGTH;
    this.tries[tryIndex].letters[letterIndex].text = letter;
  }

  private async checkCurrentTry() {
    const curTry = this.tries[this.numSubmittedTries];
    if (curTry.letters.some(letter => letter.text === '')) {
      this.showInfoMessage('No hay suficientes letras');
      return;
    }

    const wordFromCurTry =
        curTry.letters.map(letter => letter.text).join('').toUpperCase();
    if (!WORDS.includes(wordFromCurTry)) {
      this.showInfoMessage('La palabra no está en la lista');
      // Shake the row
      const tryContainer =
          this.tryContainers.get(this.numSubmittedTries)?.nativeElement as
          HTMLElement;
      tryContainer.classList.add('shake');
      setTimeout(() => {
        tryContainer.classList.remove('shake');
      }, 500);
      return;
    }

    // Check if the current try matches the target word and stores the check results.
    const targetWordLetterCounts = {...this.targetWordLetterCounts};
    const states: LetterState[] = [];
    for (let i = 0; i < WORD_LENGTH; i++) {
      const expected = this.targetWord[i];
      const curLetter = curTry.letters[i];
      const got = curLetter.text.toLowerCase();
      let state = LetterState.WRONG;

      if (expected === got && targetWordLetterCounts[got] > 0) {
        targetWordLetterCounts[expected]--;
        state = LetterState.FULL_MATCH;
      } else if (this.targetWord.includes(got) && targetWordLetterCounts[got] > 0) {
        targetWordLetterCounts[got]--
        state = LetterState.PARTIAL_MATCH;
      }
      states.push(state);
    }
    // Esto se puede borrar
    console.log(states);

    // Animate
    const tryContainer = this.tryContainers.get(this.numSubmittedTries)?.nativeElement as HTMLElement;
    const letterEles = tryContainer.querySelectorAll('.letter-container');
    for (let i = 0; i < letterEles.length; i++) {
      const curLetterEle = letterEles[i];
      curLetterEle.classList.add('fold');
      await this.wait(75);
      curTry.letters[i].state = states[i];
      curLetterEle.classList.remove('fold');
      await this.wait(75);
    }

    // Upload keyboard state
    for (let i = 0; i < WORD_LENGTH; i++) {
      const curLetter = curTry.letters[i];
      const got = curLetter.text.toLowerCase();
      const curStoredState = this.curLetterStates[got];
      const targetState = states[i];

      if (curStoredState == null || targetState > curStoredState) {
        this.curLetterStates[got] = targetState;
      }
    }

    this.numSubmittedTries++;

    // Check if all letters are correct
    if (states.every(state => state === LetterState.FULL_MATCH)) {
      this.showInfoMessage('¡CORRECTO!');
      this.won = true;
      for (let i = 0; i < letterEles.length; i++) {
        const curLetterEle = letterEles[i];
        curLetterEle.classList.add('bounce');
        await this.wait(160);
      }
      return;
    }

    // Show correct answer.
    if (this.numSubmittedTries === NUM_TRIES) {
      this.showInfoMessage(this.targetWord.toUpperCase(), false);
    }
  }

  private showInfoMessage(msg: string, hide = true) {
    this.infoMsg = msg;
    if (hide) {
      setTimeout(() => {
        this.fadeOutInfoMessage = true;
        setTimeout(() => {
          this.infoMsg = '';
          this.fadeOutInfoMessage = false;
        }, 500);
      }, 2000);
    }
  }

  private async wait(ms: number) {
    await new Promise<void>((resolve) => {
      setTimeout(() => {
        resolve();
      }, ms);
    })
  }
}