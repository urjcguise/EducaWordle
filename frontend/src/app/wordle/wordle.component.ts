import { Component, Input } from '@angular/core';
import { WordleService } from '../service/wordle.service';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-wordle',
  templateUrl: './wordle.component.html',
  styleUrls: ['./wordle.component.css']
})
export class WordleComponent {

  @Input() folderId!: number;
  @Input() professorName!: string;

  wordles: string[] = [''];

  constructor(private wordleService: WordleService, private route: ActivatedRoute, private router: Router, private tokenService: TokenService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  addWordle() {
    if (this.wordles.length < 10) {
      this.wordles.push('');
    }
  }

  removeWordle(index: number) {
    this.wordles.splice(index, 1);
  }

  trackByIndex(index: number): number {
    return index;
  }

  createWordles() {
    let posMoreThanOneWord: number[] = []
    let moreThanTen: boolean = false;
    for (let i = 0; i < this.wordles.length; i++) {
      const word = this.wordles[i];
      if (word == "") {
        alert('No se puede guardar un wordle vacío');
        return;
      }
      if (word.trim().split(/\s+/).length > 1) {
        posMoreThanOneWord.push(i);
      } 
      if (word.trim().length >= 10)
        moreThanTen = true;
    }

    if (moreThanTen)
      alert('Las palabras demasiado largas pueden afectar la visualización en dispositivos móviles. Se recomienda usar palabras de hasta 10 caracteres');

    if (posMoreThanOneWord.length > 0) {
      const confirmSave = confirm(`No se puede guardar un Wordle con espacios, se procederá a unir las palabras`);
      if (!confirmSave)
        return;

      posMoreThanOneWord.forEach((pos) => {
        this.wordles[pos] = this.wordles[pos].replace(/\s+/g, '');
      });
    }

    this.wordleService.saveWordles(this.wordles, 0, this.professorName, this.folderId).subscribe({
      next: () => {
        alert('Wordles creados correctamente');
      },
      error: (e) => {
        console.error('Error guardando los wordle', e);
      }
    });
  }

  goBack() {
    this.router.navigate(['/wordles'], { state: { professorName: this.professorName } });
  }
}
