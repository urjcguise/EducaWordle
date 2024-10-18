import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  isLogged = false;
  userName = '';

  roles!: string[];
  authority!: string;
  isAdmin = false;
  isProfessor = false;
  isStudent = false;

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      } else if (rol === 'ROLE_PROFESSOR') {
        this.isProfessor = true;
      } else if (rol === 'ROLE_STUDENT') {
        this.isStudent = true;
      }
    })
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.userName = this.tokenService.getUserName() ?? '';
    } else {
      this.isLogged = false;
      this.userName = '';
    }
  }

}
