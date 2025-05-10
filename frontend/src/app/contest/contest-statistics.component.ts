import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Game } from '../models/wordle-state';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { UserState } from '../models/user-state';
import { Wordle } from '../models/wordle';
import { TokenService } from '../service/token.service';
import { WordleStateLog } from '../models/wordle-state-log';
import { interval, Subscription } from 'rxjs';
import { WordleService } from '../service/wordle.service';

@Component({
  selector: 'app-contest-statistics',
  templateUrl: './contest-statistics.component.html',
  styleUrls: ['./contest-statistics.component.css']
})
export class ContestStatisticsComponent implements OnInit, OnDestroy {

  private subscription: Subscription = new Subscription;

  @Input() professorName: string = '';

  contestId: number = 0;
  competitionName!: string;
  isProfessor: boolean = false;
  isStudent: boolean = false;
  isAdmin: boolean = false;

  wordlesInContest: string[] = [];

  wordleList: Wordle[] = [];

  wordlesData: {
    wordle: string;
    success: number;
    trying: number;
    wrong: number;
    finished: number;
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
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.contestId = Number(this.route.snapshot.paramMap.get('contestId'));
    this.isProfessor = this.tokenService.getAuthorities().includes("ROLE_PROFESSOR");
    this.isStudent = this.tokenService.getAuthorities().includes("ROLE_STUDENT");
    this.isAdmin = this.tokenService.getAuthorities().includes("ROLE_ADMIN");

    this.professorName = history.state.professorName;

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
            finished: 0,
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
              this.studentInformation.forEach((elem) => {
                elem.info.sort((a, b) => a.nunTry - b.nunTry);
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

    this.getWordlesInfo();
    this.subscription = interval(1000).subscribe(() => {
      this.getWordlesInfo();
    });

    if (this.isProfessor || this.isAdmin) {
      this.getLogs();
      this.subscription = interval(1000).subscribe(() => {
        this.getLogs();
      });
    }
  }

  getWordlesInfo() {

    this.contestService.getAllUserState(this.contestId).subscribe({
      next: (data) => {

        const newWordlesData: typeof this.wordlesData = this.wordleList.map((wordle) => ({
          wordle: wordle.word,
          success: 0,
          trying: 0,
          wrong: 0,
          finished: 0,
          averageTryCount: 0,
          totalTriesAccumulated: 0,
          averageTime: 0,
          totalTimeAccumulated: 0
        }));

        data.forEach((userState: UserState) => {
          const userName = userState.userName;
          const email = userState.email;
          let wordleIndex = 0;

          if (userName !== this.professorName) {
            userState.state.games.forEach((game: Game) => {
              let endTime = "";
              let totalTime = 0;

              const wordleDataItem = newWordlesData.find((item) => item.wordle === this.wordleList[wordleIndex].word);
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
                wordleDataItem.finished++;
                wordleDataItem.totalTriesAccumulated += game.tryCount;
                wordleDataItem.totalTimeAccumulated += game.timeNeeded;
              } else if (game.tryCount > 0) {
                wordleDataItem.trying += 1;
              }

              const existsStudentIndex = wordleStudentsItem.students.findIndex(
                (student) => student.name === userName && student.email === email
              );

              const updatedStudentData = {
                name: userName,
                email: email,
                totalTries: game.tryCount,
                startTime: game.startTime,
                endTime: endTime,
                totalTime: totalTime,
                lastWordle: game.lastWordle,
                finished: game.finished
              };

              if (existsStudentIndex !== -1)
                wordleStudentsItem.students[existsStudentIndex] = updatedStudentData;
              else
                wordleStudentsItem.students.push(updatedStudentData);

              wordleIndex += 1;
            });
          }
        });

        this.wordlesData = this.wordlesData.map((oldItem, i) => {
          const newItem = newWordlesData[i];
          const changed = oldItem.totalTriesAccumulated !== newItem.totalTriesAccumulated ||
            oldItem.finished !== newItem.finished ||
            oldItem.success !== newItem.success ||
            oldItem.wrong !== newItem.wrong ||
            oldItem.trying !== newItem.trying ||
            oldItem.totalTimeAccumulated !== newItem.totalTimeAccumulated;

          return changed ? newItem : oldItem;
        });
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
  }

  getLogs() {
    this.contestService.getAllStateLog(this.contestId).subscribe({
      next: (logs) => {
        const updatedLogs = logs
          .filter((log: WordleStateLog) => log.userName !== this.professorName)
          .map((log: WordleStateLog) => ({
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
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
  }

  getSuccessPercentage(wordle: any): number {
    if (wordle.success === 0) return 0;
    return parseFloat(((wordle.success / (wordle.finished + wordle.trying)) * 100).toFixed(2));
  }

  getTryingPercentage(wordle: any): number {
    if (wordle.trying === 0) return 0;
    return parseFloat(((wordle.trying / (wordle.finished + wordle.trying)) * 100).toFixed(2));
  }

  getWrongPercentage(wordle: any): number {
    if (wordle.wrong === 0) return 0;
    return parseFloat(((wordle.wrong / (wordle.finished + wordle.trying)) * 100).toFixed(2));
  }

  getAverageTime(wordle: any): string {
    if (wordle.finished === 0) return '0:00';
    const average = wordle.totalTimeAccumulated / wordle.finished;
    const minutes = Math.floor(average / 60);
    const remainingSeconds = Math.floor(average % 60);
    return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
  }

  getAverageTries(wordle: any): number {
    if (wordle.finished === 0) return 0;
    return parseFloat((wordle.totalTriesAccumulated / wordle.finished).toFixed(2));
  }

  exportToExcel() {
    if (this.studentsLog.length === 0)
      return;
    this.contestService.exportLogsInExcel(this.contestId).subscribe({
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

  goBack() {
    this.router.navigate(['/competiciones'], { state: { professorName: this.professorName } });
  }
}