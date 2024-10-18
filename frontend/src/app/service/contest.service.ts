import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contest } from '../models/contest';

@Injectable({
  providedIn: 'root'
})
export class ContestService {

  private apiUrl = 'http://localhost:9090/api/contest/';

  constructor(private httpClient: HttpClient) {}

  public createContest(contest: Contest): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newContest', contest);
  }

  public getContestsByCompetition(competitionId: number): Observable<any[]> {
    return this.httpClient.get<any[]>(this.apiUrl + competitionId + '/contests');
  }
}
