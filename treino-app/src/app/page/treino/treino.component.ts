import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TreinoService } from '../../service/treino.service';
import { ExercicioService } from '../../service/exercicio.service';

@Component({
  selector: 'app-treino',
  standalone: true,
  imports: [],
  templateUrl: './treino.component.html',
  styleUrl: './treino.component.css'
})
export class TreinoComponent implements OnInit {
  treino: any = {
    exercicios: []
  };

  constructor(
    private route: ActivatedRoute,
    private treinoService: TreinoService,
    private exercicioService: ExercicioService,
    private router: Router
  ) {}

  ngOnInit() {
    const treinoId = this.route.snapshot.params['id'];
    if (treinoId) {
      this.carregarTreino(treinoId);
    }
  }

  carregarTreino(id: string) {
    this.treinoService.getTreino(id).subscribe(treino => {
      this.treino = treino;
    });
  }

  adicionarExercicio() {
    // Will implement dialog/form for adding exercise
  }

  removerExercicio(exercicioId: string) {
    this.exercicioService.deletarExercicio(exercicioId).subscribe(() => {
      this.carregarTreino(this.treino.id);
    });
  }
}
