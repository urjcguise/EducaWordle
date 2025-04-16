import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  @Input() showBackButton: boolean = true;
  @Output() backClicked = new EventEmitter<void>();

  isLogged = false;
  isAdmin = false;
  isProfessor = false;
  isStudent = false;

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
    if (this.tokenService.getToken())
      this.isLogged = true;
    else
      this.isLogged = false;

    if (this.tokenService.getAuthorities().includes("ROLE_ADMIN"))
      this.isAdmin = true;
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.isProfessor = true;
    if (this.tokenService.getAuthorities().includes("ROLE_STUDENT"))
      this.isStudent = true;
  }

  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

  goBack() {
    this.backClicked.emit();
  }
}
