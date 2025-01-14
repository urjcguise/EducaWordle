import { Component, OnInit } from '@angular/core';
import { NewUser } from '../models/new-user';
import { TokenService } from '../service/token.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { Observer } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  selectedRoles = {
    admin: false,
    professor: false,
    student: false
  };

  newUser!: NewUser;
  userName!: string;
  email!: string;
  password!: string;
  errMsj!: string;
  authorities!: string[];
  isLogged = false;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  onRegister(): void {

    const roles: string[] = [];

    if (this.selectedRoles.admin) {
      roles.push('admin');
    }
    if (this.selectedRoles.professor) {
      roles.push('professor');
    }
    if (this.selectedRoles.student) {
      roles.push('student');
    }

    this.newUser = new NewUser(this.userName, this.email, this.password, roles);
    const observer: Observer<any> = {
      next: () => {
        console.log('Cuenta creada exitosamente');
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.errMsj = err.error.mensaje;
        console.error('Error al crear la cuenta:', this.errMsj);
      },
      complete: () => {
        console.log('Registro completado.');
      }
    };

    this.authService.new(this.newUser).subscribe(observer);
  }

}
