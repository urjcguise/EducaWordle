import { Component, OnInit } from '@angular/core';
import { Game, State, WordleState } from '../models/wordle-state';
import { ContestService } from '../service/contest.service';
import { ActivatedRoute } from '@angular/router';
import { UserState } from '../models/user-state';

@Component({
  selector: 'app-contest-statistics',
  templateUrl: './contest-statistics.component.html',
  styleUrls: ['./contest-statistics.component.css']
})
export class ContestStatisticsComponent implements OnInit{
  
  contestName!: string;

  usersWithStates: { userName: string; state: State[] }[] = [];
  studentsName: string[] = [];
  wordleStates: WordleState[] = [];

  constructor(private contestService: ContestService, private route: ActivatedRoute) {}
  
  ngOnInit(): void {
    this.contestName = this.route.snapshot.paramMap.get('contestName')!;
    this.contestService.getUserAndState(this.contestName).subscribe({
      next: (data) => {
        this.usersWithStates = data.map((elem: UserState) => ({
          userName: elem.userName,
          state: elem.state.games.map((game: Game) => game.state), 
      }));
      },
      error: (error) => {
        console.error('Error consiguiendo los usuarios y sus estados', error);
      },
    });
  }
}
