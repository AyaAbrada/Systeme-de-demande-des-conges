import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthServiceService} from '../services/auth-service.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-register-manager',
  templateUrl: './register-manager.component.html',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  styleUrls: ['./register-manager.component.css']
})
export class RegisterManagerComponent {

  registerForm: FormGroup;
  message: string = '';
  error: string = '';

  constructor(private fb: FormBuilder, private authService: AuthServiceService) {
    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      prenom: ['', Validators.required],
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.registerForm.invalid) return;

    const formValue = this.registerForm.value;
    const registerData = {
      name: formValue.name,
      prenom: formValue.prenom,
      username: formValue.username,
      password: formValue.password,
      role: 'MANAGER' // on force le rôle MANAGER
    };

    this.authService.register(registerData).subscribe({
      next: (res: any) => {
        this.message = res.message || 'Manager créé avec succès';
        this.error = '';
        this.registerForm.reset();
      },
      error: (err) => {
        this.error = err.error?.error || 'Erreur lors de l’enregistrement';
        this.message = '';
      }
    });
  }
}
