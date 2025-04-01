import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  constructor(private tokenService: TokenService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      if (this.tokenService.getAuthorities().includes("ROLE_ADMIN"))
        this.router.navigate(['/usuarios']);
      else
        this.router.navigate(['/competiciones']);
    }
  }
}
