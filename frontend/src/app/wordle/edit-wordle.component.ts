import { Component } from '@angular/core';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute } from '@angular/router';
import { Contest } from '../models/contest';
import { Wordle } from '../models/wordle';

@Component({
  selector: 'app-edit-wordle',
  templateUrl: './edit-wordle.component.html',
  styleUrls: ['./edit-wordle.component.css']
})
export class EditWordleComponent {

  contest: Contest = new Contest("", new Date(), new Date(), false, false, "", 0, []);
  contestName = "";
  dictionary: boolean = false;
  file: boolean = false;
  fileRoute = "";
  competitionId!: number;
  wordles: Wordle[] = [{ word: '' }];

  formattedStartDate: string = "";
  formattedEndDate: string = "";

  constructor(private route: ActivatedRoute, private contestService: ContestService) { }

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
    const contestNameParam = this.route.snapshot.paramMap.get('contestName');
    this.contestName = contestNameParam || '';
    this.loadContest();
  }

  loadContest(): void {
    this.contestService.getContestByName(this.contestName).subscribe({
      next: (data: Contest) => {
        this.contest = data;
        this.wordles = data.wordles || [{ word: '' }];
        this.dictionary = data.useDictionary || false;
        this.file = data.useExternalFile || false;
        this.fileRoute = data.fileRoute || "";
        this.formattedStartDate = this.formatDateForInput(this.contest.startDate);
        this.formattedEndDate = this.formatDateForInput(this.contest.endDate);
      },
      error: (err) => console.error('Error al cargar concurso', err)
    });
  }

  private formatDateForInput(date: Date | string): string {
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
      this.wordles.push({ word: '' });
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
    if (file) {
      this.fileRoute = file.name; // Aquí se asigna solo el nombre, pero podrías manejar la subida del archivo
      this.contest.fileRoute = this.fileRoute;
    }
  }

  updateContest() {
    const updatedContest: Contest = {
      ...this.contest,
      contestName: this.contest.contestName,
      startDate: new Date(this.formattedStartDate),
      endDate: new Date(this.formattedEndDate),
      useDictionary: this.dictionary,
      useExternalFile: this.file,
      fileRoute: this.fileRoute,
      competitionId: this.competitionId,
      wordles: this.wordles
    };
    this.contestService.editContest(this.contestName, updatedContest).subscribe({
      next: () => {
        alert('Concurso editado con éxito');
      },
      error: (err) => console.error('Error al editar concurso', err)
    });
  }
}

