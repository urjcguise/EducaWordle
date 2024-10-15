import { Component, OnInit } from '@angular/core';
import { Competition } from '../models/competition';
import { CompetitionService } from '../service/competition.service';

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit{

  competitions: Competition[] = [];

  constructor(private competitionService: CompetitionService) {}

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

}
