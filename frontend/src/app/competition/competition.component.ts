import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompetitionService } from '../service/competition.service';
import { Competition } from '../models/competition';
import { NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css']
})
export class CompetitionComponent {

  competitionForm: FormGroup;
  professorName: string = '';

  constructor(private fb: FormBuilder, private competitionService: CompetitionService, private router: Router) {
    this.professorName = history.state.professorName;
    this.competitionForm = this.fb.group({
      competitionName: ['', Validators.required]
    });
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  onSubmit(): void {
    if (this.competitionForm.valid) {
      const newCompetition: Competition = this.competitionForm.value;
      this.competitionService.createCompetition(newCompetition, this.professorName).subscribe({
        next: (res) => {
          console.log('Competición creada:', res);
          alert('Competición creada con éxito');
          this.competitionForm.reset();
        },
        error: (err) => {
          console.error('Error al crear la competición:', err);
          alert('Hubo un error al crear la competición');
        },
      });
    }
  }

  goBack() {
    this.router.navigate(['/competiciones']);
  }
}
