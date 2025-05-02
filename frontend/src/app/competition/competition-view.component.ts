import { Component, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { ContestService } from '../service/contest.service';
import { CompetitionService } from '../service/competition.service';

@Component({
  selector: 'app-competition-view',
  templateUrl: './competition-view.component.html',
  styleUrls: ['./competition-view.component.css']
})
export class CompetitionViewComponent implements OnInit {

  isProfessor: boolean = false;
  professorName: string = '';

  competitionName: string = '';
  competitionId: number = 0;
  contests: Contest[] = [];

  activeTab: string = 'contests';

  isEditingName: boolean = false;
  tempCompetitionName: string = '';

  isModalOpen: boolean = false;

  constructor(private route: ActivatedRoute, private tokenService: TokenService, private contestService: ContestService, private router: Router, private competitionService: CompetitionService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.competitionName = this.route.snapshot.paramMap.get('competitionName') || '';
    this.competitionId = history.state.competitionId;
    this.professorName = history.state.professorName;
    this.isProfessor = this.tokenService.getAuthorities().includes('ROLE_PROFESSOR') ||
      this.tokenService.getAuthorities().includes('ROLE_ADMIN');
    this.loadContests();
  }

  loadContests() {
    this.contestService.getContestsByCompetition(this.competitionId).subscribe({
      next: (cont) => {
        this.contests = cont;
      },
      error: (e) => {
        console.error('Error obteniendo los concursos', e);
      }
    });
  }

  startEditing() {
    this.tempCompetitionName = this.competitionName;
    this.isEditingName = true;
  }

  cancelEditing() {
    this.isEditingName = false;
  }

  saveName() {
    const newName = this.tempCompetitionName.trim();
    if (newName && newName !== this.competitionName)
      this.changeCompetitionName(newName);
    this.cancelEditing();
  }

  changeCompetitionName(newName: string) {
    this.competitionService.editCompetition(this.competitionId, newName).subscribe({
      next: () => {
        this.competitionName = newName;
      },
      error: (e) => {
        console.error('Error cambiando el nombre a la competición', e);
      }
    });
  }

  navigateToViewContest(contestId: number) {
    this.router.navigate(['/' + contestId + '/concurso'], { state: { competitionName: this.competitionName, professorName: this.professorName } });
  }

  deleteCompetition() {
    const confirmDelete = confirm('¿Está seguro de que desea eliminar esta competición?');
    if (confirmDelete) {
      this.competitionService.deleteCompetition(this.competitionId).subscribe({
        next: () => {
          alert('Competición eliminada con éxito');
          this.goBack();
        },
        error: (err) => console.error('Error al eliminar la competición:', err)
      });
    }
  }

  openCreateContest() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.contests = [];
    this.loadContests();
  }
  
  closeModalOnBackdropClick(event: MouseEvent) {
    if (event.target === event.currentTarget)
      this.closeModal();
  }

  goBack() {
    this.router.navigate(['/competiciones']);
  }
}
