import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { CompetitionService } from '../service/competition.service';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: User[] = [];
  competitionId!: number;

  constructor(private competitionService: CompetitionService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
    this.competitionService.getStudentsByCompetition(this.competitionId).subscribe({
      next: (stdts) => {
        this.students = stdts;
      },
      error: (error) => {
        console.error('Error consiguiendo los alumnos', error);
      }
    });
  }

  addStudent(): void {
    this.router.navigate(['/' + '/nuevoAlumno'], { state: {competitionId: this.competitionId} });
  }
}
