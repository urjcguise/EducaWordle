import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Wordle } from '../models/wordle';

@Injectable({
  providedIn: 'root'
})
export class WordleService {
  
  private apiUrl = 'http://localhost:9090/api/wordle/';

  constructor(private httpClient: HttpClient) {}

  public saveWordles(wordles: string[], contestName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "newWordles/" + contestName, wordles);
  }

  public deleteWordles(contestName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "deleteWordles/", contestName);
  }

  public getWordles(contestName: string): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordles/" + contestName);
  }
}
