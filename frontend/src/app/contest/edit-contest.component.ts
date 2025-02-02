import { Component } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { Wordle } from '../models/wordle';
import { WordleService } from '../service/wordle.service';
import { Competition } from '../models/competition';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-edit-contest',
  templateUrl: './edit-contest.component.html',
  styleUrls: ['./edit-contest.component.css']
})
export class EditContestComponent {

  professorName: string = '';

  contest: Contest = new Contest("", new Date(), new Date(), 0, false, false, "", new Competition(""), []);
  contestId: number = 0;
  dictionary: boolean = false;
  file: boolean = false;
  wordles: string[] = [];
  initialWordles: string[] = [];
  numTries: number = 0;

  formattedStartDate: string = "";
  formattedEndDate: string = "";

  competition!: Competition;

  constructor(private route: ActivatedRoute, private contestService: ContestService, private wordleService: WordleService, private tokenService: TokenService) { }

  ngOnInit(): void {
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.professorName = this.tokenService.getUserName()!;
    this.contestId = Number(this.route.snapshot.paramMap.get('contestId'));
    this.loadContest();
    this.getWordles();
  }

  loadContest(): void {
    this.contestService.getContestById(this.contestId).subscribe({
      next: (data: Contest) => {
        this.contest = data;
        this.dictionary = data.useDictionary || false;
        this.file = data.useExternalFile || false;
        this.numTries = data.numTries;
        this.formattedStartDate = this.formatDateForInput(this.contest.startDate);
        this.formattedEndDate = this.formatDateForInput(this.contest.endDate);
      },
      error: (err) => console.error('Error al cargar el concurso', err)
    });
  }

  getWordles(): void {
    this.wordleService.getWordlesByContest(this.contestId).subscribe({
      next: (data: Wordle[]) => {
        if (data && data.length > 0) {
          this.wordles = data.map((elem) => elem.word);
          this.initialWordles = data.map((elem) => elem.word);
        } else {
          this.wordles = [''];
          this.initialWordles = [''];
        }
      },
      error: (err) => console.error('Error al obtener Wordles', err)
    });
  }

  formatDateForInput(date: Date | string): string {
    const d = new Date(date);
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    const hours = String(d.getHours()).padStart(2, '0');
    const minutes = String(d.getMinutes()).padStart(2, '0');
    return `${year}-${month}-${day}T${hours}:${minutes}`;
  }

  addWordle() {
    if (this.wordles.length < 3) {
      this.wordles.push('');
    }
  }

  useDictionary(value: boolean) {
    this.dictionary = value;
    this.contest.useDictionary = value;
    if (!value) {
      this.file = false;
    }
  }

  useFile(value: boolean) {
    this.file = value;
  }

  selectFile(event: any) {
    const file: File = event.target.files[0];
    var toSave: string[] = [];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const content = reader.result as string;
        toSave.push(...content.split('\n').map(line => line.trim()).filter(line => line !== ""));
        this.contestService.saveExternalDictionary(toSave, this.contestId).subscribe({
          next: () => {
            console.log("Palabras guardadas correctamente en el diccionario");
          },
          error: (e) => {
            console.log("Error guardando las palabras del diccionario: ", e);
          }
        });
      }
      reader.readAsText(file);
    }
  }

  updateContest() {
    for (const word of this.wordles) {
      if (word == "") {
        alert('No se puede guardar un wordle vacío');
        return;
      }
    }

    const commonWordles = this.wordles.filter(wordle => this.initialWordles.includes(wordle));
    const removedWordles = this.initialWordles.filter(wordle => !this.wordles.includes(wordle));
    const addedWordles = this.wordles.filter(wordle => !this.initialWordles.includes(wordle));

    this.deleteWordles(removedWordles);
    this.saveWordles(addedWordles);

    const updatedWordles: Wordle[] = [...commonWordles, ...addedWordles].map(word => ({ word }));

    const updatedContest: Contest = {
      ...this.contest,
      contestName: this.contest.contestName,
      startDate: new Date(this.formattedStartDate),
      endDate: new Date(this.formattedEndDate),
      useDictionary: this.dictionary,
      useExternalFile: this.file,
      wordles: updatedWordles
    };
    this.contestService.editContest(this.contestId, updatedContest).subscribe({
      next: () => {
        alert('Concurso guardado con éxito');
      },
      error: (err) => console.error('Error al editar concurso', err)
    });
  }

  deleteWordles(wordlesToDelete: string[]) {
    if (wordlesToDelete.length == 0)
      return;
    this.wordleService.deleteWordles(wordlesToDelete).subscribe({
      next: () => {
        console.log('Wordles eliminados con éxito');
      },
      error: (err) => console.error('Error al eliminar Wordles', err)
    });
  }

  saveWordles(wordlesToSave: string[]) {
    if (wordlesToSave.length == 0)
      return;
    this.wordleService.saveWordles(wordlesToSave, this.contest.id, this.professorName, 0).subscribe({
      next: () => {
        alert('Wordles guardados con éxito');
        this.initialWordles = [...this.wordles];
      },
      error: (err) => console.error('Error al editar Wordles', err)
    });
  }

  trackByIndex(index: number): number {
    return index;
  }

  removeWordle(index: number) {
    this.wordles.splice(index, 1);
  }
}