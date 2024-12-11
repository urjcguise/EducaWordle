import { Component } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { Wordle } from '../models/wordle';
import { WordleService } from '../service/wordle.service';
import { Competition } from '../models/competition';

@Component({
  selector: 'app-edit-contest',
  templateUrl: './edit-contest.component.html',
  styleUrls: ['./edit-contest.component.css']
})
export class EditContestComponent {
  
  contest: Contest = new Contest("", new Date(), new Date(), false, false, "", new Competition(""), []);
  contestName = "";
  dictionary: boolean = false;
  file: boolean = false;
  wordles: string[] = [];

  formattedStartDate: string = "";
  formattedEndDate: string = "";

  competition!: Competition;

  constructor(private route: ActivatedRoute, private contestService: ContestService, private wordleService: WordleService) { }

  ngOnInit(): void {
    const contestNameParam = this.route.snapshot.paramMap.get('contestName');
    this.contestName = contestNameParam || '';
    this.loadContest();
    this.getWordles();
  }

  loadContest(): void {
    this.contestService.getContestByName(this.contestName).subscribe({
      next: (data: Contest) => {
        this.contest = data;
        this.dictionary = data.useDictionary || false;
        this.file = data.useExternalFile || false;
        this.formattedStartDate = this.formatDateForInput(this.contest.startDate);
        this.formattedEndDate = this.formatDateForInput(this.contest.endDate);
      },
      error: (err) => console.error('Error al cargar el concurso', err)
    });
  }

  getWordles(): void {
    this.wordleService.getWordles(this.contestName).subscribe({
      next: (data: Wordle[]) => {
        if (data && data.length > 0) {
          this.wordles = data.map((elem) => elem.word);
        } else {
          this.wordles = [''];
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
        this.contestService.saveExternalDictionary(toSave, this.contestName).subscribe({
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

    const updatedContest: Contest = {
      ...this.contest,
      contestName: this.contest.contestName,
      startDate: new Date(this.formattedStartDate),
      endDate: new Date(this.formattedEndDate),
      useDictionary: this.dictionary,
      useExternalFile: this.file,
      wordles: []
    };
    this.contestService.editContest(this.contestName, updatedContest).subscribe({
      next: () => {
        if (this.wordles.length > 0)
          this.saveWordles();
        else
        alert('Concurso guardado con éxito');
      },
      error: (err) => console.error('Error al editar concurso', err)
    });
  }

  saveWordles() {
    this.wordleService.saveWordles(this.wordles, this.contest.contestName).subscribe({
      next: () => {
        alert('Concurso y Wordles guardados con éxito');
      },
      error: (err) => console.error('Error al editar Wordles', err)
    });
  }

  trackByIndex(index: number, item: any): number {
    return index;
  }  

  removeWordle(index: number) {
    this.wordles.splice(index, 1);
}

}
