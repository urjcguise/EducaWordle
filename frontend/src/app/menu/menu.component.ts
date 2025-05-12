import { Component, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  @Input() showBackButton: boolean = true;
  @Input() mainPage: boolean = false;
  @Input() isPlayingWordle: boolean = false;
  @Output() backClicked = new EventEmitter<void>();

  isLogged = false;
  isAdmin = false;
  isProfessor = false;
  isStudent = false;

  mobilePhone: boolean = false;

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {

    this.mobilePhone = window.innerWidth < 450;

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

  @HostListener('window:resize', [])
  onResize() {
    this.checkWindowSize();
  }

  private checkWindowSize() {
    this.mobilePhone = window.innerWidth < 450;
  }
}
