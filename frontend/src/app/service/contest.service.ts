import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, EMPTY, Observable, throwError } from 'rxjs';
import { Contest } from '../models/contest';
import { WordleState } from '../models/wordle-state';
import { UserState } from '../models/user-state';

@Injectable({
  providedIn: 'root'
})
export class ContestService {

  private apiUrl = 'http://localhost:9090/api/contests/';

  constructor(private httpClient: HttpClient) { }

  public createContest(contest: Contest, competitionId: number): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newContest/' + competitionId, contest);
  }

  public getContestsByCompetition(competitionName: string): Observable<any[]> {
    return this.httpClient.get<any[]>(this.apiUrl + competitionName + '/contests');
  }

  public deleteContest(contestName: string) {
    return this.httpClient.delete<Contest>(this.apiUrl + 'deleteContest/' + contestName);
  }

  public editContest(contestName: string, updatedContest: Contest) {
    return this.httpClient.post<any>(this.apiUrl + 'editContest/' + contestName, updatedContest);
  }

  public getContestByName(contestName: string) {
    return this.httpClient.get<Contest>(this.apiUrl + contestName + '/contest');
  }

  public copyContest(newContest: Contest, oldContestName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'copyContest/' + oldContestName, newContest);
  }

  public createContestState(contestName: string, userName: string, wordleState: WordleState): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newContestState/' + contestName + '/' + userName, wordleState).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 409) {
          console.log('El estado del concurso ya existe. No se creará nuevamente.');
          return EMPTY; 
        }
        return throwError(() => error);
      })
    );
  }

  public getContestState(contestName: string, userName: string) {
    return this.httpClient.get<WordleState>(this.apiUrl + 'getContestState/' + contestName + '/' + userName);
  }

  public updateContestState(contestName: string, userName: string, wordleState: WordleState) {
    return this.httpClient.post<any>(this.apiUrl + 'updateContestState/' + contestName + '/' + userName, wordleState);
  }

  public getUserAndState(contestName: string) {
    return this.httpClient.get<UserState[]>(this.apiUrl + 'getUserAndContestState/' + contestName);
  }
}
