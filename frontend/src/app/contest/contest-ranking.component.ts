import { Component, Input, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { TokenService } from '../service/token.service';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-contest-ranking',
  templateUrl: './contest-ranking.component.html',
  styleUrls: ['./contest-ranking.component.css']
})
export class ContestRankingComponent implements OnInit {

  private subscription: Subscription = new Subscription;

  @Input() professorName: string = '';

  isAdmin: boolean = false;

  competitionName: string = '';
  contestId: number = 0;
  contest!: Contest;

  sortOption: string = 'moreSuccess';

  studentsRanking: {
    name: string;
    rightGuess: number;
    actualTries: number[];
    totalTries: number;
    totalTime: number;
  }[] = [];

  constructor(private contestService: ContestService, private route: ActivatedRoute, private router: Router, private tokenService: TokenService) {
    this.competitionName = history.state.competitionName;
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.contestId = Number(this.route.snapshot.paramMap.get('contestId'));

    if (this.tokenService.getAuthorities().includes("ROLE_ADMIN")) {
      this.isAdmin = true;
      this.professorName = history.state.professorName;
    }

    this.contestService.getContestById(this.contestId).subscribe({
      next: (contest) => {
        this.contest = contest;
        this.obtainRanking();
        this.subscription = interval(1000).subscribe(() => {
          this.obtainRanking();
        });
      },
      error: (error) => {
        console.error('El concurso no existe', error);
      },
    });
  }

  obtainRanking() {
    this.contestService.getAllUserState(this.contest.id).subscribe({
      next: (userState) => {
        userState.forEach((user) => {
          const userName = user.userName;
          if (userName !== this.professorName) {
            var numRightGuess = 0;
            var totalRightGuess = 0;
            var time = 0;
            var tries: number[] = [];

            const realOrder = user.state.wordleOrder;
            let lastFinishedGame = null;

            for (let i = 0; i < realOrder.length; i++) {
              const game = user.state.games[realOrder[i]];
              totalRightGuess += game.tryCount;

              if (game.finished) {
                if (game.won)
                  numRightGuess++;
                time += Number(game.timeNeeded);
                lastFinishedGame = game;
              } else {
                tries = [game.tryCount, this.contest.numTries];
                break;
              }
            }

            if (tries.length === 0 && lastFinishedGame)
              tries = [lastFinishedGame.tryCount, this.contest.numTries];

            const existingStudent = this.studentsRanking.find(student => student.name === userName);

            if (existingStudent) {
              existingStudent.rightGuess = numRightGuess;
              existingStudent.actualTries = tries;
              existingStudent.totalTime = time;
              existingStudent.totalTries = totalRightGuess;
            } else {
              this.studentsRanking.push({
                name: userName,
                rightGuess: numRightGuess,
                actualTries: tries,
                totalTries: totalRightGuess,
                totalTime: time,
              });
            }
          }
        });
      },
      error: (error) => {
        console.error('Error obteniendo los estados', error);
      }
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
    this.router.navigate([this.competitionName + '/concursos'], { state: { professorName: this.professorName } });
  }
}