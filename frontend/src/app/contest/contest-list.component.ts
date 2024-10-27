import { Component, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-contest-list',
  templateUrl: './contest-list.component.html',
  styleUrls: ['./contest-list.component.css']
})
export class ContestListComponent implements OnInit {

  contests: Contest[] = [];
  competitionName!: string;
  isProfessor = false;

  constructor(private contestService: ContestService, private route: ActivatedRoute, private tokenService: TokenService, private router: Router) { }

  ngOnInit(): void {
    this.competitionName = this.route.snapshot.paramMap.get('competitionName')!;
    if (!this.competitionName) {
      console.error('No se encontró la competición');
    }
    this.loadContests();
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.isProfessor = true;
  }

  loadContests(): void {
    this.contestService.getContestsByCompetition(this.competitionName).subscribe({
      next: (data) => {
        this.contests = data;
      },
      error: (error) => {
        console.error('Error consiguiendo los concursos', error);
      }
    });
  }

  deleteContest(contestName: string) {
    const confirmDelete = confirm('¿Estás seguro de que deseas eliminar esta concurso?');
    if (confirmDelete) {
      this.contestService.deleteContest(contestName).subscribe({
        next: () => {
          alert('Concurso eliminada con éxito.');
          this.loadContests();
        },
        error: (err) => console.error('Error al eliminar la concurso:', err)
      });
    }
  }

  navigateToEditWordle(contestName: string) {
    this.router.navigate([`${contestName}/editar`]);
  }
}
