import { Component, OnInit } from '@angular/core';
import { Competition } from '../models/competition';
import { CompetitionService } from '../service/competition.service';
import { Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  isProfessor = false;
  isStudent = false;
  roles: string[] = [];

  competitions: Competition[] = [];
  noCompetitions = true; 

  professorName: string = '';

  constructor(private competitionService: CompetitionService, private router: Router, private tokenService: TokenService, private userService: UserService) { }

  ngOnInit(): void {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_PROFESSOR') {
        this.isProfessor = true;
        this.professorName = this.tokenService.getUserName()!;
        this.loadCompetitionsProfessor();
      } else if (rol === 'ROLE_STUDENT') {
        this.isStudent = true;
        this.loadCompetitionsStudent();
      }
    })
  }

  loadCompetitionsStudent() {
    console.log(this.tokenService.getUserName());
    this.userService.getCompetitionsByUserName(this.tokenService.getUserName()!).subscribe({
      next: (data) => {
        if (data.length != 0) {
          this.competitions = data;
          this.noCompetitions = false;
        } else {
          this.noCompetitions = true;
        }
      },
      error: (error) => {
        console.error('Error consiguiendo las competiciones', error);
      }
    });
  }

  loadCompetitionsProfessor(): void {
    this.competitionService.getCompetitions(this.professorName).subscribe({
      next: (data) => {
        this.competitions = data;
      },
      error: (error) => {
        console.error('Error consiguiendo las competiciones', error);
      }
    });
  }

  viewContests(competitionName: string, competitionId: number): void {
    this.router.navigate(['/' + competitionName + '/concursos'], { state: {competitionId} });
  }

  deleteCompetition(id: number): void {
    const confirmDelete = confirm('¿Estás seguro de que deseas eliminar esta competición?');
    if (confirmDelete) {
      this.competitionService.deleteCompetition(id).subscribe({
        next: () => {
          alert('Competición eliminada con éxito.');
          this.loadCompetitionsProfessor();
        },
        error: (err) => console.error('Error al eliminar la competición:', err)
      });
    }
  }

  viewStudents(competitionName: string, competitionId: number): void {
    this.router.navigate(['/' + competitionName + '/alumnos'], { state: {competitionId} });
  }
}
