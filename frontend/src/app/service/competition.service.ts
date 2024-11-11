import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Competition } from '../models/competition';
import { Contest } from '../models/contest';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  
  private apiUrl = 'http://localhost:9090/api/competitions/';

  constructor(private httpClient: HttpClient) { }

  public createCompetition(competition: Competition): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newCompetition', competition);
  }

  public getCompetitions(): Observable<Competition[]> {
    return this.httpClient.get<Competition[]>(this.apiUrl + 'getCompetitions');
  }

  public getCompetitionById(id: number): Observable<Competition> {
    return this.httpClient.get<Competition>(this.apiUrl + 'getCompetitionById/' + id);
  }

  public deleteCompetition(id: number): Observable<Contest> {
    return this.httpClient.delete<Contest>(this.apiUrl + 'deleteCompetition/' + id);
  }
}
