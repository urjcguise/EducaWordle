import { Component } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { Wordle } from '../models/wordle';
import { WordleService } from '../service/wordle.service';
import { Competition } from '../models/competition';
import { TokenService } from '../service/token.service';
import { Observable, of, switchMap } from 'rxjs';

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
  wordles: Wordle[] = [];
  initialWordles: Wordle[] = [];
  numTries: number = 0;

  formattedStartDate: string = "";
  formattedEndDate: string = "";

  competitionName: string = '';

  constructor(private route: ActivatedRoute, private contestService: ContestService, private wordleService: WordleService, private tokenService: TokenService, private router: Router) {
    this.competitionName = history.state.competitionName;
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.router.navigate([this.competitionName + '/concursos'], { state: { professorName: this.professorName } });
        }
      }
    });
  }

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
          this.wordles = [...data];
          this.initialWordles = [...data];
        } else {
          this.wordles = [];
          this.initialWordles = [];
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
      this.wordles.push(new Wordle(''));
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
      if (word.word == "") {
        alert('No se puede guardar un wordle vacío');
        return;
      }
    }

    const commonWordles = this.wordles.filter(wordle => this.initialWordles.includes(wordle));
    const removedWordles = this.initialWordles.filter(wordle => !this.wordles.includes(wordle));
    const addedWordles = this.wordles.filter(wordle => !this.initialWordles.includes(wordle));

    this.deleteWordles(removedWordles);

    this.saveWordles(addedWordles).pipe(
      switchMap(() => {
        const updatedWordles: Wordle[] = [...commonWordles, ...addedWordles];

        const updatedContest: Contest = {
          ...this.contest,
          contestName: this.contest.contestName,
          startDate: new Date(this.formattedStartDate),
          endDate: new Date(this.formattedEndDate),
          numTries: this.numTries,
          useDictionary: this.dictionary,
          useExternalFile: this.file
        };

        return this.contestService.editContest(updatedContest, updatedWordles);
      })
    ).subscribe({
      next: () => {
        alert('Concurso guardado con éxito');
        this.ngOnInit();
      },
      error: (err) => console.error('Error al editar concurso', err)
    });
  }

  deleteWordles(wordlesToDelete: Wordle[]) {
    if (wordlesToDelete.length == 0)
      return;
    this.wordleService.deleteWordles(wordlesToDelete).subscribe({
      next: () => {
        console.log('Wordles eliminados con éxito');
      },
      error: (err) => console.error('Error al eliminar Wordles', err)
    });
  }

  saveWordles(wordlesToSave: Wordle[]): Observable<any> {
    if (wordlesToSave.length == 0)
      return of(null);
    const nameWordles = wordlesToSave.map(w => w.word);
    return this.wordleService.saveWordles(nameWordles, this.contest.id, this.professorName, 0);
  }

  trackByIndex(index: number): number {
    return index;
  }

  removeWordle(index: number) {
    this.wordles.splice(index, 1);
  }
}