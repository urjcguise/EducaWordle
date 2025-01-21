import { Component, OnInit } from '@angular/core';
import { WordleService } from '../service/wordle.service';
import { TokenService } from '../service/token.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-wordle',
  templateUrl: './wordle.component.html',
  styleUrls: ['./wordle.component.css']
})
export class WordleComponent implements OnInit {

  wordles: string[] = [''];
  professorName: string = '';
  folderName!: string;

  constructor(private wordleService: WordleService, private tokenService: TokenService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (history.state.folderName == '')
      this.folderName = 'empty'
    else
      this.folderName = history.state.folderName;
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
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

  trackByIndex(index: number, item: any): number {
    return index;
  }

  createWordles() {
    for (const word of this.wordles) {
      if (word == "") {
        alert('No se puede guardar un wordle vacío');
        return;
      }
    }

    this.wordleService.saveWordles(this.wordles, 'empty', this.professorName, this.folderName).subscribe({
      next: () => {
        alert('Wordles creados correctamente');
      },
      error: (e) => {
        console.error('Error guardando los wordle', e);
      }
    });
  }
}
