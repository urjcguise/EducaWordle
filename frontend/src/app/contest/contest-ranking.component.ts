import { Component, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ActivatedRoute } from '@angular/router';
import { ContestService } from '../service/contest.service';
import { UserState } from '../models/user-state';

@Component({
  selector: 'app-contest-ranking',
  templateUrl: './contest-ranking.component.html',
  styleUrls: ['./contest-ranking.component.css']
})
export class ContestRankingComponent implements OnInit {

  contestName: string = '';
  contest!: Contest;

  studentsRanking: {
    name: string;
    rightGuess: number;
    actualTries: number[];
    totalTries: number;
    totalTime: number;
  }[] = [];

  constructor(private contestService: ContestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.contestName = this.route.snapshot.paramMap.get('contestName')!;
    this.contestService.getContestByName(this.contestName).subscribe({
      next: (contest) => {
        this.contest = contest;
        this.contestService.getUserAndState(this.contest.contestName).subscribe({
          next: (userState) => {
            console.log(userState);
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
          numRightGuess++;
          time += Number(game.timeNeeded);
        }
        tries = [game.tryCount, 6];
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
}



