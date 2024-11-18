import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Competition } from "../models/competition";

@Injectable({
    providedIn: 'root'
  })
export class UserService {
  
    private apiUrl = 'http://localhost:9090/api/users/';
  
    constructor(private httpClient: HttpClient) { }

    public getCompetitionsByUserName(name: string): Observable<Competition[]> {
      return this.httpClient.get<Competition[]>(this.apiUrl + 'getCompetitions/' + name);
    }
  }