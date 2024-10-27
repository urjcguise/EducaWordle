import { Component } from '@angular/core';

@Component({
  selector: 'app-edit-wordle',
  templateUrl: './edit-wordle.component.html',
  styleUrls: ['./edit-wordle.component.css']
})
export class EditWordleComponent {
  wordles: string[] = [''];
  dictionary: boolean = false;
  file: boolean = false;

  addWordle() {
    if (this.wordles.length < 3) {
      this.wordles.push('');
    }
  }

  useDictionary(value: boolean) {
    this.dictionary = value;
    if (!value) {
      this.file = false; 
    }
  }

  useFile(value: boolean) {
    this.file = value;
  }

  selectFile(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      console.log(`Archivo seleccionado: ${file.name}`);
      // Aquí puedes añadir la lógica para manejar el archivo .env
    }
  }
}
