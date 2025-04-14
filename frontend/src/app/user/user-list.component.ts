import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../service/user.service';
import { NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  showBackButton: boolean = false;
  professorList: User[] = [];
  studentList: User[] = [];

  constructor(private userService: UserService, private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.router.navigate(['/usuarios']);
        }
      }
    });
  }

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
    const confirmDelete = confirm('¿Está seguro de que desea eliminar este profesor?');
    if (confirmDelete) {
      this.userService.deleteUser(professorName).subscribe({
        next: () => {
          alert('Profesor eliminado correctamente');
        },
        error: (e) => {
          console.log('Error eliminando al profesor', e);
        }
      });
    }
  }

  deleteStudent(studentName: string) {
    const confirmDelete = confirm('¿Está seguro de que desea eliminar este alumno?');
    if (confirmDelete) {
      this.userService.deleteUser(studentName).subscribe({
        next: () => {
          alert('Alumno eliminado correctamente');
        },
        error: (e) => {
          console.log('Error eliminando al alumno', e);
        }
      });
    }
  }

  navigateToWatchCompetitions(professorName: string) {
    this.router.navigate(['/competiciones'], { state: { professorName } });
  }

  navigateToEditUser(userName: string) {
    this.router.navigate([userName + '/editarUsuario']);
  }

  navigateToWordleList(professorName: string) {
    this.router.navigate(['/wordles'], { state: { professorName: professorName } });
  }
}
