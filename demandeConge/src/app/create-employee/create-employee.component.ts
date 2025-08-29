import { Component } from '@angular/core';
import {AuthServiceService} from '../services/auth-service.service';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-create-employee',
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './create-employee.component.html'
})
export class CreateEmployeeComponent {
  name = '';
  prenom = '';
  username = '';
  password = '';
  message = '';

  constructor(protected authService: AuthServiceService) { }

  createEmployee() {
    const role = 'EMPLOYE';
    const employee = { name: this.name, prenom: this.prenom, username: this.username, password: this.password, role };

    this.authService.registerEmployee(employee).subscribe({
      next: res => this.message = res,
      error: err => this.message = err.error
    });
  }
}
