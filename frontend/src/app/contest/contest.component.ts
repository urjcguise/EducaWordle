import { Component, Input, OnInit } from '@angular/core';
import { ContestService } from '../service/contest.service';
import { Contest } from '../models/contest';
import { Router } from '@angular/router';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-contest',
  templateUrl: './contest.component.html',
  styleUrls: ['./contest.component.css']
})
export class ContestComponent implements OnInit{
  
  competitionId!: number;
  contestName = '';

  constructor(private contestService: ContestService, private router: Router, private tokenService: TokenService) {}

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;

    if (!this.competitionId) {
      console.error('No se encontró la competición');
    }
  }

  createContest(): void {
    const newContest: Contest = { contestName: this.contestName, competitionId: this.competitionId };
    this.contestService.createContest(newContest).subscribe({
      next: (res) => {
        alert('Concurso creado con éxito');
        this.contestName = '';
      },
      error: (err) => console.error('Error al crear concurso', err)
    });
  }
}
