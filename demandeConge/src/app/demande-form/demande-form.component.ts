import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-demande-form',
  templateUrl: './demande-form.component.html',
  imports: [ReactiveFormsModule],
  styleUrls: ['./demande-form.component.css']
})
export class DemandeFormComponent implements OnInit {
  demandeForm!: FormGroup;
  employeId!: number;

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    // Récupérer l'id de l'employé depuis localStorage
    const storedId = localStorage.getItem('employeId');
    if (!storedId) {
      alert("Employé non connecté !");
      return;
    }
    this.employeId = Number(storedId);

    // Initialisation du formulaire
    this.demandeForm = this.fb.group({
      employeId: [this.employeId, Validators.required],
      managerId: [1, Validators.required], // Manager fixe = 1
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
          alert("Demande envoyée avec succès !");
          this.demandeForm.reset({
            employeId: this.employeId,
            managerId: 1
          });
        },
        error: (err) => {
          console.error("Erreur:", err);
          alert("Erreur lors de l'envoi de la demande !");
        }
      });
    } else {
      alert("Veuillez remplir tous les champs !");
    }
  }
}
