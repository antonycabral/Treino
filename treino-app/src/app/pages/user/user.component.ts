import { Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { Treino } from '../../model/Treino';
import { ActivatedRoute} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Exercicio } from '../../model/Exercicio';
import { ExercicioService } from '../../services/exercicio.service';
import { UserService } from '../../services/user.service';
import { TreinoService } from '../../services/treino.service';
import { CommunicationService } from '../../services/communication.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [FormsModule, CommonModule],
  providers: [UserService, TreinoService, ExercicioService],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {

  users: User[] = [];
  selectedUser: User | null = null;
  treinos: Treino[] = [];
  exercicios: Exercicio[] = [];

  constructor(
    private userService: UserService,
    private treinoService: TreinoService,
    private exercicioService: ExercicioService,
    private communicationService: CommunicationService,
    private route: ActivatedRoute // Injete ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Carregar todos os usuários
    this.userService.getUsers().subscribe(
      (data: User[]) => {
        this.users = data;
      },
      error => {
        console.error('Erro ao carregar usuários', error);
      }
    );

    // Capture o ID do usuário da rota
    this.route.paramMap.subscribe(params => {
      const userIdParam = params.get('id');
      const userId = userIdParam ? +userIdParam : null; // Obtenha o ID do usuário
      if (userId) {
        this.loadTreinos(userId);
      }
    });
  }

  loadTreinos(usuarioId: number): void {
    this.selectedUser = this.users.find(user => user.id === usuarioId) || null;
    if (this.selectedUser) {
      this.userService.getTreinosByUserId(usuarioId).subscribe(
        (data: Treino[]) => {
          this.treinos = data;
          this.exercicios = []; // Limpar exercícios ao carregar novos treinos
        },
        error => {
          console.error('Erro ao carregar treinos', error);
        }
      );
    }
  }

  loadExercicios(treinoId: number): void {
    this.exercicioService.getExerciciosPorTreino(treinoId).subscribe(
      (data: Exercicio[]) => {
        this.exercicios = data;
      },
      error => {
        console.error('Erro ao buscar os exercícios', error);
      }
    );
  }
}