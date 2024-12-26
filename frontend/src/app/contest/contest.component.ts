import { Component, Input, OnInit } from '@angular/core';
import { ContestService } from '../service/contest.service';
import { Contest } from '../models/contest';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-contest',
  templateUrl: './contest.component.html',
  styleUrls: ['./contest.component.css']
})
export class ContestComponent implements OnInit {

  contestForm!: FormGroup;
  competitionId!: number;

  constructor(private fb: FormBuilder, private contestService: ContestService) { 
    this.contestForm = this.fb.group({
      contestName: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.competitionId = history.state.competitionId;
    if (!this.competitionId) {
      console.error('No se encontró la competición');
    }
  }

  createContest(): void {
    if (this.contestForm.invalid) return;

    const newContest: Contest = {
      ...this.contestForm.value,
      startDate: new Date(this.contestForm.value.startDate),
      endDate: new Date(this.contestForm.value.endDate)
    };

    if (newContest.startDate >= newContest.endDate) {
      alert('La fecha de inicio es posterior a la de finalización');
      return;
    }

    this.contestService.createContest(newContest, this.competitionId).subscribe({
      next: () => {
        alert('Concurso creado con éxito');
        this.contestForm.reset();
      },
      error: (err) => console.error('Error al crear concurso', err)
    });
  }
}
