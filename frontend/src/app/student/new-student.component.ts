import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NewUser } from '../models/new-user';
import { Observer } from 'rxjs';
import { AuthService } from '../service/auth.service';
import { CompetitionService } from '../service/competition.service';

@Component({
  selector: 'app-new-student',
  templateUrl: './new-student.component.html',
  styleUrls: ['./new-student.component.css']
})
export class NewStudentComponent {

  @Input() competitionId!: number;
  @Input() professorName!: string;
  @Input() competitionName!: string;
  @Output() close = new EventEmitter<void>();

  newUser!: NewUser;
  userName!: string;
  email!: string;
  password!: string;
  errMsj!: string;

  selectedFile: File | null = null;

  activeTab: string = 'single';

  constructor(private authService: AuthService, private competitionService: CompetitionService) { }

  onRegister(): void {
    const roles: string[] = ['student'];

    this.newUser = new NewUser(this.userName, this.email, this.password, roles);
    const observer: Observer<any> = {
      next: (userId) => {
        this.competitionService.createUser(this.competitionId, userId).subscribe({
          next: () => console.log('Usuario vinculado a la competición exitosamente'),
          error: (err) => console.error('Error al vincular al usuario:', err)
        });
      },
      error: (err) => {
        if (err.status === 409 && err.error) {
          const existingUserId = err.error;
          this.competitionService.createUser(this.competitionId, existingUserId).subscribe({
            next: () => {
              console.log('Usuario vinculado a la competición exitosamente');
              this.close.emit();
            },
            error: (err) => console.error('Error al vincular al usuario:', err)
          });
        } else {
          this.errMsj = err.error.mensaje;
          console.error('Error al crear la cuenta:', this.errMsj);
        }
      },
      complete: () => {
        alert('Usuario creado correctamente');
        this.close.emit();
      }
    };

    this.authService.new(this.newUser).subscribe(observer);
  }

  addStudentExcel() {
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append('file', this.selectedFile!);

    this.competitionService.addByExcel(this.competitionId, formData).subscribe({
      next: () => {
        alert('Alumnos creados correctamente');
      },
      error: (error) => {
        console.error('Error creando los alumnos', error);
      }
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
}