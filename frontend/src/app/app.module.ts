import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { IndexComponent } from './index/index.component';
import { MenuComponent } from './menu/menu.component';
import { interceptorProvider } from './interceptors/prod-interceptor.service';
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


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    MenuComponent,
    CompetitionComponent,
    CompetitionListComponent,
    ContestComponent,
    ContestListComponent,
    EditContestComponent,
    PlayWordleComponent,
    StudentListComponent,
    NewStudentComponent,
    ContestStatisticsComponent,
    ContestRankingComponent,
    NewProfessorComponent,
    UserListComponent,
    EditUserComponent,
    WordleListComponent,
    WordleComponent,
    EditWordleComponent,
    FolderListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
