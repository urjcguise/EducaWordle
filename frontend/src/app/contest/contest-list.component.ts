import { Component, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { WordleService } from '../service/wordle.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-contest-list',
  templateUrl: './contest-list.component.html',
  styleUrls: ['./contest-list.component.css']
})
export class ContestListComponent implements OnInit {

  contests: Contest[] = [];
  contestsWithState: { contest: Contest; state: 'upcoming' | 'ongoing' | 'finished' }[] = [];
  competitionName!: string;
  competitionId!: number;

  noContests = true;
  isProfessor = false;
  isStudent = false;

  constructor(private contestService: ContestService, private route: ActivatedRoute, private tokenService: TokenService, private router: Router, private wordleService: WordleService) { }

  ngOnInit() {
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

  async loadContests() {
    this.contestService.getContestsByCompetition(this.competitionName).subscribe({
      next: async (data) => {
        if (data.length == 0) {
          this.noContests = true;
        } else {
          this.contests = data.map(contest => ({
            ...contest,
            startDate: new Date(contest.startDate),
            endDate: new Date(contest.endDate)
          }));

          const contestsWithStatePromises = this.contests.map(async (contest) => ({
            contest,
            state: await this.getContestState(contest)
          }));

          this.contestsWithState = await Promise.all(contestsWithStatePromises);

          this.noContests = false;
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
          alert('Concurso eliminado con éxito.');
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

  navigateToCreateContest() {
    this.router.navigate(['/nuevoConcurso'], { state: { competitionId: this.competitionId } });
  }

  navigateToPlayWordle(contestName: string, wordleIndex: number) {
    // El wordleIndex está por si se realiza la funcionalidad que se resuelvan los wordle de manera aleatoria
    this.router.navigate(['/wordle'], { state: { contestName, wordleIndex, competitionName: this.competitionName } });
  }

  navigateToWatchStatistics(contestName: string) {
    console.log(this.competitionName)
    this.router.navigate([`/${contestName}/verEstadisticas`], { state: {competitionName: this.competitionName} });
  }

  navigateToRanking(contestName: string) {
    this.router.navigate([`/${contestName}/verRanking`]);
  }

  getContestState(contest: Contest): Promise<'upcoming' | 'ongoing' | 'finished'> {
    const now = new Date();

    if (contest.startDate == null && contest.endDate == null) {
      return Promise.resolve('upcoming');
    } else if (now < contest.startDate) {
      return Promise.resolve('upcoming');
    } else if (now >= contest.startDate && now <= contest.endDate) {
      if (this.isStudent) {
        return firstValueFrom(this.contestService.getContestState(contest.contestName, this.tokenService.getUserName()!))
          .then((state) => {
            for (const game of state.games) {
              if (!game.finished) {
                return 'ongoing';
              }
            }
            return 'finished';
          })
          .catch((e) => {
            console.error("Error obteniendo el estado", e);
            return 'ongoing';
          });
      }
      return Promise.resolve('ongoing');
    } else {
      return Promise.resolve('finished');
    }
  }



  copyContest(oldContest: Contest) {

    const now = new Date();
    const oneYearLater = new Date();
    oneYearLater.setFullYear(now.getFullYear() + 1);

    const newContest = {
      contestName: oldContest.contestName + "_copia",
      competition: oldContest.competition,
      startDate: now,
      endDate: oneYearLater,
      numTries: oldContest.numTries,
      useDictionary: oldContest.useDictionary,
      useExternalFile: oldContest.useExternalFile,
      fileRoute: oldContest.fileRoute,
      wordles: []
    };

    this.contestService.copyContest(newContest, oldContest.contestName).subscribe({
      next: () => {
        const wordStrings: string[] = oldContest.wordles.map(wordle => wordle.word);
        this.wordleService.saveWordles(wordStrings, newContest.contestName).subscribe({
          next: () => {
            alert("Concurso copiado correctamente");
          },
          error: (error) => {
            console.error('Error guardando los wordle', error);
          }
        });
        this.loadContests();
      },
      error: (error) => {
        console.error('Error copiando el concurso', error);
      }
    });
  }
}
