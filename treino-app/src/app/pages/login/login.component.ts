import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
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
    this.authService.login(this.credentials).subscribe(
      response => {
        this.authService.setCurrentUserEmail(this.credentials.email);
        this.router.navigate(['/home']);
      },
      error => {
        if (error.error && error.error.message) {
          alert(error.error.message);
        } else {
          alert('Erro ao fazer login. Tente novamente.');
        }
      }
    )
  }

  navigateToCadastro() {
    this.router.navigate(['cadastro']); // Redireciona para a página de cadastro
  }
}
