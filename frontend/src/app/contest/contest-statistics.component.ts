import { Component, OnInit } from '@angular/core';
import { Game, State } from '../models/wordle-state';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute } from '@angular/router';
import { UserState } from '../models/user-state';
import { Wordle } from '../models/wordle';

@Component({
  selector: 'app-contest-statistics',
  templateUrl: './contest-statistics.component.html',
  styleUrls: ['./contest-statistics.component.css']
})
export class ContestStatisticsComponent implements OnInit {
  contestName!: string;

  wordlesInContest: string[] = [];

  totalStudents: number = 0;

  wordlesData: {
    wordle: string;
    success: number;
    trying: number;
    wrong: number;
    averageTryCount: number;
    averageTime: number;
    totalTimeAccumulated: number;
  }[] = [];

  wordleStudents: {
    wordle: string;
    students: {
      name: string;
      email: string;
      totalTries: number;
      startTime: string;
      endTime: string;
      totalTime: number;
      lastWordle: string;
      finished: boolean;
    }[];
  }[] = [];

  constructor(private contestService: ContestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.contestName = this.route.snapshot.paramMap.get('contestName')!;

    this.contestService.getContestByName(this.contestName).subscribe({
      next: (contest) => {
        contest.wordles.forEach((w: Wordle) => {
          this.wordlesInContest.push(w.word);
        });
      },
      error: (error) => {
        console.error('El concurso no existe', error);
      },
    });

    this.contestService.getWordlesInContest(this.contestName).subscribe({
      next: (wordles) => {
        wordles.forEach((wordle) => {
          this.wordlesData.push({
            wordle: wordle.word,
            success: 0,
            trying: 0,
            wrong: 0,
            averageTryCount: 0,
            averageTime: 0,
            totalTimeAccumulated: 0
          });
          this.wordleStudents.push({
            wordle: wordle.word,
            students: []
          });
        });
      },
      error: (error) => {
        console.error('Error obteniendo los wordles', error);
      }
    });

    this.contestService.getUserAndState(this.contestName).subscribe({
      next: (data) => {
        this.totalStudents = data.length;

        data.forEach((userState: UserState) => {
          const userName = userState.userName;
          const email = userState.email;
          let endTime = "";
          let totalTime = 0;

          userState.state.games.forEach((game: Game) => {
            const wordleDataItem = this.wordlesData.find((data) => data.wordle === game.wordle);
            const wordleStudentsItem = this.wordleStudents.find((data) => data.wordle === game.wordle);

            if (!wordleDataItem || !wordleStudentsItem) {
              console.error(`No se encontró el wordle para ${game.wordle}`);
              return;
            }

            if (game.finished) {
              if (game.won)
                wordleDataItem.success += 1;
              else
                wordleDataItem.wrong += 1;
              endTime = game.finishTime;
              totalTime = game.timeNeeded;
              wordleDataItem.totalTimeAccumulated += game.timeNeeded;
            } else {
              wordleDataItem.trying += 1;
            }

            wordleStudentsItem.students.push({
              name: userName,
              email: email,
              totalTries: game.tryCount,
              startTime: game.startTime,
              endTime: endTime,
              totalTime: totalTime,
              lastWordle: game.lastWordle,
              finished: game.finished,
            });

            this.wordlesData.forEach((wordleDataItem) => {
              if (wordleDataItem.success > 0) {
                wordleDataItem.averageTime = wordleDataItem.totalTimeAccumulated / wordleDataItem.success;
              } else {
                wordleDataItem.averageTime = 0;
              }
            });
          });
        });
      },
      error: (error) => {
        console.error('Error consiguiendo los usuarios y sus estados', error);
      },
    });
  }

  convertTime(seconds: number) {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
  }

  convertPercentage(total: number) {
    return (total / this.totalStudents) * 100;
  }
}


