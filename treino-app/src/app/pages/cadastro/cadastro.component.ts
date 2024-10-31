import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule],
  providers: [AuthService],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {
  usuario = {
    nome: '',
    email: '',
    password: '',
    peso: null,
    altura: null
  };

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.cadastrar(this.usuario).subscribe(response => {
      alert('Usuário cadastrado com sucesso!');
      this.router.navigate(['/login']);
    }, error => {
      alert('Erro ao cadastrar usuário: ' + error.message);
    });
  }
}