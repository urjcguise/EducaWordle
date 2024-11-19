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
  competitionId!: number;

  noContest = true;
  isProfessor = false;
  isStudent = false;

  constructor(private contestService: ContestService, private route: ActivatedRoute, private tokenService: TokenService, private router: Router) { }

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
    this.competitionName = this.route.snapshot.paramMap.get('competitionName')!;
    if (!this.competitionName) {
      console.error('No se encontró la competición');
    }
    this.loadContests();
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.isProfessor = true;
    if (this.tokenService.getAuthorities().includes("ROLE_STUDENT"))
      this.isStudent = true;
  }

  loadContests(): void {
    this.contestService.getContestsByCompetition(this.competitionName).subscribe({
      next: (data) => {
        if (data.length == 0)
          this.noContest = true;
        else {
          this.contests = data.map(contest => ({
            ...contest,
            startDate: new Date(contest.startDate),
            endDate: new Date(contest.endDate)
          }));
          this.noContest = false;
        }
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
    if (this.competitionId != null) {
      this.router.navigate([`/${contestName}/editar`]);
    } else {
      console.error("competitionId no está definido. Verifica su inicialización.");
    }
  }

  navigateToCreateContest(): void {
    this.router.navigate(['/nuevoConcurso'], { state: { competitionId: this.competitionId } });
  }

  getContestState(startDate: Date, endDate: Date): 'upcoming' | 'ongoing' | 'finished' {
    const now = new Date();
  
    if (now < startDate) {
      return 'upcoming';
    } else if (now >= startDate && now <= endDate) {
      return 'ongoing';
    } else {
      return 'finished';
    }
  }
}
