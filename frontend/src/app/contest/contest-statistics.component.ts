import { Component, OnDestroy, OnInit } from '@angular/core';
import { Game } from '../models/wordle-state';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { UserState } from '../models/user-state';
import { Wordle } from '../models/wordle';
import { TokenService } from '../service/token.service';
import { WordleStateLog } from '../models/wordle-state-log';
import { interval, Subscription, switchMap, tap } from 'rxjs';
import { WordleService } from '../service/wordle.service';

@Component({
  selector: 'app-contest-statistics',
  templateUrl: './contest-statistics.component.html',
  styleUrls: ['./contest-statistics.component.css']
})
export class ContestStatisticsComponent implements OnInit, OnDestroy {

  private subscription: Subscription = new Subscription;

  professorName: string = '';

  contestId: number = 0;
  competitionName!: string;
  isProfessor: boolean = false;
  isStudent: boolean = false;
  isAdmin: boolean = false;

  wordlesInContest: string[] = [];

  totalStudents: number = 0;

  wordleList: Wordle[] = [];

  wordlesData: {
    wordle: string;
    success: number;
    trying: number;
    wrong: number;
    averageTryCount: number;
    totalTriesAccumulated: number;
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

  studentInformation: {
    wordle: string;
    info: {
      nunTry: number;
      time: string;
      lastWord: string;
      correct: number;
      wrongPlace: number;
      wrong: number;
    }[];
  }[] = [];

  studentsLog: {
    student: string;
    email: string;
    time: string;
    wordleToGuess: string;
    wordlePosition: number;
    wordleInserted: string;
    try: number;
    state: boolean;
  }[] = [];

  constructor(private contestService: ContestService, private wordleService: WordleService, private route: ActivatedRoute, private tokenService: TokenService, private router: Router) {
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
    this.isProfessor = this.tokenService.getAuthorities().includes("ROLE_PROFESSOR");
    this.isStudent = this.tokenService.getAuthorities().includes("ROLE_STUDENT");
    this.isAdmin = this.tokenService.getAuthorities().includes("ROLE_ADMIN");

    if (this.isAdmin)
      this.professorName = history.state.professorName;

    /*
    this.contestService.getContestById(this.contestId).subscribe({
      next: (contest) => {
        contest.wordles.forEach((w: Wordle) => {
          this.wordlesInContest.push(w.word);
        });
      },
      error: (error) => {
        console.error('El concurso no existe', error);
      }
    });*/
    this.wordleService.getWordlesByContest(this.contestId).subscribe({
      next: (wordles) => {
        this.wordleList = wordles;
        wordles.forEach((wordle) => {
          this.wordlesInContest.push(wordle.word);
          this.wordlesData.push({
            wordle: wordle.word,
            success: 0,
            trying: 0,
            wrong: 0,
            averageTryCount: 0,
            totalTriesAccumulated: 0,
            averageTime: 0,
            totalTimeAccumulated: 0
          });
          this.wordleStudents.push({
            wordle: wordle.word,
            students: []
          });
          this.studentInformation.push({
            wordle: wordle.word,
            info: []
          });
        })
      },
      error: (e) => {
        if (e.status === 409)
          console.error('No existe el concurso', e);
        if (e.status === 400)
          console.error('No puedes realizar esa llamada', e);
        if (e.status === 404)
          console.error('No se ha encontrado al usuario', e);
      }
    });

    this.contestService.getAllUserState(this.contestId).subscribe({
      next: (data) => {
        this.totalStudents = data.length;
        data.forEach((userState: UserState) => {
          const userName = userState.userName;
          const email = userState.email;
          let endTime = "";
          let totalTime = 0;
          let wordleIndex = 0;
          userState.state.games.forEach((game: Game) => {
            /*
            const wordleDataItem = this.wordlesData.find((item) => item.wordle === game.wordle);
            const wordleStudentsItem = this.wordleStudents.find((item) => item.wordle === game.wordle);
            */
            const wordleDataItem = this.wordlesData.find((item) => item.wordle === this.wordleList[wordleIndex].word);
            const wordleStudentsItem = this.wordleStudents.find((item) => item.wordle === this.wordleList[wordleIndex].word);

            if (!wordleDataItem || !wordleStudentsItem) {
              console.error(`No se encontró el wordle para ${this.wordleList[wordleIndex].word}`);
              return;
            }

            if (game.finished) {
              if (game.won)
                wordleDataItem.success += 1;
              else
                wordleDataItem.wrong += 1;
              endTime = game.finishTime;
              totalTime = game.timeNeeded;
              wordleDataItem.totalTriesAccumulated += game.tryCount;
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
              finished: game.finished
            });
            wordleIndex += 1;
          });
        });


        if (this.isStudent) {
          this.contestService.getAllUserStateLog(this.contestId, this.tokenService.getUserName()!).subscribe({
            next: (logs) => {
              logs.forEach((log: WordleStateLog) => {
                const studentInfoItem = this.studentInformation.find(item =>
                  item.wordle.trim().toLowerCase() === log.wordleToGuess.trim().toLowerCase()
                );
                if (!studentInfoItem) {
                  console.error(`No se encontró el wordle para ${log.wordleToGuess}`);
                  return;
                }
                studentInfoItem.info.push({
                  nunTry: log.numTry,
                  time: log.dateLog,
                  lastWord: log.wordleInserted,
                  correct: log.correct,
                  wrongPlace: log.wrongPosition,
                  wrong: log.wrong
                });
              });
            },
            error: (e) => {
              console.error('Error obteniendo los logs', e);
            }
          });
        }
      },
      error: (e) => {
        console.error('Error consiguiendo los usuarios y sus estados', e);
      }
    });


    /*
    this.contestService.getWordlesInContest(this.contestId).pipe(
      tap((wordles) => {
        wordles.forEach((wordle) => {
          this.wordlesData.push({
            wordle: wordle.word,
            success: 0,
            trying: 0,
            wrong: 0,
            averageTryCount: 0,
            totalTriesAccumulated: 0,
            averageTime: 0,
            totalTimeAccumulated: 0
          });
          this.wordleStudents.push({
            wordle: wordle.word,
            students: []
          });
          this.studentInformation.push({
            wordle: wordle.word,
            info: []
          });
        });
      }),
      switchMap(() => this.contestService.getAllUserState(this.contestId))
    ).subscribe({
      next: (data) => {
        this.totalStudents = data.length;
        data.forEach((userState: UserState) => {
          const userName = userState.userName;
          const email = userState.email;
          let endTime = "";
          let totalTime = 0;
          userState.state.games.forEach((game: Game) => {
            const wordleDataItem = this.wordlesData.find((item) => item.wordle === game.wordle);
            const wordleStudentsItem = this.wordleStudents.find((item) => item.wordle === game.wordle);

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
              wordleDataItem.totalTriesAccumulated += game.tryCount;
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
              finished: game.finished
            });
          });
        });
        

        if (this.isStudent) {
          this.contestService.getAllUserStateLog(this.contestId, this.tokenService.getUserName()!).subscribe({
            next: (logs) => {
              logs.forEach((log: WordleStateLog) => {
                const studentInfoItem = this.studentInformation.find(item =>
                  item.wordle.trim().toLowerCase() === log.wordleToGuess.trim().toLowerCase()
                );
                if (!studentInfoItem) {
                  console.error(`No se encontró el wordle para ${log.wordleToGuess}`);
                  return;
                }
                studentInfoItem.info.push({
                  nunTry: log.numTry,
                  time: log.dateLog,
                  lastWord: log.wordleInserted,
                  correct: log.correct,
                  wrongPlace: log.wrongPosition,
                  wrong: log.wrong
                });
              });
            },
            error: (e) => {
              console.error('Error obteniendo los logs', e);
            }
          });
        }
      },
      error: (error) => {
        console.error('Error consiguiendo los usuarios y sus estados', error);
      }
    });
*/
    if (this.isProfessor || this.isAdmin) {
      this.contestService.getAllStateLog(this.contestId).subscribe({
        next: (logs) => {
          logs.forEach((log: WordleStateLog) => {
            this.studentsLog.push({
              student: log.userName,
              email: log.email,
              time: log.dateLog,
              wordleToGuess: log.wordleToGuess,
              wordlePosition: log.wordlePosition,
              wordleInserted: log.wordleInserted,
              try: log.numTry,
              state: log.state
            });
          });
          this.studentsLog.sort((a, b) => new Date(a.time).getTime() - new Date(b.time).getTime());
          this.subscription = interval(5000).subscribe(() => {
            this.getLogs();
          });
        },
        error: (e) => {
          console.error('Error obteniendo los logs', e);
        }
      });
    }
  }

