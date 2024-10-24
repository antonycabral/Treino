import { Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Treino } from '../../model/Treino';
import { Router } from '@angular/router';



@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HttpClientModule, CommonModule,FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  users: User[] = [];

  constructor(private router: Router) {} // Injetar Router

  ngOnInit(): void {
    // Simulação de usuários
    this.users = [
      { id: 1, nomeCompleto: 'Usuário A', sexo: 'Masculino', idade: 25, peso: 70 },
      { id: 2, nomeCompleto: 'Usuário B', sexo: 'Feminino', idade: 30, peso: 60 },
      { id: 3, nomeCompleto: 'Usuário C', sexo: 'Masculino', idade: 22, peso: 80 }
    ];
  }

  // Método para redirecionar para o UserComponent
  selectUser(userId: number): void {
    this.router.navigate(['/user', userId]); // Navega para a rota do usuário
  }
}
