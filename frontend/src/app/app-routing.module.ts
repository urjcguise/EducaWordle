import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';
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
import { NewProfessorComponent } from './professor/new-professor.component';
import { UserListComponent } from './user/user-list.component';
import { EditUserComponent } from './user/edit-user.component';
import { WordleListComponent } from './wordle/wordle-list.component';
import { WordleComponent } from './wordle/wordle.component';
import { EditWordleComponent } from './wordle/edit-wordle.component';
import { FolderListComponent } from './folder/folder-list.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrar', component: RegisterComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'usuarios', component: UserListComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'wordle', component: PlayWordleComponent, canActivate: [guard], data: { expectedRol: ['student'] } },
  { path: 'nuevaCompeticion', component: CompetitionComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: 'competiciones', component: CompetitionListComponent, canActivate: [guard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: 'wordles', component: WordleListComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':folderId/wordles', component: FolderListComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':professorName/nuevosWordles', component: WordleComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':wordle/editarWordle', component: EditWordleComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: 'nuevoConcurso', component: ContestComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':competitionName/concursos', component: ContestListComponent, canActivate: [guard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: ':contestId/editarConcurso', component: EditContestComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':userName/editarUsuario', component: EditUserComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: ':competitionName/alumnos', component: StudentListComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: 'nuevoAlumno', component: NewStudentComponent, canActivate: [guard], data: { expectedRol: ['professor', 'admin'] } },
  { path: 'nuevoProfesor', component: NewProfessorComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: ':contestId/verEstadisticas', component: ContestStatisticsComponent, canActivate: [guard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: ':contestId/verRanking', component: ContestRankingComponent, canActivate: [guard], data: { expectedRol: ['professor', 'student', 'admin'] } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
