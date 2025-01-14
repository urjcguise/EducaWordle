import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  professorList: User[] = [];
  studentList: User[] = [];

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getAllProfessors().subscribe({
      next: (professors) => {
        this.professorList = professors;
      },
      error: (e) => {
        console.error('Error obteniendo los profesores', e)
      }
    });

    this.userService.getAllStudents().subscribe({
      next: (students) => {
        this.studentList = students;
      },
      error: (e) => {
        console.error('Error obteniendo los profesores', e)
      }
    });
  }

  addProfessor() {
    this.router.navigate(['/nuevoProfesor']);
  }
  
  deleteProfessor(professorName: string) {
    this.userService.deleteUser(professorName).subscribe({
      next: () => {
        alert('Profesor eliminado correctamente');
      },
      error: (e) => {
        console.log('Error eliminando al profesor', e);
      }
    });
  }

  navigateToWatchCompetitions(professorName: string) {
    this.router.navigate(['/competiciones'], { state: { professorName } });
  }

  navigateToEditUser(userName: string) {
    this.router.navigate([userName + '/editarUsuario']);
  }
}
