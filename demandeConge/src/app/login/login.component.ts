import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthServiceService } from '../services/auth-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginResponse } from '../model/user.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    username: '',
    password: ''
  };

  message: string = '';

  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) {}

  login() {
    this.authService.login(this.user).subscribe({
      next: (res: LoginResponse) => {
        console.log('Réponse de login:', res);

        // Sauvegarde token et rôle
        this.authService.saveToken(res.token);
        this.authService.saveRole(res.role);

        // Sauvegarde employeId si dispo
        if (res.employeId) {
          localStorage.setItem('employeId', res.employeId.toString());
        }

        // Redirection selon rôle
        if (res.role === 'MANAGER') {
          this.router.navigate(['/dashboard-manager']);
        } else if (res.role === 'EMPLOYE') {
          this.router.navigate(['/dashboard-employe']);
        } else {
          this.router.navigate(['/dashboard']);
        }
      },
      error: (err) => {
        console.error('Erreur login:', err);
        this.message = 'Identifiants invalides.';
      }
    });
  }
}
