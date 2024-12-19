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

  wordlesWithRankings: {
    wordle: string;
    rankings: { userName: string; score: number }[];
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
            console.log(this.wordlesWithRankings);
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
    const wordleRankingMap = new Map();

    userState.forEach((user) => {
      const userName = user.userName;

      user.state.games.forEach((game) => {
        if (game.finished) {
          const { wordle, tryCount, timeNeeded } = game;
          const score = Number(timeNeeded) + tryCount;

          if (!wordleRankingMap.has(wordle)) {
            wordleRankingMap.set(wordle, []);
          }

          wordleRankingMap.get(wordle).push({ userName, score });
        }
      });
    });

    this.wordlesWithRankings = Array.from(wordleRankingMap.entries()).map(([wordle, rankings]) => {
      rankings.sort((a: { score: number; }, b: { score: number; }) => a.score - b.score);
      return { wordle, rankings };
    });
  }
}



