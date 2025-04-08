import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Folder } from '../models/folder';
import { Wordle } from '../models/wordle';

@Component({
  selector: 'app-folder-tree-node',
  templateUrl: './folder-tree-node.component.html',
  styleUrls: ['./folder-tree-node.component.css']
})
export class FolderTreeNodeComponent implements OnInit, OnChanges {

  @Input({ required: true }) folder!: Folder;
  @Input() level: number = 0;

  @Input() contestWordles: string[] = [];

  @Input() selectedForAddition: string[] = [];

  @Output() wordleToggled = new EventEmitter<string>();

  isExpanded: boolean = false;
  displayableWordles: Wordle[] = [];

  ngOnInit(): void {
    this.updateDisplayableWordles();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['contestWordles'] || changes['selectedForAddition']) {
      this.updateDisplayableWordles();
    }
  }

  updateDisplayableWordles(): void {
    if (this.folder?.wordles) {
      this.displayableWordles = this.folder.wordles.filter(w =>
        !this.contestWordles.includes(w.word)
      );
    } else {
      this.displayableWordles = [];
    }
  }

  isWordleSelected(wordleWord: string): boolean {
    return this.selectedForAddition.includes(wordleWord);
  }

  toggleExpansion(event?: MouseEvent): void {
    if (event) event.stopPropagation();
    this.isExpanded = !this.isExpanded;
  }

  toggleWordleSelection(wordleWord: string, event: MouseEvent): void {
    event.stopPropagation();
    if (!this.contestWordles.includes(wordleWord)) {
      this.wordleToggled.emit(wordleWord);
    }
  }

  get subFolders(): Folder[] {
    return this.folder?.folders || [];
  }

  get canExpand(): boolean {
    const hasSubfolders = this.subFolders.length > 0;
    const hasDisplayableWordles = this.displayableWordles.length > 0;
    return hasSubfolders || hasDisplayableWordles;
  }
}
