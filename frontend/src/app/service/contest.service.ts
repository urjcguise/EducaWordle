import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contest } from '../models/contest';
import { WordleState } from '../models/wordle-state';
import { UserState } from '../models/user-state';
import { WordleStateLog } from '../models/wordle-state-log';
import { ResumeContestDTO } from '../models/resume-contest';

@Injectable({
  providedIn: 'root'
})
export class ContestService {

  private apiUrl = 'http://localhost:9090/api/contests/';

  constructor(private httpClient: HttpClient) { }

  public createContest(contest: Contest, competitionId: number): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newContest/' + competitionId, contest);
  }

  public getContestsByCompetition(competitionId: number): Observable<Contest[]> {
    return this.httpClient.get<Contest[]>(this.apiUrl + competitionId + '/contests');
  }

  public deleteContest(contestId: number) {
    return this.httpClient.delete<Contest>(this.apiUrl + 'deleteContest/' + contestId);
  }

  public editContest(updatedContest: Contest) {
    return this.httpClient.post<any>(this.apiUrl + 'editContest', updatedContest);
  }

  public editRandomMode(newMode: boolean, contestId: number) {
    return this.httpClient.post<any>(this.apiUrl + 'editRandomMode/' + contestId, newMode);
  }

  public editAccentMode(newMode: boolean, contestId: number) {
    return this.httpClient.post<any>(this.apiUrl + 'editAccentMode/' + contestId, newMode);
  }

  public addWordlesToContest(contestId: number, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + 'addWordlesToContest/' + contestId, wordles);
  }

  public deleteWordlesInContest(contestId: number, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + 'deleteWordlesInContest/' + contestId, wordles);
  }

  public changeWordlesPosition(contestId: number, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + 'changeWordlesPosition/' + contestId, wordles);
  }

  public getContestById(contestId: number) {
    return this.httpClient.get<Contest>(this.apiUrl + contestId + '/contest');
  }

  public copyContest(oldContestId: number) {
    return this.httpClient.post<any>(this.apiUrl + 'copyContest/' + oldContestId, null);
  }

  public resumeContest(contestId: number, userName: string) {
    return this.httpClient.get<ResumeContestDTO>(this.apiUrl + 'resumeContest/' + contestId + '/' + userName);
  }

  public createContestState(contestId: number, userName: string, wordleState: WordleState): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newContestState/' + contestId + '/' + userName, wordleState);
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

  public deleteProfessorState(contestId: number, professorName: string) {
    return this.httpClient.post<any>(this.apiUrl + 'deleteAllProfessorState/' + contestId + '/' + professorName, null);
  }

  public existsInDictionary(word: string) {
    return this.httpClient.get<boolean>(this.apiUrl + 'existsInDictionary/' + word);
  }

  public existsInExternalDictionary(word: string, contestId: number) {
    return this.httpClient.get<boolean>(this.apiUrl + 'existsInExternalDictionary/' + contestId + '/' + word);
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
