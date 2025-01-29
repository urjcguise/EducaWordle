import { Component, OnInit } from '@angular/core';
import { WordleService } from '../service/wordle.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-wordle',
  templateUrl: './wordle.component.html',
  styleUrls: ['./wordle.component.css']
})
export class WordleComponent implements OnInit {

  wordles: string[] = [''];
  professorName: string = '';
  folderId: number = 0;

  constructor(private wordleService: WordleService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.folderId = history.state.folderId;
    this.professorName = this.route.snapshot.paramMap.get('professorName') || '';
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

    this.wordleService.saveWordles(this.wordles, 'empty', this.professorName, this.folderId).subscribe({
      next: () => {
        alert('Wordles creados correctamente');
      },
      error: (e) => {
        console.error('Error guardando los wordle', e);
      }
    });
  }
}
