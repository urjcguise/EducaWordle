import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { Contest } from '../models/contest';
import { CompetitionService } from '../service/competition.service';
import { User } from '../models/user';
import { catchError, forkJoin, interval, of, Subscription } from 'rxjs';

@Component({
  selector: 'app-competition-ranking',
  templateUrl: './competition-ranking.component.html',
  styleUrls: ['./competition-ranking.component.css']
})
export class CompetitionRankingComponent implements OnInit {

  private subscription: Subscription = new Subscription;

  @Input() competitionId: number = 0;

  competitionName: string = '';

  contests: Contest[] = [];
  students: User[] = [];

  sortOption: string = 'moreSuccess';

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

            this.obtainRanking();
            this.subscription = interval(1000).subscribe(() => {
              this.obtainRanking();
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

  obtainRanking() {
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

          const existingStudent = this.studentsRanking.find(student => student.name === userName);

          if (existingStudent) {
            existingStudent.rightGuess = numRightGuess;
            existingStudent.totalTime = time;
            existingStudent.totalTries = numTotalTries;
          } else {
            this.studentsRanking.push({
              name: userName,
              rightGuess: numRightGuess,
              totalTries: numTotalTries,
              totalTime: time
            });
          }
        },
        error: () => {
          console.error('Error general en la carga de concursos para', s.username);
        }
      });
    });
    this.sortStudentsRanking(this.sortOption);
  }

  formatTime(seconds: number): string {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
  }

  onSortChange(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value;
    this.sortOption = selectedValue;
    this.sortStudentsRanking(selectedValue);
  }

  sortStudentsRanking(sortOption: string) {
    switch (sortOption) {
      case 'moreSuccess':
        this.studentsRanking.sort((a, b) => {
          if (b.rightGuess !== a.rightGuess)
            return b.rightGuess - a.rightGuess;
          else
            return a.totalTime - b.totalTime;
        });
        break;

      case 'lessSuccess':
        this.studentsRanking.sort((a, b) => {
          if (a.rightGuess !== b.rightGuess)
            return a.rightGuess - b.rightGuess;
          else
            return a.totalTime - b.totalTime;
        });
        break;

      case 'slowest':
        this.studentsRanking.sort((a, b) => {
          if (b.rightGuess !== a.rightGuess)
            return b.rightGuess - a.rightGuess;
          else
            return b.totalTime - a.totalTime;
        });
        break;

      default:
        console.warn('Opción de ordenación no válida');
    }
  }

  ngOnDestroy(): void {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  goBack() {
    this.router.navigate(['/competiciones'], { state: { professorName: history.state.professorName } });
  }
}
