import { Component, HostListener, OnInit } from '@angular/core';
import { Wordle } from '../models/wordle';
import { ActivatedRoute, Router } from '@angular/router';
import { Folder } from '../models/folder';
import { WordleService } from '../service/wordle.service';

@Component({
  selector: 'app-folder-list',
  templateUrl: './folder-list.component.html',
  styleUrls: ['./folder-list.component.css']
})
export class FolderListComponent implements OnInit {

  folderParentName: string = '';
  professorName: string = '';

  parentFolder!: Folder;
  parentsFoldersList: string[] = [];

  wordleList: Wordle[] = [];
  folderList: Folder[] = [];
  selectedFolders: number[] = [];
  selectedWordles: number[] = [];

  isExpanded: boolean[] = [];

  canCreateWordle: boolean = true;
  canEditWordle: boolean = false;
  canCreateFolder: boolean = true;
  canEditFolder: boolean = false;
  canMoveWordle: boolean = false;
  canDeleteWordle: boolean = false;

  isCreatingFolder: boolean = false;
  isEditingFolder: boolean[] = [];
  newFolderName: string = '';
  oldFolderName: string = '';

  folderOptions: string[] = [];
  folderSelected: string = '';
  dropdownVisible: boolean = false;

  constructor(private route: ActivatedRoute, private wordleService: WordleService, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((paramMap) => {
      this.folderParentName = paramMap.get('folderName') || '';
      this.professorName = history.state.professorName;
      this.wordleService.getFolder(this.folderParentName).subscribe({
        next: (folder) => {
          this.parentFolder = folder;
          this.parentsFoldersList.push(folder.name);
          while (folder.parentFolder != null) {
            folder = folder.parentFolder;
            this.parentsFoldersList.push(folder.name);
          }
          this.parentsFoldersList.push('...');
          this.parentsFoldersList.reverse();
        },
        error: (e) => {
          console.error('Error obteniendo la carpeta', e);
        }
      });
      this.wordleService.getFoldersByFolderName(this.folderParentName).subscribe({
        next: (folders) => {
          this.folderList = folders;
          this.folderList.forEach(() => {
            this.isEditingFolder.push(false);
            this.isExpanded.push(false);
          })
        },
        error: (e) => {
          console.error('Error obteniendo las carpetas', e);
        }
      });

      this.wordleService.getWordlesByFolderName(this.folderParentName).subscribe({
        next: (wordles) => {
          this.wordleList = wordles;
        },
        error: (e) => {
          console.error('Error obteniendo los wordle', e);
        }
      });
    });
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event): void {
    const targetElement = event.target as HTMLElement;

    if (!targetElement.closest('.wordle-list') && !targetElement.closest('.button-group')) {
      this.deselectAll();
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

  createWordle() {
    this.router.navigate([`/${this.professorName}/nuevosWordles`], { state: { folderName: this.folderParentName } });
  }

  editWordle(): void {
    if (this.selectedWordles.length === 1) {
      const selectedIndex = this.selectedWordles[0];
      const selectedWordle = this.wordleList[selectedIndex];
      this.router.navigate([`/${selectedWordle.word}/editarWordle`], { state: { professorName: this.professorName } });
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
    if (this.newFolderName && this.newFolderName.trim().length > 0) {
      this.wordleService.createFolderInsideFolder(this.newFolderName, this.professorName, this.folderParentName).subscribe({
        next: () => {
          console.log('Carpeta creada correctamente');
          this.parentsFoldersList = [];
          this.ngOnInit();
        },
        error: (e) => {
          console.error('Error creando la carpeta', e);
        }
      });
      this.isCreatingFolder = false;
      this.newFolderName = '';
    }
  }

  enterFolder(i: number) {
    this.parentsFoldersList = [];
    this.router.navigate(['/' + this.folderList[i].name + '/wordles'], { state: { professorName: this.professorName } });
  }

  navigateToFolder(folderName: string) {
    if (folderName != '...') {
      this.parentsFoldersList = [];
      this.router.navigate(['/' + folderName + '/wordles'], { state: { professorName: this.professorName } });
    } else {
      this.router.navigate(['/wordles'], { state: { professorName: this.professorName } });
    }
  }

  moveWordle() {
    if (this.dropdownVisible) {
      this.dropdownVisible = false;
      return;
    }
    this.folderOptions = [];
    this.wordleService.getFoldersByFolderName(this.folderParentName).subscribe({
      next: (folders) => {
        folders.forEach((folder) => {
          this.folderOptions.push(folder.name);
        })
        this.folderOptions = [
          ...this.parentsFoldersList.filter(folder => folder !== this.folderParentName),
          ...this.folderOptions
        ];
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
    this.parentsFoldersList = [];
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
