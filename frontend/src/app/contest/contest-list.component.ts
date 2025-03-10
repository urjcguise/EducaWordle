import { Component, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { WordleService } from '../service/wordle.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-contest-list',
  templateUrl: './contest-list.component.html',
  styleUrls: ['./contest-list.component.css']
})
export class ContestListComponent implements OnInit {

  contests: Contest[] = [];
  contestsWithState: { contest: Contest; state: 'upcoming' | 'ongoing' | 'finished'; empty: boolean }[] = [];
  competitionName!: string;
  competitionId!: number;

  noContests = true;
  isProfessor = false;
  isStudent = false;
  isAdmin = false;

  professorName: string = '';

  constructor(private contestService: ContestService, private route: ActivatedRoute, private tokenService: TokenService, private router: Router, private wordleService: WordleService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate')
          this.router.navigate(['/competiciones'], { state: { professorName: this.professorName } });
      }
    });
  }

  ngOnInit() {
    this.competitionId = history.state.competitionId;
    this.competitionName = this.route.snapshot.paramMap.get('competitionName')!;
    if (!this.competitionName) {
      console.error('No se encontró la competición');
    }
    this.loadContests();
    this.professorName = history.state.professorName;
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.isProfessor = true;
    if (this.tokenService.getAuthorities().includes("ROLE_STUDENT"))
      this.isStudent = true;
    if (this.tokenService.getAuthorities().includes("ROLE_ADMIN"))
      this.isAdmin = true;
  }

  async loadContests() {
    this.contestService.getContestsByCompetition(this.competitionName).subscribe({
      next: async (data) => {
        if (data.length == 0) {
          this.noContests = true;
        } else {
          this.contests = data.map(contest => ({
            ...contest,
            startDate: new Date(contest.startDate),
            endDate: new Date(contest.endDate)
          }));

          const contestsWithStatePromises = this.contests.map(async (contest) => ({
            contest,
            state: await this.getContestState(contest),
            empty: this.checkEmpty(contest)
          }));

          this.contestsWithState = await Promise.all(contestsWithStatePromises);

          this.noContests = false;
        }
      },
      error: (error) => {
        console.error('Error consiguiendo los concursos', error);
      }
    });
  }

  deleteContest(contestId: number) {
    const confirmDelete = confirm('¿Está seguro de que desea eliminar este concurso?');
    if (confirmDelete) {
      this.contestService.deleteContest(contestId).subscribe({
        next: () => {
          alert('Concurso eliminado con éxito');
          this.loadContests();
        },
        error: (err) => console.error('Error al eliminar el concurso:', err)
      });
    }
  }

  navigateToEditContest(contestId: number) {
    this.router.navigate([`/${contestId}/editarConcurso`], { state: { competitionName: this.competitionName } });
  }

  navigateToCreateContest() {
    this.router.navigate(['/nuevoConcurso'], { state: { competitionId: this.competitionId, competitionName: this.competitionName } });
  }

  navigateToPlayWordle(contestId: number, wordleIndex: number) {
    this.router.navigate(['/wordle'], { state: { contestId, wordleIndex, competitionName: this.competitionName } });
  }

  navigateToWatchStatistics(contestId: number) {
    this.router.navigate([`/${contestId}/verEstadisticas`], { state: { competitionName: this.competitionName, professorName: this.professorName } });
  }

  navigateToRanking(contestId: number) {
    this.router.navigate([`/${contestId}/verRanking`], { state: { competitionName: this.competitionName, professorName: this.professorName } });
  }

  getContestState(contest: Contest): Promise<'upcoming' | 'ongoing' | 'finished'> {
    const now = new Date();

    if (contest.startDate == null && contest.endDate == null) {
      return Promise.resolve('upcoming');
    } else if (now < contest.startDate) {
      return Promise.resolve('upcoming');
    } else if (now >= contest.startDate && now <= contest.endDate) {
      if (this.isStudent) {
        return firstValueFrom(this.contestService.getContestState(contest.id, this.tokenService.getUserName()!))
          .then((state) => {
            for (const game of state.games) {
              if (!game.finished) {
                return 'ongoing';
              }
            }
            return 'finished';
          })
          .catch((e) => {
            console.error("Error obteniendo el estado", e);
            return 'ongoing';
          });
      }
      return Promise.resolve('ongoing');
    } else {
      return Promise.resolve('finished');
    }
  }

  copyContest(oldContest: Contest) {
    this.contestService.copyContest(oldContest.id).subscribe({
      next: () => {
        alert('Concurso y wordles copiados correctamente');
      },
      error: (e) => {
        console.error('Error copiando el concurso', e);
      }
    });
  }

  private checkEmpty(contest: Contest): boolean {
    return contest.wordlesLength.length == 0;
  }
}
