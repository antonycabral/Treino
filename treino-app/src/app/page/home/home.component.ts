import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { TreinoService } from '../../service/treino.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,HttpClientModule],
  providers: [UserService,AuthService,TreinoService],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  usuario: any = {};

  constructor(
    private router: Router,
    private userService: UserService,
    private authService: AuthService,
    private treinoService: TreinoService
  ) {}

  ngOnInit() {
    if (typeof window !== 'undefined') {
      const userEmail = window.localStorage.getItem('userEmail');
      if (userEmail) {
        this.userService.getUserByEmail(userEmail).subscribe({
          next: (data) => {
            this.usuario = data;
            this.carregarTreinos(); // Now we call carregarTreinos after user data is loaded
          },
          error: (error) => {
            console.error('Erro ao carregar dados do usuário:', error);
          }
        });
      }
    }
  }

  calcularIMC(): number {
    const altura = this.usuario.altura / 100; // convertendo cm para metros
    return this.usuario.peso / (altura * altura);
}

getClassificacaoIMC(): string {
    const imc = this.calcularIMC();
    
    if (imc < 18.5) return 'Abaixo do peso';
    if (imc < 24.9) return 'Peso normal';
    if (imc < 29.9) return 'Sobrepeso';
    if (imc < 34.9) return 'Obesidade Grau I';
    if (imc < 39.9) return 'Obesidade Grau II';
    return 'Obesidade Grau III';
}

carregarTreinos() {
  if (this.usuario && this.usuario.id) {
    this.treinoService.listarTreinosPorUsuario(this.usuario.id)
      .subscribe(treinos => {
        this.usuario.treinos = treinos;
      });
  }
}

removerTreino(id: string) {
  this.treinoService.deletarTreino(id)
    .subscribe(() => {
      this.carregarTreinos();
    });
}

adicionarTreino() {
  this.router.navigate(['treino/novo']);
}

editarTreino(treino: any) {
  this.router.navigate(['treino/editar', treino.id]);
}

  navegarParaPerfil() {
    this.router.navigate(['/perfil']);
  }
}
