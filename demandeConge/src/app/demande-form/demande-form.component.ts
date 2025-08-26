import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-demande-form',
  templateUrl: './demande-form.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./demande-form.component.css']
})
export class DemandeFormComponent implements OnInit {
  demandeForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    // Initialisation du formulaire avec les champs nécessaires
    this.demandeForm = this.fb.group({
      employeId: ['', Validators.required],
      managerId: ['', Validators.required],
      typeConge: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.demandeForm.valid) {
      const demande = this.demandeForm.value;
      console.log("Payload envoyé:", demande);

      this.http.post("http://localhost:8083/api/demandes", demande).subscribe({
        next: (res) => {
          console.log("Succès:", res);
          alert("Demande envoyée avec succès ");
          this.demandeForm.reset();
        },
        error: (err) => {
          console.error("Erreur:", err);
          alert("Erreur lors de l'envoi ");
        }
      });
    } else {
      alert("Veuillez remplir tous les champs !");
    }
  }
}
