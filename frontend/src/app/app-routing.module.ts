import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { WordleComponent } from './wordle/wordle.component';
import { ProdGuardService as guard} from './guards/prod-guard.service';
import { CompetitionComponent } from './competition/competition.component';
import { CompetitionListComponent } from './competition/competition-list.component';

const routes: Routes = [
  {path: '', component: IndexComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent, canActivate: [guard], data: { expectedRol: ['admin'] }},
  {path: 'wordle', component: WordleComponent, canActivate: [guard], data: { expectedRol: ['student'] }},
  {path: 'competition', component: CompetitionComponent, canActivate: [guard], data: { expectedRol: ['professor'] }},
  {path: 'competitionList', component: CompetitionListComponent, canActivate: [guard], data: { expectedRol: ['professor'] }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
