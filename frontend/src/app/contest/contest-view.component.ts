import { Component, Input, OnInit } from '@angular/core';
import { ContestService } from '../service/contest.service';
import { Contest } from '../models/contest';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-contest-view',
  templateUrl: './contest-view.component.html',
  styleUrls: ['./contest-view.component.css']
})
export class ContestViewComponent implements OnInit {

  isProfessor: boolean = false;
  isStudent: boolean = false;

  professorName: string = '';

  studentFinished: boolean = false;
  studentPlaying: boolean = false;

  contest!: Contest;
  contestId: number = 0;
  contestFinished: boolean = false;
  contestStarted: boolean = false;
  hasWordles: boolean = false;

  competitionName: string = '';
  competitionId: number = 0;
  activeTab: string = '';

  constructor(private contestService: ContestService, private route: ActivatedRoute, private router: Router, private tokenService: TokenService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.activeTab = history.state.activeTab || 'info';
    this.competitionName = history.state.competitionName;
    this.professorName = history.state.professorName;
    this.competitionId = history.state.competitionId;
    this.contestId = Number(this.route.snapshot.paramMap.get('contestId'));
    this.isProfessor = this.tokenService.getAuthorities().includes('ROLE_PROFESSOR') ||
      this.tokenService.getAuthorities().includes('ROLE_ADMIN');
    this.isStudent = this.tokenService.getAuthorities().includes('ROLE_STUDENT');

    this.contestService.getContestById(this.contestId).subscribe({
      next: (cont) => {
        this.contest = cont;

        this.hasWordles = cont.wordlesLength.length > 0;

        const startDate = new Date(cont.startDate);
        const endDate = new Date(cont.endDate);
        const thisMoment = new Date();
        if (endDate < thisMoment)
          this.contestFinished = true;
        if (startDate <= thisMoment)
          this.contestStarted = true;

        this.checkIsFinished();
      },
      error: (e) => {
        console.error('Error obteniendo el concurso', e);
      }
    });
  }

  editContest() {
    this.router.navigate([Number(this.route.snapshot.paramMap.get('contestId')) + '/editarConcurso'], { state: { professorName: history.state.professorName, competitionName: this.competitionName, competitionId: this.competitionId } });
  }

  deleteContest() {
    const confirmDelete = confirm('¿Está seguro de que desea eliminar este concurso?');
    if (confirmDelete) {
      this.contestService.deleteContest(this.contestId).subscribe({
        next: () => {
          alert('Concurso eliminado con éxito');
          this.router.navigate(['/competiciones']);
        },
        error: (err) => console.error('Error al eliminar el concurso:', err)
      });
    }
  }

  copyContest() {
    this.contestService.copyContest(this.contestId).subscribe({
      next: () => {
        alert('Concurso y wordles copiados correctamente');
      },
      error: (e) => {
        console.error('Error copiando el concurso', e);
      }
    });
  }

  checkIsFinished() {
    return firstValueFrom(this.contestService.getContestState(this.contest.id, this.tokenService.getUserName()!))
      .then((state) => {
        for (const game of state.games) {
          if (!game.finished) {
            this.studentFinished = false;
            this.studentPlaying = true;
            return;
          }
        }
        this.studentFinished = true;
        return;
      })
      .catch((e) => {
        console.error("Error obteniendo el estado", e);
        return 'ongoing';
      });
  }

  playWordle() {
    this.router.navigate(['/wordle'], {
      state: {
        contestId: this.contest.id,
        competitionName: this.competitionName,
        isProfessor: this.isProfessor,
        professorName: this.professorName,
        competitionId: this.competitionId
      }
    });
  }

  hasWordlesChange(value: boolean) {
    this.hasWordles = value;
  }

  navigateToCompetition() {
    this.router.navigate(['/competicion/' + this.competitionName], { state: { competitionId: this.competitionId, professorName: this.professorName } });
  }

  goBack() {
    this.router.navigate(['/competiciones']);
  }

}