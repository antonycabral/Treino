import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,HttpClientModule],
  providers: [UserService,AuthService],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  usuario: any = {};

  constructor(
    private router: Router,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    if (typeof window !== 'undefined') {
      const userEmail = window.localStorage.getItem('userEmail');
      if (userEmail) {
        this.userService.getUserByEmail(userEmail).subscribe({
          next: (data) => {
            this.usuario = data;
          },
          error: (error) => {
            console.error('Erro ao carregar dados do usuário:', error);
          }
        });
      }
    }
  }

  navegarParaPerfil() {
    this.router.navigate(['/perfil']);
  }
}
