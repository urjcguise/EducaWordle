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
  competitionName: string = '';

  students: User[] = [];
  competitionId!: number;

  selectedFile: File | null = null;

  currentTab: string = 'add-student';

  showModal: boolean = false;

  constructor(private competitionService: CompetitionService, private router: Router, private route: ActivatedRoute) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
    this.professorName = history.state.professorName;
    this.competitionName = this.route.snapshot.paramMap.get('competitionName') || '';
    this.competitionService.getStudentsByCompetition(this.competitionName).subscribe({
      next: (stdts) => {
        this.students = stdts;
      },
      error: (error) => {
        console.error('Error consiguiendo los alumnos', error);
      }
    });
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

  openStudentModal(): void {
    this.showModal = true;
  }

  closeStudentModal(): void {
    this.showModal = false;
  }

  closeModalOnBackdropClick(event: MouseEvent) {
    if (event.target === event.currentTarget)
      this.closeStudentModal();
  }

  goBack() {
    this.router.navigate(['/competiciones'], { state: { professorName: this.professorName } });
  }
}
