import { Component, OnInit } from '@angular/core';
import { WordleService } from '../service/wordle.service';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from '../service/token.service';
import { ContestService } from '../service/contest.service';
import { Folder } from '../models/folder';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-show-wordles',
  templateUrl: './show-wordles.component.html',
  styleUrls: ['./show-wordles.component.css']
})
export class ShowWordlesComponent implements OnInit {

  professorName: string = '';
  contestId: number = 0;

  wordles: string[] = [];
  selectedWordles: string[] = [];

  showAddModal = false;
  wordlesToAddSelection: string[] = [];

  editingWordle: string | null = null;
  editedValue: string = '';

  isModifyingOrder: boolean = false;
  initialWordleList: string[] = [];

  isCreatingNewWordle = false;
  newWordleValue = '';

  availableRootWordles: string[] = [];
  folders: Folder[] = [];
  foldersWithOutWordles: Folder[] = [];
  currentSelectedFolderId: string | number | null = 0;

  constructor(private wordleService: WordleService, private route: ActivatedRoute, private tokenService: TokenService, private contestService: ContestService) { }

  ngOnInit(): void {
    this.professorName = this.tokenService.getUserName() || '';
    this.contestId = Number(this.route.snapshot.paramMap.get('contestId'));
    this.loadContestWordles();
  }

  loadContestWordles() {
    this.wordleService.getWordlesByContest(this.contestId).subscribe({
      next: (wordleList) => {
        this.wordles = wordleList.map(w => w.word);
      },
      error: (e) => {
        console.error('Error obteniendo los wordles', e);
      }
    });
  }

  loadModalData(): void {

    forkJoin({
      rootWordlesRaw: this.wordleService.getWordlesByProfessor(this.professorName),
      rootFoldersRaw: this.wordleService.getFoldersByProfessor(this.professorName)
    }).subscribe({
      next: ({ rootWordlesRaw, rootFoldersRaw }) => {
        this.availableRootWordles = rootWordlesRaw
          .map(w => w.word)
          .filter(word => !this.wordles.includes(word))
          .sort((a, b) => a.localeCompare(b));

        this.folders = deepCopyFolders(rootFoldersRaw);
        this.deleteWordlesInFolder(rootFoldersRaw);
        this.folders.forEach(folder => sortWordlesInFolder(folder));
      },
      error: (e) => {
        console.error('Error obteniendo datos para modal', e);
        this.availableRootWordles = [];
        this.folders = [];
      }
    });
  }

  toggleSelection(wordle: string) {
    if (this.editingWordle) {
      return;
    }

    const index = this.selectedWordles.indexOf(wordle);
    if (index > -1) {
      this.selectedWordles.splice(index, 1);
    } else {
      this.selectedWordles.push(wordle);
    }
  }

  isSelected(wordle: string): boolean {
    if (this.editingWordle === wordle) {
      return false;
    }
    return this.selectedWordles.includes(wordle);
  }

  editWordle() {
    if (this.selectedWordles.length === 1) {
      const wordleToEdit = this.selectedWordles[0];
      this.editingWordle = wordleToEdit;
      this.editedValue = wordleToEdit;
      this.selectedWordles = [];
    }
  }

  saveEdit() {
    if (!this.editingWordle) return;

    const originalWordle = this.editingWordle;
    const newValue = this.editedValue.trim();

    if (newValue === '' || newValue === originalWordle) {
      this.cancelEdit();
      return;
    }

    if (this.wordles.some(w => w === newValue && w !== originalWordle) || this.availableRootWordles.some(w => w === newValue && w !== originalWordle)) {
      alert(`El wordle "${newValue}" ya existe en la lista`);
      return;
    }

    this.wordleService.editWordle(originalWordle, newValue).subscribe({
      next: () => {
        const index = this.wordles.indexOf(originalWordle);
        if (index > -1) {
          this.wordles[index] = newValue;
        }
        this.cancelEdit();
      },
      error: (e) => {
        console.error('Error al actualizar el wordle en el backend', e);
      }
    });
  }

  cancelEdit() {
    this.editingWordle = null;
    this.editedValue = '';
  }

  deleteWordle() {
    if (this.editingWordle) return;

    if (this.selectedWordles.length > 0) {
      if (!confirm(`¿Seguro que quieres eliminar ${this.selectedWordles.length} wordle(s)?`)) {
        return;
      }
      this.contestService.deleteWordlesInContest(this.contestId, this.selectedWordles).subscribe({
        next: () => {
          this.wordles = this.wordles.filter(w => !this.selectedWordles.includes(w));
          this.selectedWordles = [];
        },
        error: (e) => {
          console.error('Error eliminando los wordles', e);
        }
      });
    }
  }

  startModifyOrder() {
    this.isModifyingOrder = true;
    this.initialWordleList = [...this.wordles];
  }

  cancelModifyOrder() {
    this.wordles = this.initialWordleList;
    this.isModifyingOrder = false;
  }

