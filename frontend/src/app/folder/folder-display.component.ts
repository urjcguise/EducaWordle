import { Component, EventEmitter, HostListener, Input, Output } from '@angular/core';
import { Folder } from '../models/folder';
import { Router } from '@angular/router';
import { WordleService } from '../service/wordle.service';

@Component({
  selector: 'app-folder-display',
  templateUrl: './folder-display.component.html',
  styleUrls: ['./folder-display.component.css']
})
export class FolderDisplayComponent {

  @Input() folder!: Folder;
  @Input() isSelected: boolean = false;
  @Input() professorName!: string;
  @Output() folderSelected = new EventEmitter<any>();

  isExpanded: boolean = false;
  isEditingFolder: boolean = false;

  constructor(private router: Router, private wordleService: WordleService) { }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const clickedInside = (event.target as HTMLElement).closest('.folder-item');

    if (!clickedInside) {
      this.isEditingFolder = false;
    }
  }

  toggleExpansion(event: MouseEvent): void {
    event.stopPropagation();
    this.isExpanded = !this.isExpanded;
  }

  onFolderClick(): void {
    this.folderSelected.emit(this.folder);
  }

  onFolderDoubleClick(): void {
    this.router.navigate(['/' + this.folder.id + '/wordles'], { state: { professorName: this.professorName, folderName: this.folder.name } });
  }

  editFolderPushed() {
    event?.stopPropagation();
    this.isEditingFolder = true;
  }

  editFolder() {
    this.wordleService.editFolder(this.folder.id, this.folder.name).subscribe({
      next: () => {
        console.log('Carpeta modificada correctamente');
      },
      error: (e) => {
        console.error('Error modificando la carpeta', e);
      }
    });
    this.isEditingFolder = false;
  }
}
