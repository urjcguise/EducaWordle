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

  public deleteWordles(wordlesName: string[]): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "deleteWordles", wordlesName);
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

  public createFolder(folderName: string, professorName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "newFolder/" + folderName, professorName);
  }

  public deleteFolders(foldersIds: number[]): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "deleteFolders", foldersIds);
  }

  public getFoldersByProfessor(professorName: string): Observable<Folder[]> {
    return this.httpClient.get<Folder[]>(this.apiUrl + "getFoldersByProfessor/" + professorName);
  }

  public editFolder(oldFolderNameId: number, newFolderName: string): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + "editFolder/" + oldFolderNameId, newFolderName);
  }

  public getFoldersByFolderId(folderId: number): Observable<Folder[]> {
    return this.httpClient.get<Folder[]>(this.apiUrl + "getFoldersByFolderId/" + folderId);
  }

  public getFolder(folderId: number): Observable<Folder> {
    return this.httpClient.get<Folder>(this.apiUrl + "getFolder/" + folderId);
  }

  public getWordlesByFolderId(folderId: number): Observable<Wordle[]> {
    return this.httpClient.get<Wordle[]>(this.apiUrl + "getWordlesByFolderId/" + folderId);
  }

  public createFolderInsideFolder(newFolderName: string, professorName: string, folderId: number) {
    return this.httpClient.post<any>(this.apiUrl + "newFolderInsideFolder/" + newFolderName + '/' + folderId, professorName);
  }

  public moveToFolder(folderId: number, wordles: string[]) {
    return this.httpClient.post<any>(this.apiUrl + "moveToFolder/" + folderId, wordles);
  }
}