  moveUp(index: number) {
    if (index > 0)
      [this.wordles[index - 1], this.wordles[index]] = [this.wordles[index], this.wordles[index - 1]];
  }

  moveDown(index: number) {
    if (index < this.wordles.length - 1)
      [this.wordles[index], this.wordles[index + 1]] = [this.wordles[index + 1], this.wordles[index]];
  }

  saveOrder() {
    this.contestService.changeWordlesPosition(this.contestId, this.wordles).subscribe({
      next: () => {
        alert('Orden correctamente modificado');
        this.isModifyingOrder = false;
      },
      error: (e) => {
        console.error('Error modificando el orden', e);
      }
    });
  }

  openAddModal() {
    if (this.editingWordle) return;

    this.wordlesToAddSelection = [];
    this.isCreatingNewWordle = false;
    this.newWordleValue = '';
    this.loadModalData();
    this.showAddModal = true;
  }

  closeModal() {
    this.showAddModal = false;
    this.wordlesToAddSelection = [];
    this.isCreatingNewWordle = false;
    this.newWordleValue = '';
    this.currentSelectedFolderId = 0;
  }

  toggleWordleToAdd(wordle: string) {
    if (this.isCreatingNewWordle) return;

    const index = this.wordlesToAddSelection.indexOf(wordle);
    if (index > -1) {
      this.wordlesToAddSelection.splice(index, 1);
    } else {
      this.wordlesToAddSelection.push(wordle);
    }
  }

  isWordleToAddSelected(wordle: string) {
    if (this.isCreatingNewWordle) return false;
    return this.wordlesToAddSelection.includes(wordle);
  }

  saveNewWordles() {
    if (this.wordlesToAddSelection.length > 0 || !this.isCreatingNewWordle) {
      this.contestService.addWordlesToContest(this.contestId, this.wordlesToAddSelection).subscribe({
        next: () => {
          this.wordles = [...this.wordles, ...this.wordlesToAddSelection];
          this.closeModal();
          this.selectedWordles = [];
        },
        error: (e) => {
          console.error('Error guardando los wordles', e);
        }
      });
    } else {
      this.closeModal();
    }
  }

  startCreateNewWordle() {
    this.isCreatingNewWordle = true;
    this.newWordleValue = '';
    this.wordlesToAddSelection = [];
  }

  cancelCreateNewWordle() {
    this.isCreatingNewWordle = false;
    this.currentSelectedFolderId = 0;
    this.newWordleValue = '';
  }

  saveCreatedWordle() {

    let newWordle = this.newWordleValue.trim();

    if (newWordle === '') {
      alert('El nombre del wordle no puede estar vacío.');
      return;
    }

    const moreThanOneWord = this.newWordleValue.trim().split(/\s+/).length > 1;

    if (moreThanOneWord) {
      const newWordleNoSpaces = newWordle.replace(/\s+/g, '');
      const confirmSave = confirm(`El Wordle cuenta con más de una palabra, se convertirá de "${newWordle.toUpperCase()}" a "${newWordleNoSpaces.toUpperCase()}"`);
      if (!confirmSave)
        return;
      newWordle = newWordleNoSpaces;
    }


    if (this.wordles.includes(newWordle.toUpperCase()) || this.availableRootWordles.includes(newWordle.toUpperCase())) {
      const confirmSave = confirm(`El wordle "${newWordle}" ya existe, ¿Está seguro de que desea crearlo de nuevo?`);
      if (!confirmSave)
        return;
    }


    this.wordleService.saveWordles([newWordle], 0, this.professorName, Number(this.currentSelectedFolderId)).subscribe({
      next: () => {
        this.isCreatingNewWordle = false;
        this.newWordleValue = '';
        this.loadModalData();
      },
      error: (e) => {
        console.error('Error al crear el nuevo wordle en el backend', e);
      }
    });
  }

  onModalContentClick(event: MouseEvent) {
    event.stopPropagation();
  }

  deleteWordlesInFolder(folders: Folder[]) {
    folders.forEach(folder => {
      folder.wordles = [];

      if (folder.folders.length > 0)
        this.deleteWordlesInFolder(folder.folders);
    })

    this.foldersWithOutWordles = folders;
  }

  onFolderSelected(folderId: number) {
    console.log(folderId);
  }

  handleFolderSelection(folderId: number | string | null): void {
    this.currentSelectedFolderId = folderId;
  }

}

function sortWordlesInFolder(folder: Folder) {
  folder.wordles.sort((a, b) => a.word.localeCompare(b.word));
  folder.folders.forEach(subFolder => sortWordlesInFolder(subFolder));
}

function deepCopyFolders(folders: Folder[]): Folder[] {
  return folders.map(folder => ({
    ...folder,
    wordles: [...folder.wordles],
    folders: deepCopyFolders(folder.folders)
  }));
}