import { Injectable } from '@angular/core';
import { NewUser } from '../models/new-user';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'
import { LoginUser } from '../models/login-user';
import { JwtDTO } from '../models/jwt-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = 'http://localhost:9090/auth/';

  constructor(private httpClient: HttpClient) { }

  public new(newUser: NewUser): Observable<any> {
    return this.httpClient.post<any>(this.authURL + 'newUser', newUser);
  }

  public loginUser(loginUser: LoginUser): Observable<JwtDTO> {
    return this.httpClient.post<JwtDTO>(this.authURL + 'login', loginUser);
  }
}
