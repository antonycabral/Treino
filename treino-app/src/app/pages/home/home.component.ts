import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  providers:[AuthService, UsuarioService],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  usuario: any = {};
  temTreino = false;

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
}