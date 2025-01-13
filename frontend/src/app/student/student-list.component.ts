import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { CompetitionService } from '../service/competition.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: User[] = [];
  competitionId!: number;

  selectedFile: File | null = null;

  currentTab: string = 'add-student';

  constructor(private competitionService: CompetitionService, private router: Router) { }

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
    this.competitionService.getStudentsByCompetition(this.competitionId).subscribe({
      next: (stdts) => {
        this.students = stdts;
      },
      error: (error) => {
        console.error('Error consiguiendo los alumnos', error);
      }
    });
  }

  addStudent(): void {
    this.router.navigate(['/nuevoAlumno'], { state: { competitionId: this.competitionId } });
  }

  setTab(tab: string) {
    this.currentTab = tab;
  }

  addStudentExcel() {
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append('file', this.selectedFile!);

    this.competitionService.addByExcel(this.competitionId, formData).subscribe({
      next: () => {
        console.log('Alumnos creados correctamente');
      },
      error: (error) => {
        console.error('Error creando los alumnos', error);
      }
    });
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
}
