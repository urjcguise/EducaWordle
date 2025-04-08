import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, EMPTY, Observable, throwError } from 'rxjs';
import { Contest } from '../models/contest';
import { WordleState } from '../models/wordle-state';
import { UserState } from '../models/user-state';
import { WordleStateLog } from '../models/wordle-state-log';
import { Wordle } from '../models/wordle';

@Injectable({
  providedIn: 'root'
})
export class ContestService {

  private apiUrl = 'http://localhost:9090/api/contests/';

  constructor(private httpClient: HttpClient) { }

  public createContest(contest: Contest, competitionId: number): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newContest/' + competitionId, contest);
  }

  public getContestsByCompetition(competitionName: string): Observable<Contest[]> {
    return this.httpClient.get<Contest[]>(this.apiUrl + competitionName + '/contests');
  }

  public deleteContest(contestId: number) {
    return this.httpClient.delete<Contest>(this.apiUrl + 'deleteContest/' + contestId);
  }

  public editContest(updatedContest: Contest, updatedWordles: Wordle[]) {
    const requestBody = {
      contest: updatedContest,
      wordles: updatedWordles
    };
    return this.httpClient.post<any>(this.apiUrl + 'editContest', requestBody);
  }


  public addWordlesToContest(contestId: number, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + 'addWordlesToContest/' + contestId, wordles);
  }

  public deleteWordlesInContest(contestId: number, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + 'deleteWordlesInContest/' + contestId, wordles);
  }


  public getContestById(contestId: number) {
    return this.httpClient.get<Contest>(this.apiUrl + contestId + '/contest');
  }

  public copyContest(oldContestId: number) {
    return this.httpClient.post<any>(this.apiUrl + 'copyContest/' + oldContestId, null);
  }

  public createContestState(contestId: number, userName: string, wordleState: WordleState): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newContestState/' + contestId + '/' + userName, wordleState).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 409) {
          console.log('El estado del concurso ya existe. No se creará nuevamente.');
          return EMPTY;
        }
        return throwError(() => error);
      })
    );
  }

  public getContestState(contestId: number, userName: string) {
    return this.httpClient.get<WordleState>(this.apiUrl + 'getContestState/' + contestId + '/' + userName);
  }

  public updateContestState(contestId: number, userName: string, wordleState: WordleState) {
    return this.httpClient.post<any>(this.apiUrl + 'updateContestState/' + contestId + '/' + userName, wordleState);
  }

  public createContestLog(contestId: number, wordlePosition: number, userName: string, contestLog: WordleStateLog) {
    return this.httpClient.post<any>(this.apiUrl + 'createContestLog/' + contestId + '/' + wordlePosition + '/' + userName, contestLog);
  }

  public getAllStateLog(contestId: number) {
    return this.httpClient.get<WordleStateLog[]>(this.apiUrl + 'getAllContestStateLogs/' + contestId);
  }

  public getAllUserState(contestId: number) {
    return this.httpClient.get<UserState[]>(this.apiUrl + 'getAllContestState/' + contestId);
  }

  public getAllUserStateLog(contestId: number, userName: string) {
    return this.httpClient.get<WordleStateLog[]>(this.apiUrl + 'getAllUserContestStateLogs/' + contestId + '/' + userName);
  }

  public existsInDictionary(word: string) {
    return this.httpClient.get<Boolean>(this.apiUrl + 'existsInDictionary/' + word);
  }

  public existsInExternalDictionary(word: string, contestId: number) {
    return this.httpClient.get<Boolean>(this.apiUrl + 'existsInExternalDictionary/' + contestId + '/' + word);
  }

  public saveExternalDictionary(words: string[], contestId: number) {
    return this.httpClient.post<any>(this.apiUrl + 'saveExternalDictionary/' + contestId, words);
  }

  public exportLogsInExcel(contestId: number) {
    return this.httpClient.get(this.apiUrl + 'exportLogsInExcel/' + contestId, {
      responseType: 'blob'
    });
  }
}
