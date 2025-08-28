import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DemandeCongeServiceService } from '../services/demande-conge-service.service';
import { DemandeConge } from '../model/demande-conge.model';

@Component({
  selector: 'app-historique-employe',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './historique-employe.component.html',
  styleUrls: ['./historique-employe.component.css']
})
export class HistoriqueEmployeComponent implements OnInit {
  demandes: DemandeConge[] = [];
  loading = true;
  errorMessage = '';

  constructor(private demandeService: DemandeCongeServiceService) {}

  ngOnInit(): void {
    // Récupérer l'ID de l'employé depuis le localStorage
    const employeId = Number(localStorage.getItem('employeId'));

    if (!employeId) {
      this.errorMessage = 'Employé non identifié.';
      this.loading = false;
      return;
    }

    // Appel au service pour récupérer les demandes
    this.demandeService.getDemandesByEmploye(employeId).subscribe({
      next: (data) => {
        this.demandes = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des demandes', err);
        this.errorMessage = 'Impossible de charger les demandes.';
        this.loading = false;
      }
    });
  }
}
