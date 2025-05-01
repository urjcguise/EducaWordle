import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { prodGuard } from './guards/prod.guard';
import { CompetitionListComponent } from './competition/competition-list.component';
import { ContestListComponent } from './contest/contest-list.component';
import { EditContestComponent } from './contest/edit-contest.component';
import { PlayWordleComponent } from './wordle/play-wordle.component';
import { StudentListComponent } from './student/student-list.component';
import { ContestStatisticsComponent } from './contest/contest-statistics.component';
import { ContestRankingComponent } from './contest/contest-ranking.component';
import { NewProfessorComponent } from './professor/new-professor.component';
import { UserListComponent } from './user/user-list.component';
import { EditUserComponent } from './user/edit-user.component';
import { WordleListComponent } from './wordle/wordle-list.component';
import { EditWordleComponent } from './wordle/edit-wordle.component';
import { FolderListComponent } from './folder/folder-list.component';
import { ContestViewComponent } from './contest/contest-view.component';
import { CompetitionRankingComponent } from './competition/competition-ranking.component';
import { WordleListStudentComponent } from './wordle/wordle-list-student.component';
import { CompetitionViewComponent } from './competition/competition-view.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrar', component: RegisterComponent, canActivate: [prodGuard], data: { expectedRol: ['admin'] } },
  { path: 'usuarios', component: UserListComponent, canActivate: [prodGuard], data: { expectedRol: ['admin'] } },
  { path: 'wordle', component: PlayWordleComponent, canActivate: [prodGuard], data: { expectedRol: ['student'] } },
  { path: 'competiciones', component: CompetitionListComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: 'competicion/:competitionName', component: CompetitionViewComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':competitionName/ranking', component: CompetitionRankingComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: 'wordles', component: WordleListComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'admin'] } },
  { path: 'wordlesResueltos', component: WordleListStudentComponent, canActivate: [prodGuard], data: { expectedRol: ['student'] } },
  { path: ':folderId/wordles', component: FolderListComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':wordle/editarWordle', component: EditWordleComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':competitionName/concursos', component: ContestListComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: ':contestId/concurso', component: ContestViewComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: ':contestId/editarConcurso', component: EditContestComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'admin'] } },
  { path: ':userName/editarUsuario', component: EditUserComponent, canActivate: [prodGuard], data: { expectedRol: ['admin'] } },
  { path: ':competitionName/alumnos', component: StudentListComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'admin'] } },
  { path: 'nuevoProfesor', component: NewProfessorComponent, canActivate: [prodGuard], data: { expectedRol: ['admin'] } },
  { path: ':contestId/verEstadisticas', component: ContestStatisticsComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'student', 'admin'] } },
  { path: ':contestId/verRanking', component: ContestRankingComponent, canActivate: [prodGuard], data: { expectedRol: ['professor', 'student', 'admin'] } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
