import { Component, OnInit } from '@angular/core';
import { NewUser } from '../models/new-user';
import { TokenService } from '../service/token.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser!: NewUser;
  userName!: string;
  email!: string;
  password!: string;
  errMsj!: string;
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
    this.newUser = new NewUser(this.userName, this.email, this.password);
    this.authService.new(this.newUser).subscribe(
      data => {
        // Aquí puedes agregar alguna lógica de éxito, como redirigir o mostrar un mensaje
        console.log('Cuenta creada exitosamente');
        this.router.navigate(['/login']);
      },
      err => {
        this.errMsj = err.error.mensaje;
        // Aquí puedes agregar alguna lógica para manejar el error
        console.error('Error al crear la cuenta:', this.errMsj);
      }
    );
  }

}
