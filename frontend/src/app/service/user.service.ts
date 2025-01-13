import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Competition } from "../models/competition";
import { User } from "../models/user";

@Injectable({
    providedIn: 'root'
  })
export class UserService {
  
    private apiUrl = 'http://localhost:9090/api/users/';
  
    constructor(private httpClient: HttpClient) { }

    public getCompetitionsByUserName(name: string): Observable<Competition[]> {
      return this.httpClient.get<Competition[]>(this.apiUrl + 'getCompetitions/' + name);
    }

    public addByExcel(competitionId: number, formData: FormData) {
      return this.httpClient.post<any>(this.apiUrl + 'addStudentsByExcel/' + competitionId, formData);
    }

    public getAllProfessors() {
      return this.httpClient.get<User[]>(this.apiUrl + 'getAllProfessors');
    }

    public deleteUser(userName: string) {
      return this.httpClient.post<any>(this.apiUrl + 'deleteStudentsByName/' + userName, null);
    }
  }