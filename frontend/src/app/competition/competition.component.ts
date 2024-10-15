import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompetitionService } from '../service/competition.service';
import { Competition } from '../models/competition';

@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css']
})
export class CompetitionComponent {

  competitionForm: FormGroup;

  constructor(private fb: FormBuilder, private competitionService: CompetitionService) {
    this.competitionForm = this.fb.group({
      competitionName: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.competitionForm.valid) {
      const newCompetition: Competition = this.competitionForm.value;
      this.competitionService.createCompetition(newCompetition).subscribe({
        next: (res) => {
          console.log('Competición creada:', res);
          alert('Competición creada con éxito.');
          this.competitionForm.reset();
        },
        error: (err) => {
          console.error('Error al crear la competición:', err);
          alert('Hubo un error al crear la competición.');
        },
      });
    }
  }
}