  getLogs() {
    this.contestService.getAllStateLog(this.contestId).subscribe({
      next: (logs) => {
        const updatedLogs = logs.map((log: WordleStateLog) => ({
          student: log.userName,
          email: log.email,
          time: log.dateLog,
          wordleToGuess: log.wordleToGuess,
          wordlePosition: log.wordlePosition,
          wordleInserted: log.wordleInserted,
          try: log.numTry,
          state: log.state
        }));

        this.studentsLog.length = 0;
        this.studentsLog.push(...updatedLogs);

        this.studentsLog.sort((a, b) => new Date(a.time).getTime() - new Date(b.time).getTime());
      },
      error: (e) => {
        console.error('Error obteniendo los logs', e);
      }
    });
  }

  ngOnDestroy(): void {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  convertTime(seconds: number): string {
    if (this.totalStudents === 0) return '0:00';
    const average = seconds / this.totalStudents;
    const minutes = Math.floor(average / 60);
    const remainingSeconds = Math.floor(average % 60);
    return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
  }

  convertPercentage(total: number): number {
    if (this.totalStudents === 0) return 0;
    return parseFloat(((total / this.totalStudents) * 100).toFixed(2));
  }

  convertTries(totalTries: number): number {
    if (this.totalStudents === 0) return 0;
    return parseFloat((totalTries / this.totalStudents).toFixed(2));
  }

  exportToExcel() {
    this.contestService.getLogsInExcel(this.contestId).subscribe({
      next: (docu) => {
        const blob = new Blob([docu], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'logs.xlsx';
        a.click();
        window.URL.revokeObjectURL(url);
      },
      error: (e) => {
        console.error('Error obteniendo el Excel para su descarga', e);
      }
    })
  }
}