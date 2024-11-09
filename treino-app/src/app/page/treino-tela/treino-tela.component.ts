import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TreinoService } from '../../service/treino.service';

@Component({
  selector: 'app-treino-tela',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './treino-tela.component.html',
  styleUrl: './treino-tela.component.css'
})
export class TreinoTelaComponent implements OnInit, OnDestroy {
  treino: any = {
    nome: '',
    exercicios: []
  };
  currentExercise: any = null;
  remainingExercises: any[] = [];
  completedExercises: any[] = [];
  elapsedTime = 0;
  timerInterval: any;
  currentSeries: number = 1;
  isResting: boolean = false;
  restTimeLeft: number = 60;
  restInterval: any;
  seriesCompleted: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private treinoService: TreinoService
  ) {}

  ngOnInit() {
    const treinoId = this.route.snapshot.params['id'];
    this.treinoService.getTreino(treinoId).subscribe({
      next: (treino) => {
        if (treino) {
          this.treino = treino;
          this.remainingExercises = [...(treino.exercicios || [])];
        } else {
          console.error('Treino não encontrado');
          this.router.navigate(['/home']);
        }
      },
      error: (error) => {
        console.error('Erro ao carregar treino:', error);
        this.router.navigate(['/home']);
      }
    });
  }


  ngOnDestroy() {
    this.stopTimer();
    this.stopRestTimer();
  }

  startTimer() {
    if (!this.timerInterval) {
      this.timerInterval = setInterval(() => {
        this.elapsedTime++;
      }, 1000);
    }
  }

  stopTimer() {
    if (this.timerInterval) {
      clearInterval(this.timerInterval);
      this.timerInterval = null;
    }
  }

  stopRestTimer() {
    if (this.restInterval) {
      clearInterval(this.restInterval);
      this.restInterval = null;
    }
  }

  formatTime(seconds: number): string {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const remainingSeconds = seconds % 60;
    return `${this.padNumber(hours)}:${this.padNumber(minutes)}:${this.padNumber(remainingSeconds)}`;
  }

  padNumber(num: number): string {
    return num.toString().padStart(2, '0');
  }

  selectExercise(exercise: any) {
    if (this.currentExercise) {
      this.completedExercises.push(this.currentExercise);
    } else {
      this.startTimer();
    }
    
    this.currentExercise = exercise;
    this.remainingExercises = this.remainingExercises.filter(ex => ex !== exercise);
    this.seriesCompleted = false;
    this.currentSeries = 1;
    this.isResting = false;
    this.stopRestTimer();
  }

  startRestTimer() {
    this.isResting = true;
    this.restTimeLeft = 60;
    this.restInterval = setInterval(() => {
      this.restTimeLeft--;
      if (this.restTimeLeft <= 0) {
        this.finishRest();
      }
    }, 1000);
  }

  finishRest() {
    this.stopRestTimer();
    this.isResting = false;
    if (this.currentSeries < this.currentExercise.series) {
      this.currentSeries++;
    }
  }

  completeSeries() {
    if (this.currentSeries < this.currentExercise.series) {
      this.startRestTimer();
    } else {
      this.seriesCompleted = true;
      this.currentSeries = 1;
      if (this.remainingExercises.length === 0) {
        this.finishTraining();
      }
    }
  }

  finishTraining() {
    this.stopTimer();
    this.stopRestTimer();
    // You can add logic here to save training data if needed
    this.router.navigate(['/home']);
  }
}
