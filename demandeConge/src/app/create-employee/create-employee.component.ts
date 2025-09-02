import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AuthServiceService } from '../services/auth-service.service';

@Component({
  selector: 'app-create-employee',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'

})
export class CreateEmployeeComponent {
  name = '';
  prenom = '';
  username = '';
  password = '';
  message = '';

  constructor(public authService: AuthServiceService) {}

  createEmployee() {
    const role = 'EMPLOYE';
    const employee = {
      name: this.name,
      prenom: this.prenom,
      username: this.username,
      password: this.password,
      role
    };

    this.authService.registerEmployee(employee).subscribe({
      next: res => this.message = res.message,
      error: err => this.message = err.error
    });
  }
}
