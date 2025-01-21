import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Wordle } from '../models/wordle';
import { Contest } from '../models/contest';
import { Folder } from '../models/folder';

@Injectable({
  providedIn: 'root'
})
export class WordleService {
  
  private apiUrl = 'http://localhost:9090/api/wordle/';

  constructor(private httpClient: HttpClient) {}

  public saveWordles(wordles: string[], contestName: string, professorName: string, folderName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "newWordles/" + contestName + '/' + professorName + '/' + folderName, wordles);
  }

  public deleteWordles(wordlesName: string[]): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "deleteWordles", wordlesName);
  }

  public updateWordle(wordInitial: string, wordUpdated: string, contests: Contest[]): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "updateWordle/" + wordInitial + '/' + wordUpdated, contests);
  }

  public getWordlesByContest(contestName: string): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordlesByContest/" + contestName);
  }

  public getWordlesByProfessor(professorName: string): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordlesByProfessor/" + professorName);
  }

  public getContestsIsUsed(wordle: string): Observable<Contest[]> {
    return this.httpClient.get<Contest[]>(this.apiUrl + "getContestsWhereIsUsed/" + wordle);
  }

  public createFolder(folderName: string, professorName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "newFolder/" + folderName, professorName);
  }

  public deleteFolders(foldersName: string[]): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "deleteFolders", foldersName);
  }

  public getFoldersByProfessor(professorName: string): Observable<Folder[]> {
    return this.httpClient.get<Folder[]>(this.apiUrl + "getFoldersByProfessor/" + professorName);
  }

  public editFolder(oldFolderName: string, newFolderName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "editFolder/" + oldFolderName, newFolderName);
  }

  public getFoldersByFolderName(folderName: string): Observable<Folder[]> {
    return this.httpClient.get<Folder[]>(this.apiUrl + "getFoldersByFolderName/" + folderName);
  }

  public getWordlesByFolderName(folderName: string): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordlesByFolderName/" + folderName);
  }

  public createFolderInsideFolder(newFolderName: string, professorName: string, folderName: string) {
    return this.httpClient.post<any>(this.apiUrl + "newFolderInsideFolder/" + newFolderName + '/' + folderName, professorName);
  }

  public moveToFolder(folderName: string, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + "moveToFolder/" + folderName, wordles);
  }
}
