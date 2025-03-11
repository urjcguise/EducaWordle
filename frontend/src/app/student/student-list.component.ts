import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { CompetitionService } from '../service/competition.service';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  professorName: string = '';

  students: User[] = [];
  competitionId!: number;

  selectedFile: File | null = null;

  currentTab: string = 'add-student';

  constructor(private competitionService: CompetitionService, private router: Router, private route: ActivatedRoute) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.router.navigate(['/competiciones'], { state: { professorName: this.professorName } });
        }
      }
    });
  }

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
    this.professorName = history.state.professorName;
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
    const competitionName = this.route.snapshot.paramMap.get('competitionName');
    this.router.navigate(['/nuevoAlumno'], { state: { competitionId: this.competitionId, professorName: this.professorName, competitionName: competitionName } });
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
        alert('Alumnos creados correctamente');
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
