import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-demande-form',
  templateUrl: './demande-form.component.html',
  imports: [ReactiveFormsModule, NgIf],
  styleUrls: ['./demande-form.component.css']
})
export class DemandeFormComponent implements OnInit {
  demandeForm!: FormGroup;
  employeId!: number;

  successMessage: string = '';
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    const storedId = localStorage.getItem('employeId');
    if (!storedId) {
      this.errorMessage = "Employé non connecté !";
      return;
    }
    this.employeId = Number(storedId);

    this.demandeForm = this.fb.group({
      employeId: [this.employeId, Validators.required],
      managerId: [1, Validators.required],
      typeConge: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.demandeForm.valid) {
      const demande = this.demandeForm.value;
      this.http.post("http://localhost:8083/api/demandes", demande).subscribe({
        next: () => {
          this.successMessage = " Demande envoyée avec succès !";
          this.errorMessage = '';
          this.demandeForm.reset({
            employeId: this.employeId,
            managerId: 1
          });
        },
        error: () => {
          this.errorMessage = " Erreur lors de l'envoi de la demande !";
          this.successMessage = '';
        }
      });
    } else {
      this.errorMessage = "⚠ Veuillez remplir tous les champs !";
      this.successMessage = '';
    }
  }
}
