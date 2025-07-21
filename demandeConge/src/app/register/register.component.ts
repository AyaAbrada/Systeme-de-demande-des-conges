import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import {AuthServiceService} from '../services/auth-service.service';
import {CommonModule} from '@angular/common';


@Component({
  selector: 'app-register',
  standalone : true,
  imports: [CommonModule,  ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(
    private fb : FormBuilder,
    private authService: AuthServiceService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      prenom: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      role: ['USER', Validators.required]
    })
  }

  onSubmit():void{
    if(this.registerForm.valid){
      this.authService.register(this.registerForm.value).subscribe({
        next:()=> this.router.navigate(['/login']),
        error: (err) => console.error(err)
      })
    }
  }

}
