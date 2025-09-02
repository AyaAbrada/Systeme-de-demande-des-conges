import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from '../services/auth-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginResponse } from '../model/user.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = { username: '', password: '' };
  message: string = '';

  constructor(private authService: AuthServiceService, private router: Router) {}

  login() {
    this.authService.clearAuth();

    this.authService.login(this.user).subscribe({
      next: (res: LoginResponse) => {
        this.authService.saveToken(res.token);
        this.authService.saveRole(res.role);
        if (res.employeId) localStorage.setItem('employeId', res.employeId.toString());

        if (res.role === 'MANAGER') this.router.navigate(['/dashboard-manager']);
        else if (res.role === 'EMPLOYE') this.router.navigate(['/dashboard-employe']);
        else this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Login error:', err);
        if (err.status === 401) this.message = 'Identifiants invalides ou token expiré.';
        else this.message = 'Erreur serveur, réessayez plus tard.';
      }
    });
  }
}
