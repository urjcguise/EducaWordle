import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { WordleComponent } from './wordle/wordle.component';
import { ProdGuardService as guard} from './guards/prod-guard.service';
import { CompetitionComponent } from './competition/competition.component';
import { CompetitionListComponent } from './competition/competition-list.component';
import { ContestComponent } from './contest/contest.component';
import { ContestListComponent } from './contest/contest-list.component';

const routes: Routes = [
  {path: '', component: IndexComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent, canActivate: [guard], data: { expectedRol: ['admin'] }},
  {path: 'wordle', component: WordleComponent, canActivate: [guard], data: { expectedRol: ['student'] }},
  {path: 'nuevaCompeticion', component: CompetitionComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: 'competiciones', component: CompetitionListComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: 'nuevoConcurso', component: ContestComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: ':competitionName/concursos', component: ContestListComponent, canActivate: [guard], data: { expectedRol: ['professor'] }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
