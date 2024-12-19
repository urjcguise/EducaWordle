import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
//import { NgChartsModule } from 'ng2-charts';

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
    ContestRankingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule
    //,NgChartsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
