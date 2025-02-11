import { Component, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { UserState } from '../models/user-state';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-contest-ranking',
  templateUrl: './contest-ranking.component.html',
  styleUrls: ['./contest-ranking.component.css']
})
export class ContestRankingComponent implements OnInit {

  isAdmin: boolean = false;
  professorName: string = '';

  competitionName: string = '';
  contestId: number = 0;
  contest!: Contest;

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
          if (this.isAdmin)
            this.router.navigate([this.competitionName + '/concursos'], { state: { professorName: this.professorName } });
          else
            this.router.navigate([this.competitionName + '/concursos']);
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
        this.contestService.getAllUserState(this.contest.id).subscribe({
          next: (userState) => {
            this.obtainRanking(userState);
          },
          error: (error) => {
            console.error('Error obteniendo los estados', error);
          }
        });
      },
      error: (error) => {
        console.error('El concurso no existe', error);
      },
    });
  }

  obtainRanking(userState: UserState[]) {
    this.studentsRanking = [];

    userState.forEach((user) => {
      const userName = user.userName;
      var numRightGuess = 0;
      var totalRightGuess = 0;
      var time = 0;
      var tries: number[] = [];
      user.state.games.forEach((game) => {
        if (game.finished) {
          if (game.won)
            numRightGuess++;
          time += Number(game.timeNeeded);
        }
        tries = [game.tryCount, this.contest.numTries];
        totalRightGuess += game.tryCount;
      });
      this.studentsRanking.push({
        name: userName,
        rightGuess: numRightGuess,
        actualTries: tries,
        totalTries: totalRightGuess,
        totalTime: time,
      });
    });
    this.studentsRanking.sort((a, b) => b.rightGuess - a.rightGuess);
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
}



