import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthServiceService } from '../services/auth-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterLink,
    FormsModule,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    username: '',
    password: '',
    role: ''
  };

  message: string = '';

  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) {}

  login() {
    this.authService.login(this.user).subscribe({
      next: (res: any) => {
        console.log('Réponse de login:', res);

        // Sauvegarde du token
        this.authService.saveToken(res.token);

        // Sauvegarde de l'id employé (si backend le renvoie)
        if (res.employeId || res.id) {
          localStorage.setItem('employeId', (res.employeId || res.id).toString());
        }


        const role = res.role || this.user.role;

        switch (role) {
          case 'MANAGER':
            this.router.navigate(['/dashboard-manager']);
            break;
          case 'EMPLOYE':
            console.log('Réponse de login:', res);
            this.router.navigate(['/dashboard-employe']);
            break;
          default:
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
