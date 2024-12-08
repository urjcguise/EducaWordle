import { Component, OnInit } from '@angular/core';
import { Game, State } from '../models/wordle-state';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute } from '@angular/router';
import { UserState } from '../models/user-state';
import { ChartOptions } from 'chart.js';
import { Wordle } from '../models/wordle';

@Component({
  selector: 'app-contest-statistics',
  templateUrl: './contest-statistics.component.html',
  styleUrls: ['./contest-statistics.component.css']
})
export class ContestStatisticsComponent implements OnInit {
  contestName!: string;

  wordlesInContest: string[] = [];
  usersWithStates: { userName: string; state: any[] }[] = [];
  wordlesWithStates: {
    wordle: string;
    users: { userName: string; state: any }[];
    studentsFinished: number;
    studentsNotFinished: number;
    totalTryCount: number;
    averageTryCount: number;
    pieChartDatasets: { data: number[] }[];
  }[] = [];

  public pieChartOptions: ChartOptions<'pie'> = {
    responsive: true,
  };
  public pieChartLabels: string[] = ['Finalizados', 'No finalizados'];
  public pieChartLegend = true;
  public pieChartPlugins = [];

  constructor(private contestService: ContestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.contestName = this.route.snapshot.paramMap.get('contestName')!;

    this.contestService.getContestByName(this.contestName).subscribe({
      next: (contest) => {
        contest.wordles.forEach((w: Wordle) => {
          this.wordlesInContest.push(w.word);
        });

        this.wordlesWithStates = this.wordlesInContest.map((wordle) => ({
          wordle,
          users: [],
          studentsFinished: 0,
          studentsNotFinished: 0,
          totalTryCount: 0,
          averageTryCount: 0,
          pieChartDatasets: [
            {
              data: [0, 0],
            },
          ],
        }));
      },
      error: (error) => {
        console.error('El concurso no existe', error);
      },
    });

    this.contestService.getUserAndState(this.contestName).subscribe({
      next: (data) => {
        this.usersWithStates = data.map((elem: UserState) => ({
          userName: elem.userName,
          state: elem.state.games.map((game: Game) => game.state),
        }));

        data.forEach((userState: UserState) => {
          userState.state.games.forEach((game: Game) => {
            const wordleData = this.wordlesWithStates.find((w) => w.wordle === game.wordle);
            if (wordleData) {
              wordleData.users.push({
                userName: userState.userName,
                state: game.state,
              });

              if (game.finished) {
                wordleData.studentsFinished++;
                wordleData.totalTryCount += game.tryCount;
              } else {
                wordleData.studentsNotFinished++;
              }

              wordleData.averageTryCount =
                wordleData.studentsFinished > 0
                  ? wordleData.totalTryCount / wordleData.studentsFinished
                  : 0;

              wordleData.pieChartDatasets = [
                {
                  data: [wordleData.studentsFinished, wordleData.studentsNotFinished],
                },
              ];
            }
          });
        });
      },
      error: (error) => {
        console.error('Error consiguiendo los usuarios y sus estados', error);
      },
    });
  }
}


