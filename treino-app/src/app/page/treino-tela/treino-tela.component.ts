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
  treino: any = {};
  currentExerciseIndex = 0;
  currentExercise: any;
  elapsedTime = 0;
  timerInterval: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private treinoService: TreinoService
  ) {}

  ngOnInit() {
    const treinoId = this.route.snapshot.params['id'];
    this.treinoService.getTreino(treinoId).subscribe(treino => {
      this.treino = treino;
      this.currentExercise = this.treino.exercicios[0];
      this.startTimer();
    });
  }

  startTimer() {
    this.timerInterval = setInterval(() => {
      this.elapsedTime++;
    }, 1000);
  }

  ngOnDestroy() {
    this.stopTimer();
  }

  nextExercise() {
    if (this.currentExerciseIndex < this.treino.exercicios.length - 1) {
      this.currentExerciseIndex++;
      this.currentExercise = this.treino.exercicios[this.currentExerciseIndex];
    }
  }

  stopTimer() {
    if (this.timerInterval) {
      clearInterval(this.timerInterval);
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

  finishTraining() {
    this.stopTimer();
    this.router.navigate(['/home']);
  }
}
