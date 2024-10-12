import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { LoginUser } from '../models/login-user';
import { catchError, of, tap } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUser!: LoginUser;
  userName!: string;
  password!: string;
  roles!: string[];
  errMsj!: string;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  onLogin(): void {
    this.loginUser = new LoginUser(this.userName, this.password);
    this.authService.loginUser(this.loginUser).pipe(
      tap((data) => {
        this.isLogged = true;
        this.tokenService.setToken(data.token);
        this.tokenService.setUserName(data.userName);
        this.tokenService.setAuthorities(data.authorities);
        this.roles = data.authorities;
        this.router.navigate(['/']);
      }),
      catchError((err) => {
        this.isLogged = false;
        this.isLoginFail = true;

        if (err && err.error && err.error.message) {
          this.errMsj = err.error.message;  // Mensaje de error desde el servidor
        } else {
          this.errMsj = "Error en el inicio de sesión. Intenta de nuevo.";  // Mensaje de error genérico
        }
        
        console.log(this.errMsj);

        return of(null); // Retorna un observable vacío en caso de error
      })
    ).subscribe();
  }

}
