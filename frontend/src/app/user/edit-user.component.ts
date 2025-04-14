import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { NewUser } from '../models/new-user';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  updatedUser: NewUser = new NewUser('', '', '', []);
  oldUserName: string = '';
  userName: string = '';
  email: string = '';
  password: string = '';

  constructor(private route: ActivatedRoute, private userService: UserService, private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit(): void {
    this.oldUserName = this.route.snapshot.paramMap.get('userName') || '';
    this.userService.getUserData(this.oldUserName).subscribe({
      next: (user) => {
        this.userName = user.username;
        this.email = user.email;
      },
      error: (e) => {
        console.error('Error obteniendo el usuario', e);
      }
    });
  }

  onRegister() {
    this.updatedUser = {
      name: this.userName,
      email: this.email,
      password: this.password,
      roles: []
    }
    this.userService.updateUser(this.oldUserName, this.updatedUser).subscribe({
      next: () => {
        alert('Usuario correctamente actualizado');
      },
      error: (e) => {
        console.error('Error actualizando el usuario', e);
      }
    })
  }

  goBack() {
    this.router.navigate(['/usuarios']);
  }
}
