import { Component, OnInit } from '@angular/core';
import { NewUser } from '../models/new-user';
import { Observer } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { CompetitionService } from '../service/competition.service';

@Component({
  selector: 'app-new-student',
  templateUrl: './new-student.component.html',
  styleUrls: ['./new-student.component.css']
})
export class NewStudentComponent implements OnInit{

  competitionId!: number;

  newUser!: NewUser;
  userName!: string;
  email!: string;
  password!: string;
  errMsj!: string;

  constructor(private router: Router, private authService: AuthService, private competitionService: CompetitionService) {}
  
  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
  }

  onRegister(): void {

    const roles: string[] = ['student'];

    this.newUser = new NewUser(this.userName, this.email, this.password, roles);
    const observer: Observer<any> = {
      next: (userId) => {
        this.competitionService.createUser(this.competitionId, userId).subscribe({
          next: () => console.log('Usuario vinculado a la competición exitosamente'),
          error: (err) => console.error('Error al vincular al usuario:', err)
        });
      },
      error: (err) => {
        this.errMsj = err.error.mensaje;
        console.error('Error al crear la cuenta:', this.errMsj);
      },
      complete: () => {
        alert('Usuario creado correctamente');
      }
    };

    this.authService.new(this.newUser).subscribe(observer);
  }
}
