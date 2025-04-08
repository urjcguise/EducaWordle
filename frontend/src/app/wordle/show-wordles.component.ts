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

  isCreatingNewWordle = false;
  newWordleValue = '';

  availableRootWordles: string[] = [];
  folders: Folder[] = [];
  isLoadingModalData: boolean = false;

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
        console.log('Wordles en concurso cargados:', this.wordles);
      },
      error: (e) => {
        console.error('Error obteniendo los wordles', e);
      }
    });
  }

  loadModalData(): void {
    if (this.isLoadingModalData) return;
    this.isLoadingModalData = true;
    console.log('Cargando datos para el modal...');

    forkJoin({
      rootWordlesRaw: this.wordleService.getWordlesByProfessor(this.professorName),
      rootFoldersRaw: this.wordleService.getFoldersByProfessor(this.professorName)
    }).subscribe({
      next: ({ rootWordlesRaw, rootFoldersRaw }) => {
        console.log('Datos brutos recibidos:', rootWordlesRaw, rootFoldersRaw);

        this.availableRootWordles = rootWordlesRaw
          .map(w => w.word)
          .filter(word => !this.wordles.includes(word));
        console.log('Wordles raíz disponibles filtrados:', this.availableRootWordles);

        this.folders = rootFoldersRaw;
        console.log('Carpetas filtradas:', this.folders);

        this.isLoadingModalData = false;
      },
      error: (e) => {
        console.error('Error obteniendo datos para modal (wordles/folders)', e);
        this.isLoadingModalData = false;
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
    console.log('Seleccionados:', this.selectedWordles);
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
      console.log('Iniciando edición para:', this.editingWordle);
    }
  }

  saveEdit() {
    if (!this.editingWordle) return;

    const originalWordle = this.editingWordle;
    const newValue = this.editedValue.trim();

    if (newValue === '' || newValue === originalWordle) {
      this.cancelEdit();
      console.log('Edición cancelada (sin cambios o valor vacío)');
      return;
    }

    if (this.wordles.some(w => w === newValue && w !== originalWordle) || this.availableRootWordles.some(w => w === newValue && w !== originalWordle)) {
      alert(`El wordle "${newValue}" ya existe en la lista.`);
      return;
    }

    console.log(`Intentando guardar: ${originalWordle} -> ${newValue}`);

    this.wordleService.editWordle(originalWordle, newValue).subscribe({
      next: () => {
        console.log('Wordle actualizado en backend con éxito');
        const index = this.wordles.indexOf(originalWordle);
        if (index > -1) {
          this.wordles[index] = newValue;
        }
        this.cancelEdit();
      },
      error: (e) => {
        console.error('Error al actualizar el wordle en el backend', e);
        alert('Error al guardar los cambios. Inténtalo de nuevo.');
      }
    });
  }

  cancelEdit() {
    console.log('Edición cancelada para:', this.editingWordle);
    this.editingWordle = null;
    this.editedValue = '';
  }

  deleteWordle() {
    if (this.editingWordle) return;

    if (this.selectedWordles.length > 0) {
      console.log('Borrar wordles:', this.selectedWordles);
      if (!confirm(`¿Seguro que quieres eliminar ${this.selectedWordles.length} wordle(s)?`)) {
        return;
      }
      this.contestService.deleteWordlesInContest(this.contestId, this.selectedWordles).subscribe({
        next: () => {
          console.log('Wordles eliminados correctamente');
          this.wordles = this.wordles.filter(w => !this.selectedWordles.includes(w));
          this.selectedWordles = [];
        },
        error: (e) => {
          console.error('Error eliminando los wordles', e);
        }
      });
    }
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
  }

  toggleWordleToAdd(wordle: string) {
    if (this.isCreatingNewWordle) return;

    const index = this.wordlesToAddSelection.indexOf(wordle);
    if (index > -1) {
      this.wordlesToAddSelection.splice(index, 1);
    } else {
      this.wordlesToAddSelection.push(wordle);
    }
    console.log('Wordles para añadir:', this.wordlesToAddSelection);
  }

  isWordleToAddSelected(wordle: string) {
    if (this.isCreatingNewWordle) return false;
    return this.wordlesToAddSelection.includes(wordle);
  }

  saveNewWordles() {
    if (this.wordlesToAddSelection.length > 0 || !this.isCreatingNewWordle) {
      console.log('Añadiendo wordles:', this.wordlesToAddSelection);
      this.contestService.addWordlesToContest(this.contestId, this.wordlesToAddSelection).subscribe({
        next: () => {
          console.log('Wordles añadidos correctamente al backend');
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
    console.log('Iniciando modo creación de nuevo wordle.');
    this.isCreatingNewWordle = true;
    this.newWordleValue = '';
    this.wordlesToAddSelection = [];
  }

  cancelCreateNewWordle() {
    console.log('Cancelando modo creación.');
    this.isCreatingNewWordle = false;
    this.newWordleValue = '';
  }

  saveCreatedWordle() {
    const newWordle = this.newWordleValue.trim();

    if (newWordle === '') {
      alert('El nombre del wordle no puede estar vacío.');
      return;
    }

    if (this.wordles.includes(newWordle) || this.availableRootWordles.includes(newWordle)) {
      alert(`El wordle "${newWordle}" ya existe.`);
      return;
    }

    console.log(`Intentando crear nuevo wordle: ${newWordle}`);

    this.wordleService.saveWordles([newWordle], 0, this.professorName, 0).subscribe({
      next: () => {
        console.log('Nuevo wordle creado con éxito en el backend');
        this.availableRootWordles.push(newWordle);
        this.isCreatingNewWordle = false;
        this.newWordleValue = '';
      },
      error: (e) => {
        console.error('Error al crear el nuevo wordle en el backend', e);
        alert(`Error al crear el wordle "${newWordle}". Es posible que ya exista o haya otro problema.`);
      }
    });
  }

  onModalContentClick(event: MouseEvent) {
    event.stopPropagation();
  }
}