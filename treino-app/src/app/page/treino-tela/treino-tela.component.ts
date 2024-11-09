import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TreinoService } from '../../service/treino.service';
import { FormsModule } from '@angular/forms';

export interface SeriesFeedback {
  repeticoesExecutadas: number;
  cargaFeedback: 'leve' | 'adequada' | 'pesada';
}

@Component({
  selector: 'app-treino-tela',
  standalone: true,
  imports: [CommonModule, FormsModule],
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
  showFeedbackForm: boolean = false;
  seriesFeedback: SeriesFeedback = {
    repeticoesExecutadas: 0,
    cargaFeedback: 'adequada'
  };
  ajusteSugerido: string = '';
  tempoDescansoExtra: number = 0;

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
    this.showFeedbackForm = true;
    
    // começa a contar imediatamente
    this.restInterval = setInterval(() => {
        this.restTimeLeft--;
        if (this.restTimeLeft <= 0) {
            this.finishRest();
        }
    }, 1000);
}

processarFeedback() {
  const { repeticoesExecutadas, cargaFeedback } = this.seriesFeedback;
  const repeticoesAlvo = this.currentExercise.repeticoes;
  const cargaAtual = this.currentExercise.carga;

  // Situação 1: Carga leve e repetições acima do alvo
  if (cargaFeedback === 'leve' && repeticoesExecutadas > repeticoesAlvo) {
      this.ajusteSugerido = `Sugestão: Aumentar carga em 2kg (${cargaAtual + 2}kg) para intensificar o treino.`;
  } 
  // Situação 2: Carga adequada com esforço adequado
  else if (cargaFeedback === 'adequada' && repeticoesExecutadas >= 8 && repeticoesExecutadas <= 12) {
      this.ajusteSugerido = 'Sugestão: Manter a carga e o tempo de descanso para continuar progredindo.';
  }
  // Situação 3: Carga adequada, mas as últimas repetições foram fáceis
  else if (cargaFeedback === 'adequada' && repeticoesExecutadas === repeticoesAlvo && repeticoesExecutadas <= 15) {
      this.ajusteSugerido = `Sugestão: Aumentar carga em 1kg (${cargaAtual + 1}kg) para desafiar o músculo.`;
  }
  // Situação 4: Carga leve e repetições entre 8 e 12
  else if (cargaFeedback === 'leve' && repeticoesExecutadas >= 8 && repeticoesExecutadas <= 12) {
      this.ajusteSugerido = `Sugestão: Aumentar carga em 2kg (${cargaAtual + 2}kg) para manter o treino desafiador.`;
  }
  // Situação 5: Carga pesada e repetições abaixo de 8
  else if (cargaFeedback === 'pesada' && repeticoesExecutadas < 8) {
      this.ajusteSugerido = `Sugestão: Reduzir carga em 2kg (${cargaAtual - 2}kg) para evitar sobrecarga e melhorar execução.`;
  }
  // Situação 6: Repetições abaixo do alvo e carga adequada
  else if (repeticoesExecutadas === 8 && cargaFeedback === 'adequada') {
      this.ajusteSugerido = 'Sugestão: Manter carga e aumentar o tempo de descanso em 30 segundos.';
      this.restTimeLeft += 30;
  }
  // Situação 7: Repetições muito altas e carga leve
  else if (repeticoesExecutadas > 20 && cargaFeedback === 'leve') {
      this.ajusteSugerido = `Sugestão: Aumentar carga em 3kg (${cargaAtual + 3}kg) e reduzir as repetições para até 15.`;
  }
  // Situação 8: Carga muito pesada com repetições muito baixas
  else if (cargaFeedback === 'pesada' && repeticoesExecutadas <= 3) {
      this.ajusteSugerido = `Sugestão: Reduzir carga em 3kg (${cargaAtual - 3}kg) para garantir a execução com segurança.`;
  }
  // Situação padrão
  else {
      this.ajusteSugerido = 'Nenhum ajuste necessário. Continue com a mesma carga e repetições.';
  }

  this.showFeedbackForm = false;
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
    this.router.navigate(['/home']);
  }
}
