import { Component, HostListener, OnInit } from '@angular/core';
import { Wordle } from '../models/wordle';
import { WordleService } from '../service/wordle.service';
import { TokenService } from '../service/token.service';
import { Router } from '@angular/router';
import { Folder } from '../models/folder';

@Component({
  selector: 'app-wordle-list',
  templateUrl: './wordle-list.component.html',
  styleUrls: ['./wordle-list.component.css']
})
export class WordleListComponent implements OnInit {

  professorName: string = '';

  wordleList: Wordle[] = [];
  folderList: Folder[] = [];
  selectedFolders: number[] = [];
  selectedWordles: number[] = [];

  canCreateWordle: boolean = true;
  canEditWordle: boolean = false;
  canCreateFolder: boolean = true;
  canEditFolder: boolean = false;
  canMoveWordle: boolean = false;
  canDeleteWordle: boolean = false;

  isCreatingFolder: boolean = false;
  isEditingFolder: boolean[] = [];
  folderName: string = '';
  oldFolderName: string = '';

  folderOptions: Folder[] = [];
  folderSelected: string = '';
  dropdownVisible: boolean = false;

  constructor(private wordleService: WordleService, private tokenService: TokenService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.professorName = this.tokenService.getUserName()!;
    if (this.tokenService.getAuthorities().includes("ROLE_ADMIN"))
      this.professorName = history.state.professorName; //TODO

    this.wordleService.getFoldersByProfessor(this.professorName).subscribe({
      next: (folders) => {
        this.folderList = folders;
        this.folderList.forEach(() => {
          this.isEditingFolder.push(false);
        })
      },
      error: (e) => {
        console.error('Error obteniendo las carpetas', e);
      }
    });
    this.wordleService.getWordlesByProfessor(this.professorName).subscribe({
      next: (wordles) => {
        this.wordleList = wordles;
      },
      error: (e) => {
        console.error('Error obteniendo los wordle', e);
      }
    });
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event): void {
    const targetElement = event.target as HTMLElement;

    if (!targetElement.closest('.wordle-list') && !targetElement.closest('.button-group')) {
      this.deselectAll();
      this.isEditingFolder = this.isEditingFolder.map(() => false);
    }
  }

  deselectAll(): void {
    this.selectedFolders = [];
    this.selectedWordles = [];
    this.updateButtonStates();
  }

  toggleFolderSelection(index: number): void {
    const selectedIndex = this.selectedFolders.indexOf(index);
    if (selectedIndex === -1) {
      this.selectedFolders.push(index);
    } else {
      this.selectedFolders.splice(selectedIndex, 1);
    }
    this.updateButtonStates();
  }

  toggleWordleSelection(index: number): void {
    const selectedIndex = this.selectedWordles.indexOf(index);
    if (selectedIndex === -1) {
      this.selectedWordles.push(index);
    } else {
      this.selectedWordles.splice(selectedIndex, 1);
    }
    this.updateButtonStates();
  }

  updateButtonStates(): void {
    this.canCreateWordle = this.selectedWordles.length == 0 && this.selectedFolders.length == 0;
    this.canEditWordle = this.selectedWordles.length == 1 && this.selectedFolders.length == 0;
    this.canCreateFolder = this.selectedWordles.length == 0 && this.selectedFolders.length == 0;
    this.canEditFolder = this.selectedFolders.length == 1;
    this.canMoveWordle = this.selectedWordles.length > 0 && this.selectedFolders.length == 0;
    this.canDeleteWordle = this.selectedWordles.length > 0 || this.selectedFolders.length > 0;
  }

  createWordle() {
    this.router.navigate([`/${this.professorName}/nuevosWordles`], { state: { folderName: "" } });
  }

  editWordle(): void {
    if (this.selectedWordles.length === 1) {
      const selectedIndex = this.selectedWordles[0];
      const selectedWordle = this.wordleList[selectedIndex];
      this.router.navigate([`/${selectedWordle.word}/editarWordle`]);
    }
  }

  editFolderPushed(i: number) {
    this.oldFolderName = this.folderList[i].name;
    this.isEditingFolder = this.isEditingFolder.map((_, index) => index === i);
  }

  editFolder(i: number) {
    this.wordleService.editFolder(this.oldFolderName, this.folderList[i].name).subscribe({
      next: () => {
        console.log('Carpeta modificada correctamente');
      },
      error: (e) => {
        console.error('Error modificando la carpeta', e);
      }
    });
    this.isEditingFolder[i] = false;
  }

  createFolderPushed() {
    this.isCreatingFolder = true;
  }

  createFolder() {
    if (this.folderName && this.folderName.trim().length > 0) {
      this.wordleService.createFolder(this.folderName, this.professorName).subscribe({
        next: () => {
          console.log('Carpeta creada correctamente');
          this.ngOnInit();
        },
        error: (e) => {
          console.error('Error creando la carpeta', e);
        }
      });
      this.isCreatingFolder = false;
      this.folderName = '';
    }
  }

  enterFolder(i: number) {
    this.router.navigate(['/' + this.folderList[i].name + '/wordles']);
  }

  moveWordle() {
    this.wordleService.getFoldersByProfessor(this.professorName).subscribe({
      next: (folders) => {
        this.folderOptions = folders;
      },
      error: (e) => {
        console.error('Error obteniendo las carpetas', e);
      }
    });
    this.dropdownVisible = true;
  }

  selectFolderToMove(folderName: string) {
    this.folderSelected = folderName;
    const selectedWordleNames = this.selectedWordles.map(
      (index) => this.wordleList[index].word
    );
    this.wordleService.moveToFolder(this.folderSelected, selectedWordleNames).subscribe({
      next: () => {
        console.log('Wordles movidos correctamente');
      },
      error: (e) => {
        console.error('Error moviendo los wordle', e);
      }
    });
    this.dropdownVisible = false;
    this.ngOnInit();
  }

  deleteWordleAndFolder(): void {
    const selectedWordleNames = this.selectedWordles.map(
      (index) => this.wordleList[index].word
    );

    const selectedFolderNames = this.selectedFolders.map(
      (index) => this.folderList[index].name
    );

    if (selectedFolderNames.length > 0) {
      this.wordleService.deleteFolders(selectedFolderNames).subscribe({
        next: () => {
          console.log('Carpetas eliminadas correctamente');
          this.folderList = this.folderList.filter(
            (_, index) => !this.selectedFolders.includes(index)
          );
          this.selectedFolders = [];
          this.updateButtonStates();
        },
        error: (err) => {
          console.error('Error al eliminar las carpetas:', err);
        }
      });
    } else {
      console.log('No hay carpetas seleccionadas para eliminar.');
    }

    if (selectedWordleNames.length > 0) {
      this.wordleService.deleteWordles(selectedWordleNames).subscribe({
        next: () => {
          console.log('Wordles eliminados correctamente');
          this.wordleList = this.wordleList.filter(
            (_, index) => !this.selectedWordles.includes(index)
          );
          this.selectedWordles = [];
          this.updateButtonStates();
        },
        error: (err) => {
          console.error('Error al eliminar los wordles:', err);
        }
      });
    } else {
      console.log('No hay Wordles seleccionados para eliminar.');
    }
  }
}
