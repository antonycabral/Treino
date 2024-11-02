import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  providers: [AuthService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  credentials = {
    email: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.credentials).subscribe(response => {
      alert('Login bem-sucedido!');
      this.router.navigate(['home']);
    }, error => {
      alert('Erro ao fazer login: ' + error.message);
    });
  }

  navigateToCadastro() {
    this.router.navigate(['/cadastro']); // Redireciona para a página de cadastro
  }
}
