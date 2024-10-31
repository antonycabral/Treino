import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UsuarioService } from '../../services/usuario.service';
import { Desempenho } from '../../model/desempenho';
import { Treino } from '../../model/Treino';
import { Exercicio } from '../../model/Exercicio';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  providers:[AuthService, UsuarioService],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  usuario: any = {};
  temTreino = false;
  treino: Treino = {
    id: 1,
    nome: 'Treino A - Superior',
    diaSemana: 'Segunda-feira',
    objetivo: 'Hipertrofia',
    exercicios: [
      {
        id: 1,
        nome: 'Supino Reto',
        series: 4,
        repeticoes: 12,
        peso: 20,
        concluido: false
      },
      {
        id: 2,
        nome: 'Puxada Frontal',
        series: 4,
        repeticoes: 12,
        peso: 40,
        concluido: false
      }
    ],
    dataCriacao: new Date()
  };

  desempenho: Desempenho = {
    treinosRealizados: 12,
    frequenciaSemanal: 4,
    ultimasAtividades: [
      {
        id: 1,
        data: new Date(),
        descricao: 'Treino A - Superior',
        tipo: 'treino'
      },
      {
        id: 2,
        data: new Date(Date.now() - 86400000),
        descricao: 'Avaliação Física Mensal',
        tipo: 'avaliacao'
      }
    ],
    metasSemana: {
      previsto: 5,
      realizado: 4
    }
  };

  constructor(
    private authService: AuthService,
    private userService: UsuarioService
  ) {}

  ngOnInit() {
    this.carregarDadosUsuario();
  }

  carregarDadosUsuario() {
    const userEmail = this.authService.getCurrentUserEmail();
    if (userEmail) {
      this.userService.getUserByEmail(userEmail).subscribe(
        data => {
          this.usuario = data;
        },
        error => {
          alert('Erro ao carregar dados do usuário');
        }
      );
    }
  }

  concluirExercicio(exercicio: Exercicio): void {
    exercicio.concluido = !exercicio.concluido;
  }
}