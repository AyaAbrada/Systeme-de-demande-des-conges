import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DemandeCongeServiceService} from '../services/demande-conge-service.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-demande-form',
  templateUrl: './demande-form.component.html',
  imports: [CommonModule, ReactiveFormsModule],
  standalone: true,
  styleUrls: ['./demande-form.component.css']
})
export class DemandeFormComponent implements OnInit {

  demandeForm!: FormGroup;
  typesConge: string[] = ['Maladie', 'Annuel'];

  constructor(
    private fb: FormBuilder,
    private demandeService: DemandeCongeServiceService
  ) {}

  ngOnInit(): void {
    this.demandeForm = this.fb.group({
      typeConge: ['', Validators.required],
      employeId: ['', Validators.required],
      managerId: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
    });
  }

  onSubmit(): void {
    console.log('onSubmit called');
    if (this.demandeForm.valid) {
      const formValues = this.demandeForm.value;

      // جبد id المستخدم المسجل دخول من الـ localStorage أو سيرفر
      const employeId = localStorage.getItem('userId'); // مثال فقط

      const demande = {
        typeConge: formValues.typeConge,
        dateDebut: formValues.dateDebut,
        dateFin: formValues.dateFin,
        employe: { id: Number(employeId) },
        manager: { id: Number(formValues.managerId) }
      };

      this.demandeService.createDemande(demande).subscribe({
        next: (res) => {
          console.log('Réponse:', res);
          alert('Demande envoyée avec succès !');
        },
        error: (err) => {
          console.error('Erreur:', err);
          alert('Erreur lors de l\'envoi de la demande.');
        }
      });

    }

  }
}
