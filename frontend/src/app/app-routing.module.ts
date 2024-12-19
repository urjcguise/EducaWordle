import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { ProdGuardService as guard} from './guards/prod-guard.service';
import { CompetitionComponent } from './competition/competition.component';
import { CompetitionListComponent } from './competition/competition-list.component';
import { ContestComponent } from './contest/contest.component';
import { ContestListComponent } from './contest/contest-list.component';
import { EditContestComponent } from './contest/edit-contest.component';
import { PlayWordleComponent } from './wordle/play-wordle.component';
import { StudentListComponent } from './student/student-list.component';
import { NewStudentComponent } from './student/new-student.component';
import { ContestStatisticsComponent } from './contest/contest-statistics.component';
import { ContestRankingComponent } from './contest/contest-ranking.component';

const routes: Routes = [
  {path: '', component: IndexComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent, canActivate: [guard], data: { expectedRol: ['admin'] }},
  {path: 'wordle', component: PlayWordleComponent, canActivate: [guard], data: { expectedRol: ['student'] }},
  {path: 'nuevaCompeticion', component: CompetitionComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: 'competiciones', component: CompetitionListComponent, canActivate: [guard], data: { expectedRol: ['professor', 'student'] }},
  {path: 'nuevoConcurso', component: ContestComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: ':competitionName/concursos', component: ContestListComponent, canActivate: [guard], data: { expectedRol: ['professor', 'student'] }},
  {path: ':contestName/editar', component: EditContestComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: ':competitionName/alumnos', component: StudentListComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: 'nuevoAlumno', component: NewStudentComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: ':contestName/verEstadisticas', component: ContestStatisticsComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: ':contestName/verRanking', component: ContestRankingComponent, canActivate: [guard], data: { expectedRol: ['professor', 'student'] }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
