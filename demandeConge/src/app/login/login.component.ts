import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {AuthServiceService} from '../services/auth-service.service';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [
    RouterLink,
    FormsModule,
    CommonModule
],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user = {
    username : '',
    password : '',
    role : 'MANAGER | EMPLOYE'
  };

  message : string = "";

  constructor(private authService : AuthServiceService ,
              private router: Router) {
  }

  login(){
    this.authService.login(this.user).subscribe({
      next: (res : any )=>{
        console.log('Réponse de login:' , res);

        this.authService.saveToken(res.token);

        const role = (res.role || '').toUpperCase().trim();

        switch (role){
          case 'MANAGER':
            this.router.navigate(['/dashboard-manager']);
            break;
          case 'EMPLOYE':
            this.router.navigate(['/employe-manager']);
            break;
          default:
            this.router.navigate(['/dashboard'])
        }
      },
      error: () =>{
        this.message = 'Identifiants invalides.';
      }
    })
  }

}
