import { Component } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { WordleService } from '../service/wordle.service';
import { Competition } from '../models/competition';

@Component({
  selector: 'app-edit-contest',
  templateUrl: './edit-contest.component.html',
  styleUrls: ['./edit-contest.component.css']
})
export class EditContestComponent {

  professorName: string = '';

  contest: Contest = new Contest("", new Date(), new Date(), 0, false, false, new Competition(""), []);
  contestId: number = 0;
  dictionary: boolean = false;
  file: boolean = false;
  numTries: number = 0;

  formattedStartDate: string = "";
  formattedEndDate: string = "";

  competitionName: string = '';

  constructor(private route: ActivatedRoute, private contestService: ContestService, private wordleService: WordleService, private router: Router) {
    this.competitionName = history.state.competitionName;
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.professorName = history.state.professorName;
    this.contestId = Number(this.route.snapshot.paramMap.get('contestId'));
    this.loadContest();
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

  formatDateForInput(date: Date | string): string {
    const d = new Date(date);
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    const hours = String(d.getHours()).padStart(2, '0');
    const minutes = String(d.getMinutes()).padStart(2, '0');
    return `${year}-${month}-${day}T${hours}:${minutes}`;
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
    const updatedContest: Contest = {
      ...this.contest,
      contestName: this.contest.contestName,
      startDate: new Date(this.formattedStartDate),
      endDate: new Date(this.formattedEndDate),
      numTries: this.numTries,
      useDictionary: this.dictionary,
      useExternalFile: this.file
    };

    this.contestService.editContest(updatedContest).subscribe({
      next: () => {
        alert('Concurso guardado con éxito');
        this.ngOnInit();
      },
      error: (err) => console.error('Error al editar concurso', err)
    });
  }

  goBack() {
    this.router.navigate([Number(this.route.snapshot.paramMap.get('contestId')) + '/concurso'], { state: { professorName: this.professorName } });
  }
}