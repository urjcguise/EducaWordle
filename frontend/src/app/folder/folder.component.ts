import { Component, Input } from '@angular/core';
import { Folder } from '../models/folder';

@Component({
  selector: 'app-folder',
  templateUrl: './folder.component.html',
  styleUrls: ['./folder.component.css']
})
export class FolderComponent {
  @Input() folder!: Folder;

  isExpanded: boolean = false;

  toggleExpansion() {
    this.isExpanded = !this.isExpanded;
  }
}
