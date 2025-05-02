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

  constructor(private httpClient: HttpClient) { }

  public saveWordles(wordles: string[], contestId: number, professorName: string, folderId: number): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "newWordles/" + contestId + '/' + professorName + '/' + folderId, wordles);
  }

  public deleteWordles(wordles: Wordle[]): Observable<any> {
    return this.httpClient.delete<any>(this.apiUrl + "deleteWordles", {
      body: wordles
    });
  }

  public editWordle(wordle: string, newWordle: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + 'editWordle/' + wordle, newWordle);
  }

  public updateWordle(wordInitial: string, wordUpdated: string, contests: Contest[]): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "updateWordle/" + wordInitial + '/' + wordUpdated, contests);
  }

  public getWordlesByContest(contestId: number): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordlesByContest/" + contestId);
  }

  public getWordlesByProfessor(professorName: string): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordlesByProfessor/" + professorName);
  }

  public getContestsIsUsed(wordle: string): Observable<Contest[]> {
    return this.httpClient.get<Contest[]>(this.apiUrl + "getContestsWhereIsUsed/" + wordle);
  }

  public checkWordleAttempt(contestId: number, wordle: string, wordleIndex: number, userEmail: string) {
    return this.httpClient.get<number[]>(this.apiUrl + "checkWordleAttempt/" + contestId + "/" + wordleIndex + "/" + wordle + '/' + userEmail);
  }

  public getWordleInContest(contestId: number, wordleIndex: number) {
    return this.httpClient.get<Wordle>(this.apiUrl + "getWordleInContest/" + contestId + "/" + wordleIndex);
  }

  public createFolder(folderName: string, professorName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "newFolder/" + folderName, professorName);
  }

  public deleteFolders(foldersIds: number[]): Observable<any> {
    return this.httpClient.delete<any>(this.apiUrl + "deleteFolders", {
      body: foldersIds
    });
  }

  public getFoldersByProfessor(professorName: string): Observable<Folder[]> {
    return this.httpClient.get<Folder[]>(this.apiUrl + "getFoldersByProfessor/" + professorName);
  }

  public editFolder(oldFolderId: number, newFolderName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "editFolder/" + oldFolderId, newFolderName);
  }

  public getFoldersInsideFolder(folderId: number): Observable<Folder[]> {
    return this.httpClient.get<Folder[]>(this.apiUrl + "getFoldersInsideFolder/" + folderId);
  }

  public getFolder(folderId: number): Observable<Folder> {
    return this.httpClient.get<Folder>(this.apiUrl + "getFolder/" + folderId);
  }

  public getWordlesInsideFolder(folderId: number): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordlesInsideFolder/" + folderId);
  }

  public createFolderInsideFolder(newFolderName: string, professorName: string, folderId: number) {
    return this.httpClient.post<any>(this.apiUrl + "newFolderInsideFolder/" + newFolderName + '/' + folderId, professorName);
  }

  public moveToFolder(folderId: number, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + "moveToFolder/" + folderId, wordles);
  }
}
