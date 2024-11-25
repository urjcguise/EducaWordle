import { Component, ElementRef, HostListener, QueryList, ViewChild, ViewChildren } from '@angular/core';

import { WORDS } from './words'; // Archivo como diccionario
import { WordleService } from '../service/wordle.service';
import { Wordle } from '../models/wordle';
import { ContestService } from '../service/contest.service';
import { Contest } from '../models/contest';
import { Router } from '@angular/router';



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
  @ViewChildren('tryContainer') tryContainers!: QueryList<ElementRef>;

  readonly tries: Try[] = [];
  readonly LetterState = LetterState;
  readonly NUM_TRIES = 6; // Número de intentos permitido

  readonly keyboardRows = [
    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ñ'],
    ['Enviar', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Backspace'],
  ];

  readonly curLetterStates: { [key: string]: LetterState } = {};
  infoMsg = '';
  fadeOutInfoMessage = false;

  private curLetterIndex = 0;
  private targetWords: string[] = []; // Lista de palabras objetivo
  private targetWord = '';
  private targetWordLetterCounts: { [letter: string]: number } = {};

  numSubmittedTries = 0;
  currentWordleIndex = 0; // Índice de la palabra actual
  won = false;
  hasMoreWords = true;

  wordleList: Wordle[] = [];
  contest!: Contest;

  dictionary: string[] = [];

  constructor(private wordleService: WordleService, private contestService: ContestService) {
    this.wordleService.getWordles(history.state.contestName).subscribe({
      next: (wrdl) => {
        this.wordleList = wrdl;
        this.targetWords = this.wordleList.map((wordle) => wordle.word); // Cargar todas las palabras
        if (this.targetWords.length > 0) {
          this.setTargetWord();
        }
      },
      error: (error) => {
        console.error('Error consiguiendo los wordle', error);
      },
    });

    this.contestService.getContestByName(history.state.contestName).subscribe({
      next: (cont) => {
        this.contest = cont;
        this.initializeDictionary();
      },
      error: (error) => {
        console.error('Error consiguiendo el concurso', error);
      },
    });

    for (let i = 0; i < this.NUM_TRIES; i++) {
      const letters: Letter[] = [];
      for (let j = 0; j < 5; j++) {
        letters.push({ text: '', state: LetterState.PENDING });
      }
      this.tries.push({ letters });
    }
  }

  private initializeDictionary() {
    if (this.contest.useExternalFile) {
      this.loadExternalFile(this.contest.fileRoute).then((fileWords) => {
        this.dictionary = fileWords;
      });
    } else if (this.contest.useDictionary) {
      this.dictionary = WORDS;
    }
  }

  private async loadExternalFile(fileRoute: string): Promise<string[]> {
    try {
      const response = await fetch(fileRoute);
      if (response.ok) {
        const fileContent = await response.text();
        return fileContent
          .split('\n')
          .map((word) => word.trim().toUpperCase())
          .filter((word) => word.length > 0);
      } else {
        this.showInfoMessage('Error al cargar el archivo externo');
        return [];
      }
    } catch (error) {
      console.error('Error leyendo el archivo externo:', error);
      this.showInfoMessage('No se pudo leer el archivo externo');
      return [];
    }
  }

  private setTargetWord() {
    if (this.currentWordleIndex < this.targetWords.length) {
      this.resetKeyboard();
      this.targetWord = this.targetWords[this.currentWordleIndex].toLowerCase();
      this.targetWordLetterCounts = {};
      for (const letter of this.targetWord) {
        this.targetWordLetterCounts[letter] = (this.targetWordLetterCounts[letter] || 0) + 1;
      }

      // Ajustar longitud dinámica de intentos
      for (let tryData of this.tries) {
        tryData.letters = Array.from({ length: this.targetWord.length }, () => ({
          text: '',
          state: LetterState.PENDING,
        }));
      }

      console.log('Palabra objetivo:', this.targetWord);
    } else {
      this.showInfoMessage('¡Has completado todas las palabras!');
      this.won = true;
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
    if (this.won) return;

    if (key.length === 1 && /^[a-zñ]$/i.test(key)) {
      if (this.curLetterIndex < (this.numSubmittedTries + 1) * this.targetWord.length) {
        this.setLetter(key);
        this.curLetterIndex++;
      }
    } else if (key === 'Backspace') {
      if (this.curLetterIndex > this.numSubmittedTries * this.targetWord.length) {
        this.curLetterIndex--;
        this.setLetter('');
      }
    } else if (key === 'Enviar' || key === 'Enter') {
      this.checkCurrentTry();
    }
  }

  private setLetter(letter: string) {
    const tryIndex = Math.floor(this.curLetterIndex / this.targetWord.length);
    const letterIndex = this.curLetterIndex % this.targetWord.length;
    this.tries[tryIndex].letters[letterIndex].text = letter;
  }

  private async checkCurrentTry() {
    const curTry = this.tries[this.numSubmittedTries];
    if (curTry.letters.some((letter) => letter.text === '')) {
      this.showInfoMessage('No hay suficientes letras');
      return;
    }

    const wordFromCurTry = curTry.letters.map((letter) => letter.text).join('').toUpperCase();
    if (this.contest.useDictionary && !this.dictionary.includes(wordFromCurTry)) {
      this.showInfoMessage('La palabra no está en el diccionario');
      const tryContainer = this.tryContainers.get(this.numSubmittedTries)?.nativeElement as HTMLElement;
      tryContainer.classList.add('shake');
      setTimeout(() => tryContainer.classList.remove('shake'), 500);
      return;
    }

    const targetWordLetterCounts = { ...this.targetWordLetterCounts };
    const states: LetterState[] = [];

    for (let i = 0; i < this.targetWord.length; i++) {
      const expected = this.targetWord[i];
      const got = curTry.letters[i].text.toLowerCase();

      if (expected === got && targetWordLetterCounts[got] > 0) {
        states[i] = LetterState.FULL_MATCH;
        targetWordLetterCounts[got]--;
      } else {
        states[i] = LetterState.PENDING;
      }
    }

    for (let i = 0; i < this.targetWord.length; i++) {
      const got = curTry.letters[i].text.toLowerCase();
      if (states[i] === LetterState.PENDING && this.targetWord.includes(got) && targetWordLetterCounts[got] > 0) {
        states[i] = LetterState.PARTIAL_MATCH;
        targetWordLetterCounts[got]--;
      }
    }

    for (let i = 0; i < this.targetWord.length; i++) {
      if (states[i] === LetterState.PENDING) {
        states[i] = LetterState.WRONG;
      }
    }

    for (let i = 0; i < this.targetWord.length; i++) {
      curTry.letters[i].state = states[i];
      const key = curTry.letters[i].text.toLowerCase();
      const currentStoredState = this.curLetterStates[key];
      if (currentStoredState == null || states[i] > currentStoredState) {
        this.curLetterStates[key] = states[i];
      }
    }

    this.numSubmittedTries++;
    if (states.every((state) => state === LetterState.FULL_MATCH)) {
      this.showInfoMessage('¡CORRECTO!');
      this.won = true;
      this.hasMoreWords = this.currentWordleIndex < this.wordleList.length - 1;
      return;
    }

    if (this.numSubmittedTries === this.NUM_TRIES) {
      this.showInfoMessage(`La palabra era: ${this.targetWord.toUpperCase()}`);
      this.hasMoreWords = this.currentWordleIndex < this.wordleList.length - 1;
    }
  }

  handleNextWord() {
    this.numSubmittedTries = 0;
    this.curLetterIndex = 0;
    this.won = false;

    if (this.currentWordleIndex + 1 < this.targetWords.length) {
      this.currentWordleIndex++;
      this.setTargetWord();
    } else {
      this.hasMoreWords = false;
      this.showInfoMessage('¡Has completado todas las palabras!');
    }
  }

  private showInfoMessage(message: string, autoDismiss: boolean = true) {
    this.infoMsg = message;
    this.fadeOutInfoMessage = false;

    if (autoDismiss) {
      setTimeout(() => (this.fadeOutInfoMessage = true), 1500);
      setTimeout(() => (this.infoMsg = ''), 2000);
    }
  }
}

