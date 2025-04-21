import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import { UserService } from '../service/user.service';
import { WordlesStudentDTO } from '../models/wordles-student';
import { NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-wordle-list-student',
  templateUrl: './wordle-list-student.component.html',
  styleUrls: ['./wordle-list-student.component.css']
})
export class WordleListStudentComponent implements OnInit {

  competitionList: WordlesStudentDTO[] = [];

  constructor(private tokenService: TokenService, private userService: UserService, private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.userService.getAllWordles(this.tokenService.getUserName()!).subscribe({
      next: (data) => this.competitionList = data,
      error: (err) => console.error('Error al obtener wordles:', err)
    });
  }

  goBack() {
    this.router.navigate(['/competiciones']);
  }
}
