import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ExercicioService } from '../../service/exercicio.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-exercicio-form',
  standalone: true,
  imports: [CommonModule,RouterModule, FormsModule],
  templateUrl: './exercicio-form.component.html',
  styleUrl: './exercicio-form.component.css'
})
export class ExercicioFormComponent implements OnInit {
  treinoId: string = '';
  exercicio = {
    nome: '',
    descricao: '',
    series: 0,
    repeticoes: 0,
    carga: 0
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private exercicioService: ExercicioService
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.treinoId = params['treinoId'];
    });
  }

  salvarExercicio() {
    this.exercicioService.criarExercicio(this.treinoId, this.exercicio)
      .subscribe({
        next: () => {
          this.router.navigate(['/treino', this.treinoId]);
        },
        error: (error) => {
          console.error('Erro ao criar exercício:', error);
        }
      });
  }

  voltar() {
    this.router.navigate(['/treino', this.treinoId]);
  }
}