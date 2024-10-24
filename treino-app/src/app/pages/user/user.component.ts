import { Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { Treino } from '../../model/Treino';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {

  user: User | null = null;  // Armazenar o usuário escolhido
  treinoUser: Treino[] = [];  // Armazenar os treinos do usuário

  users: User[] = [
    { id: 1, nomeCompleto: 'Usuário A', sexo: 'Masculino', idade: 25, peso: 70 },
    { id: 2, nomeCompleto: 'Usuário B', sexo: 'Feminino', idade: 30, peso: 60 },
    { id: 3, nomeCompleto: 'Usuário C', sexo: 'Masculino', idade: 22, peso: 80 }
  ];

  treinoUserData: { [key: number]: Treino[] } = {
    1: [
      { nome: 'Treino A', exercicios: [{ nome: 'Agachamento', carga: 60, series: 4, tempo: 30, descanso: 60 }] },
    ],
    2: [
      { nome: 'Treino B', exercicios: [{ nome: 'Corrida', carga: 0, series: 1, tempo: 1800, descanso: 0 }] },
    ],
    3: [
      { nome: 'Treino C', exercicios: [{ nome: 'Puxada', carga: 40, series: 4, tempo: 30, descanso: 60 }] },
    ],
  };

  constructor(private route: ActivatedRoute, private router: Router) {} // Injetar Router

  ngOnInit(): void {
    const userId = Number(this.route.snapshot.paramMap.get('id')); // Obter ID do usuário da rota
    this.user = this.users.find(user => user.id === userId) || null; // Definir o usuário selecionado

    if (!this.user) {
      this.router.navigate(['/']); // Redirecionar se o usuário não for encontrado
      return;
    }

    this.treinoUser = this.treinoUserData[userId] || []; // Obter os treinos do usuário
  }
}
