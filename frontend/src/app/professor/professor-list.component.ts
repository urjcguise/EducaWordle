import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})
export class ProfessorListComponent implements OnInit {

  professorList: User[] = [];

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getAllProfessors().subscribe({
      next: (professors) => {
        this.professorList = professors;
      },
      error: (e) => {
        console.error('Error obteniendo los profesores', e)
      }
    })
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

  navigateToWatchCompetitions(arg0: string) {
    this.router.navigate(['/competiciones']);
  }
}
