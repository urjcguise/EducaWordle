import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Competition } from '../models/competition';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private apiUrl = 'http://localhost:9090/api/competitions/';

  constructor(private httpClient: HttpClient) { }

  createCompetition(competition: Competition): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newCompetition', competition);
  }

  getCompetitions(): Observable<Competition[]> {
    return this.httpClient.get<Competition[]>(this.apiUrl + 'getCompetitions');
  }
}
