import { Component, HostListener, OnInit } from '@angular/core';
import { CompetitionService } from '../service/competition.service';
import { NavigationStart, Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { UserService } from '../service/user.service';
import { Contest } from '../models/contest';

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  showBackButton: boolean = false;

  isModalOpen: boolean = false;

  isProfessor = false;
  isStudent = false;
  isAdmin = false;
  roles: string[] = [];

  noCompetitions = false;
  competitions: {
    id: number;
    name: string;
    contests: Contest[];
    isOpen: boolean;
    professorName: string;
  }[] = [];

  professorName: string = '';

  constructor(private competitionService: CompetitionService, private router: Router, private tokenService: TokenService, private userService: UserService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          if (this.isAdmin)
            this.router.navigate(['/usuarios']);
          else
            this.router.navigate(['/']);
        }
      }
    });
  }

  ngOnInit(): void {
    this.roles = this.tokenService.getAuthorities();
    if (this.roles.includes("ROLE_STUDENT")) {
      this.isStudent = true;
      this.loadCompetitionsStudent();
    } else if (this.roles.includes("ROLE_PROFESSOR")) {
      this.isProfessor = true;
      this.professorName = this.tokenService.getUserName()!;
      this.loadCompetitionsProfessor();
    } else {
      this.isAdmin = true;
      this.professorName = history.state.professorName;
      this.loadCompetitionsProfessor();
    }
  }

  loadCompetitionsStudent() {
    this.userService.getCompetitionsByUserName(this.tokenService.getUserName()!).subscribe({
      next: (data) => {
        if (data.length != 0) {
          data.forEach(compe => {
            this.competitions.push({
              id: compe.id,
              name: compe.competitionName,
              contests: compe.contests.sort((a, b) => a.contestName.localeCompare(b.contestName)),
              isOpen: true,
              professorName: compe.professor.username
            })
          });
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

  loadCompetitionsProfessor() {
    this.competitionService.getCompetitionsByProfessor(this.professorName).subscribe({
      next: (data) => {
        this.competitions = [];
        data.forEach(compe => {
          this.competitions.push({
            id: compe.id,
            name: compe.competitionName,
            contests: compe.contests.sort((a, b) => a.contestName.localeCompare(b.contestName)),
            isOpen: true,
            professorName: compe.professor.username
          })
        });
      },
      error: (error) => {
        console.error('Error consiguiendo las competiciones', error);
      }
    });
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event) {
    const targetElement = event.target as HTMLElement;

    if (!targetElement.closest('.competition-list-container') && !targetElement.closest('.title-container')) {
      this.closeModal();
    }
  }

  toggleCompetition(competition: any) {
    competition.isOpen = !competition.isOpen;
  }

  viewContest(contestId: number, competitionName: string, professorName: string) {
    this.router.navigate(['/' + contestId + '/concurso'], { state: { competitionName: competitionName, professorName: professorName } });
  }

  openCreateCompetition() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    if (this.isProfessor || this.isAdmin) {
      this.competitions = [];
      this.loadCompetitionsProfessor();
    }
  }

  navigateToCompetitionView(competitionName: string, competitionId: number) {
    this.router.navigate(['/competicion/' + competitionName], { state: { professorName: this.professorName, competitionId: competitionId } });
  }
}
