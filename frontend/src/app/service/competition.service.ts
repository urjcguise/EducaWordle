import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Competition } from '../models/competition';
import { Contest } from '../models/contest';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private apiUrl = 'http://localhost:9090/api/competitions/';

  constructor(private httpClient: HttpClient) { }

  public createCompetition(competition: Competition, professorName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'newCompetition/' + professorName, competition);
  }

  public getCompetitionsByProfessor(professorName: string): Observable<Competition[]> {
    return this.httpClient.get<Competition[]>(this.apiUrl + 'getCompetitions/' + professorName);
  }

  public deleteCompetition(id: number): Observable<Contest> {
    return this.httpClient.delete<Contest>(this.apiUrl + 'deleteCompetition/' + id);
  }

  public getStudentsByCompetition(id: number): Observable<User[]> {
    return this.httpClient.get<User[]>(this.apiUrl + 'getStudents/' + id);
  }

  public createUser(competitionId: number, userId: number): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'linkStudentToCompetition/' + competitionId + '/' + userId, null);
  }

  public addByExcel(competitionId: number, formData: FormData) {
    return this.httpClient.post<any>(this.apiUrl + 'addStudentsByExcel/' + competitionId, formData);
  }
}
