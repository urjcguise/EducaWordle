import { Component, OnInit } from '@angular/core';
import { WordleService } from '../service/wordle.service';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { Contest } from '../models/contest';
import { ContestService } from '../service/contest.service';
import { Competition } from '../models/competition';
import { CompetitionService } from '../service/competition.service';

@Component({
  selector: 'app-edit-wordle',
  templateUrl: './edit-wordle.component.html',
  styleUrls: ['./edit-wordle.component.css']
})
export class EditWordleComponent implements OnInit {

  wordle: string = '';
  wordInitial: string = '';
  competitionWithContest: {
    competition: Competition;
    contests: {
      contest: Contest
    }[];
  }[] = [];
  contestsUse: Contest[] = [];

  professorName: string = '';

  constructor(private wordleService: WordleService, private route: ActivatedRoute, private tokenService: TokenService, private competitionService: CompetitionService, private contestService: ContestService, private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.wordle = this.route.snapshot.paramMap.get('wordle') || '';
    this.wordInitial = this.route.snapshot.paramMap.get('wordle') || '';
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.professorName = this.tokenService.getUserName()!;
    if (this.tokenService.getAuthorities().includes("ROLE_ADMIN"))
      this.professorName = history.state.professorName;

    this.competitionService.getCompetitionsByProfessor(this.professorName).subscribe({
      next: (competitions) => {
        competitions.forEach((competition) => {
          this.contestService.getContestsByCompetition(competition.competitionName).subscribe({
            next: (contests) => {
              const now = new Date();
              const formattedContests = contests
                .filter((contest) => new Date(contest.endDate) > now)
                .map((contest) => ({ contest }));

              this.competitionWithContest.push({
                competition: competition,
                contests: formattedContests,
              });
            },
            error: (e) => {
              console.error('Error obteniendo los concursos', e);
            },
          });
        });
      },
      error: (e) => {
        console.error('Error obteniendo las competiciones', e);
      },
    });

    this.wordleService.getContestsIsUsed(this.wordle).subscribe({
      next: (contests) => {
        this.contestsUse = contests;
      },
      error: (e) => {
        console.error('Error obteniendo los concursos donde se utiliza el wordle', e);
      }
    })
  }

  isContestSelected(contest: Contest): boolean {
    return this.contestsUse.some((usedContest) => usedContest.id === contest.id);
  }

  toggleContestSelection(contest: Contest): void {
    const index = this.contestsUse.findIndex((usedContest) => usedContest.id === contest.id);
    if (index > -1) {
      this.contestsUse.splice(index, 1);
    } else {
      this.contestsUse.push(contest);
    }
  }

  updateWordle(): void {
    this.wordleService.updateWordle(this.wordInitial, this.wordle, this.contestsUse).subscribe({
      next: () => {
        alert('Wordle editado correctamente');
      },
      error: (e) => {
        console.error('Error editando el wordle', e);
      }
    });
  }

  goBack() {
    this.router.navigate(['/wordles']);
  }
}
