import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { Contest } from '../models/contest';
import { CompetitionService } from '../service/competition.service';
import { User } from '../models/user';
import { catchError, forkJoin, of } from 'rxjs';

@Component({
  selector: 'app-competition-ranking',
  templateUrl: './competition-ranking.component.html',
  styleUrls: ['./competition-ranking.component.css']
})
export class CompetitionRankingComponent implements OnInit {

  @Input() competitionId: number = 0;

  competitionName: string = '';

  contests: Contest[] = [];
  students: User[] = [];

  studentsRanking: {
    name: string;
    rightGuess: number;
    totalTries: number;
    totalTime: number;
  }[] = [];

  constructor(private contestService: ContestService, private competitionService: CompetitionService, private route: ActivatedRoute, private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit() {
    this.competitionName = this.route.snapshot.paramMap.get('competitionName') || '';

    this.competitionService.getStudentsByCompetition(this.competitionId).subscribe({
      next: (users) => {
        this.students = users;

        this.contestService.getContestsByCompetition(this.competitionId).subscribe({
          next: (list) => {
            this.contests = list;

            this.students.forEach((s) => {
              const userName = s.username;
              let numRightGuess = 0;
              let numTotalTries = 0;
              let time = 0;

              const contestStates$ = this.contests.map((c) =>
                this.contestService.getContestState(c.id, s.username).pipe(
                  catchError((err) => {
                    return of(null);
                  })
                )
              );

              forkJoin(contestStates$).subscribe({
                next: (states) => {
                  states.forEach((state) => {
                    if (!state) return;
                    state.games.forEach((game) => {
                      if (!game.finished) return;
                      if (game.won) numRightGuess++;
                      time += Number(game.timeNeeded);
                      numTotalTries += game.tryCount;
                    });
                  });

                  this.studentsRanking.push({
                    name: userName,
                    rightGuess: numRightGuess,
                    totalTries: numTotalTries,
                    totalTime: time
                  });

                  this.sortStudentsRanking('moreSuccess');
                },
                error: () => {
                  console.error('Error general en la carga de concursos para', s.username);
                }
              });
            });
          },
          error: (e) => {
            console.error('Error obteniendo los concursos', e);
          }
        });
      },
      error: (e) => {
        console.error('Error obteniendo los alumnos participando', e);
      }
    });
  }

  formatTime(seconds: number): string {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
  }

  onSortChange(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value;
    this.sortStudentsRanking(selectedValue);
  }

  sortStudentsRanking(sortOption: string) {
    switch (sortOption) {
      case 'moreSuccess':
        this.studentsRanking.sort((a, b) => b.rightGuess - a.rightGuess);
        break;

      case 'lessSuccess':
        this.studentsRanking.sort((a, b) => a.rightGuess - b.rightGuess);
        break;

      case 'quicker':
        this.studentsRanking.sort((a, b) => a.totalTime - b.totalTime);
        break;

      case 'slowest':
        this.studentsRanking.sort((a, b) => b.totalTime - a.totalTime);
        break;

      default:
        console.warn('Opción de ordenación no válida');
    }
  }

  goBack() {
    this.router.navigate(['/competiciones'], { state: { professorName: history.state.professorName } });
  }
}
