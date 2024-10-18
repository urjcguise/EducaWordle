import { Component, OnInit } from '@angular/core';
import { Contest } from '../models/contest';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-contest-list',
  templateUrl: './contest-list.component.html',
  styleUrls: ['./contest-list.component.css']
})
export class ContestListComponent implements OnInit {

  contests: Contest[] = [];
  competitionId!: number;

  constructor(private contestService: ContestService, private route: ActivatedRoute) {}
  
  ngOnInit(): void {
    this.competitionId = +this.route.snapshot.paramMap.get('competitionId')!;
    this.loadContests();
  }

  loadContests(): void {
    this.contestService.getContestsByCompetition(this.competitionId).subscribe({
      next: (data) => {
        this.contests = data;
      },
      error: (error) => {
        console.error('Error consiguiendo los concursos', error);
      }
    });
  }

}
