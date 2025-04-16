import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Competition } from "../models/competition";
import { User } from "../models/user";
import { NewUser } from "../models/new-user";
import { WordlesStudentDTO } from "../models/wordles-student";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:9090/api/users/';

  constructor(private httpClient: HttpClient) { }

  public getCompetitionsByUserName(name: string): Observable<Competition[]> {
    return this.httpClient.get<Competition[]>(this.apiUrl + 'getCompetitions/' + name);
  }

  public getAllProfessors() {
    return this.httpClient.get<User[]>(this.apiUrl + 'getAllProfessors');
  }

  public getAllStudents() {
    return this.httpClient.get<User[]>(this.apiUrl + 'getAllStudents');
  }

  public deleteUser(userName: string) {
    return this.httpClient.delete<any>(this.apiUrl + 'deleteUserByName/' + userName);
  }

  public getUserData(userName: string) {
    return this.httpClient.get<User>(this.apiUrl + 'getUserData/' + userName);
  }

  public updateUser(oldUserName: string, uploadUser: NewUser) {
    return this.httpClient.post<any>(this.apiUrl + 'updateUser/' + oldUserName, uploadUser);
  }

  public getEmail(userName: string) {
    return this.httpClient.get(this.apiUrl + 'getUserEmail/' + userName, { responseType: 'text' });
  }

  public getAllWordles(userName: string) {
    return this.httpClient.get<WordlesStudentDTO[]>(this.apiUrl + 'getAllWordles/' + userName);
  }
}