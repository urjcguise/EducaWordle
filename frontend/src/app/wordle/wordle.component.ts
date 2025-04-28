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
    for (const word of this.wordles) {
      if (word == "") {
        alert('No se puede guardar un wordle vacío');
        return;
      }
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
