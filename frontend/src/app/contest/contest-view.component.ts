import { Component, OnInit } from '@angular/core';
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

  studentFinished: boolean = false;

  contest!: Contest;
  contestId: number = 0;

  competitionName: string = '';
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
    this.contestId = Number(this.route.snapshot.paramMap.get('contestId'));
    this.isProfessor = this.tokenService.getAuthorities().includes('ROLE_PROFESSOR') ||
      this.tokenService.getAuthorities().includes('ROLE_ADMIN');
    this.isStudent = this.tokenService.getAuthorities().includes('ROLE_STUDENT');

    this.contestService.getContestById(this.contestId).subscribe({
      next: (cont) => {
        this.contest = cont;

        if (this.isStudent)
          this.checkIsFinished();
      },
      error: (e) => {
        console.error('Error obteniendo el concurso', e);
      }
    });
  }

  editContest() {
    this.router.navigate([Number(this.route.snapshot.paramMap.get('contestId')) + '/editarConcurso'], { state: { professorName: history.state.professorName } });
  }

  goBack() {
    this.router.navigate(['/competiciones']);
  }

  checkIsFinished() {
    console.log(this.contest.id);
    console.log(this.tokenService.getUserName()!);
    return firstValueFrom(this.contestService.getContestState(this.contest.id, this.tokenService.getUserName()!))
      .then((state) => {
        for (const game of state.games) {
          if (!game.finished) {
            this.studentFinished = false;
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
        wordleIndex: 0,
        competitionName: this.competitionName
      }
    });
  }

}