import { Component, HostListener, OnInit } from '@angular/core';
import { Wordle } from '../models/wordle';
import { WordleService } from '../service/wordle.service';
import { TokenService } from '../service/token.service';
import { NavigationStart, Router } from '@angular/router';
import { Folder } from '../models/folder';

@Component({
  selector: 'app-wordle-list',
  templateUrl: './wordle-list.component.html',
  styleUrls: ['./wordle-list.component.css']
})
export class WordleListComponent implements OnInit {

  professorName: string = '';
  isAdmin: boolean = false;

  wordleList: Wordle[] = [];
  folderList: Folder[] = [];

  isExpanded: boolean[] = [];

  selectedFolders: number[] = [];
  selectedWordles: number[] = [];

  canCreateWordle: boolean = true;
  canEditWordle: boolean = false;
  canCreateFolder: boolean = true;
  canEditFolder: boolean = false;
  canMoveWordle: boolean = false;
  canDeleteWordle: boolean = false;

  isCreatingFolder: boolean = false;
  folderName: string = '';

  folderOptions: Folder[] = [];
  dropdownVisible: boolean = false;

  modalVisible: boolean = false;

  constructor(private wordleService: WordleService, private tokenService: TokenService, private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.navigationTrigger == 'popstate') {
          this.goBack();
        }
      }
    });
  }

  ngOnInit() {
    if (this.tokenService.getAuthorities().includes("ROLE_PROFESSOR"))
      this.professorName = this.tokenService.getUserName()!;
    if (this.tokenService.getAuthorities().includes("ROLE_ADMIN")) {
      this.professorName = history.state.professorName;
      this.isAdmin = true;
    }

    this.wordleService.getFoldersByProfessor(this.professorName).subscribe({
      next: (folders) => {
        this.folderList = folders;
        this.folderList.forEach(() => {
          this.isExpanded.push(false);
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
  onDocumentClick(event: Event) {
    const targetElement = event.target as HTMLElement;

    if (!targetElement.closest('.wordle-list') && !targetElement.closest('.button-group')) {
      this.deselectAll();
    }
  }

  deselectAll() {
    this.isCreatingFolder = false;
    this.selectedFolders = [];
    this.selectedWordles = [];
    this.updateButtonStates();
  }

  toggleFolderSelection(index: number) {
    const selectedIndex = this.selectedFolders.indexOf(index);
    if (selectedIndex === -1) {
      this.selectedFolders.push(index);
    } else {
      this.selectedFolders.splice(selectedIndex, 1);
    }
    this.updateButtonStates();
  }

  toggleWordleSelection(index: number) {
    const selectedIndex = this.selectedWordles.indexOf(index);
    if (selectedIndex === -1) {
      this.selectedWordles.push(index);
    } else {
      this.selectedWordles.splice(selectedIndex, 1);
    }
    this.updateButtonStates();
  }

  toggleFolderExpansion(index: number) {
    this.isExpanded[index] = !this.isExpanded[index];
  }

  updateButtonStates(): void {
    this.canCreateWordle = this.selectedWordles.length == 0 && this.selectedFolders.length == 0;
    this.canEditWordle = this.selectedWordles.length == 1 && this.selectedFolders.length == 0;
    this.canCreateFolder = this.selectedWordles.length == 0 && this.selectedFolders.length == 0;
    this.canEditFolder = this.selectedFolders.length == 1;
    this.canMoveWordle = this.selectedWordles.length > 0 && this.selectedFolders.length == 0 && this.folderList.length > 0;
    this.canDeleteWordle = this.selectedWordles.length > 0 || this.selectedFolders.length > 0;
  }

  editWordle() {
    if (this.selectedWordles.length === 1) {
      const selectedIndex = this.selectedWordles[0];
      const selectedWordle = this.wordleList[selectedIndex];
      this.router.navigate([`/${selectedWordle.word}/editarWordle`], { state: { professorName: this.professorName } });
    }
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

  moveWordle() {
    if (this.dropdownVisible) {
      this.dropdownVisible = false;
      return;
    }
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

  selectFolderToMove(folder: Folder) {
    const folderId = folder.name === '...' ? 0 : folder.id;
    const selectedWordleNames = this.selectedWordles.map(
      (index) => this.wordleList[index].word
    );
    this.wordleService.moveToFolder(folderId, selectedWordleNames).subscribe({
      next: () => {
        console.log('Wordles movidos correctamente');
        this.ngOnInit();
      },
      error: (e) => {
        console.error('Error moviendo los wordle', e);
      }
    });
    this.dropdownVisible = false;
    this.ngOnInit();
  }

  deleteWordleAndFolder() {
    const selectedWordle = this.selectedWordles.map(
      (index) => this.wordleList[index]
    );

    const selectedFolderIds = this.selectedFolders.map(
      (index) => this.folderList[index].id
    );

    if (selectedFolderIds.length > 0) {
      this.wordleService.deleteFolders(selectedFolderIds).subscribe({
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

    if (selectedWordle.length > 0) {
      this.wordleService.deleteWordles(selectedWordle).subscribe({
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

    this.router.navigate(['/wordles'], { state: { professorName: this.professorName } });
  }

  createWordle() {
    this.modalVisible = true;
  }

  closeModal() {
    this.modalVisible = false;
    this.ngOnInit();
  }

  goBack() {
    if (this.isAdmin)
      this.router.navigate(['/usuarios']);
    else
      this.router.navigate(['/competiciones']);
  }
}
