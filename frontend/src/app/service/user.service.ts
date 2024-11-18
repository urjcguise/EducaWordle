import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
export class UserService {
  
    private apiUrl = 'http://localhost:9090/api/competitions/';
  
    constructor(private httpClient: HttpClient) { }

    public createUser(competitionId: number, userId: number): Observable<any> {
        return this.httpClient.post<any>(this.apiUrl + 'linkStudentToCompetition/' + competitionId + '/' + userId, null);
    }
  }