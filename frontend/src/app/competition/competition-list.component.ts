import { Component, OnInit } from '@angular/core';
import { Competition } from '../models/competition';
import { CompetitionService } from '../service/competition.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  competitions: Competition[] = [];

  constructor(private competitionService: CompetitionService, private router: Router) { }

  ngOnInit(): void {
    this.loadCompetitions();
  }

  loadCompetitions(): void {
    this.competitionService.getCompetitions().subscribe({
      next: (data) => {
        this.competitions = data;
      },
      error: (error) => {
        console.error('Error consiguiendo las competiciones', error);
      }
    });
  }

  viewContests(competitionName: string, competitionId: number): void {
    this.router.navigate(['/' + competitionName + '/concursos'], { state: {competitionId }});
  }

  deleteCompetition(id: number): void {
    const confirmDelete = confirm('¿Estás seguro de que deseas eliminar esta competición?');
    if (confirmDelete) {
      this.competitionService.deleteCompetition(id).subscribe({
        next: () => {
          alert('Competición eliminada con éxito.');
          this.loadCompetitions();
        },
        error: (err) => console.error('Error al eliminar la competición:', err)
      });
    }
  }

  navigateToCreateContest(competitionId: number): void {
    this.router.navigate(['/nuevoConcurso'], { state: { competitionId } });
  }
}
