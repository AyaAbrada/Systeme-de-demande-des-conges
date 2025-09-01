import { Component } from '@angular/core';
import { SoldeConge, SoldeCongeService } from '../services/solde.service';
import { FormsModule } from '@angular/forms';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-solde',
  standalone: true,
  imports: [FormsModule, NgForOf, NgIf],
  templateUrl: './solde.component.html',
  styleUrls: ['./solde.component.css']
})
export class SoldeComponent {
  employeId: number = 0;
  soldes: SoldeConge[] = [];
  loading = false;
  error = '';
  initLoading = false;
  initMessage = '';
  initError = '';

  constructor(private soldeCongeService: SoldeCongeService) {}

  // Récupérer le solde pour un employé
  getSolde() {
    this.loading = true;
    this.error = '';
    this.soldeCongeService.getSoldeParEmploye(this.employeId).subscribe({
      next: (data) => {
        this.soldes = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Erreur lors du chargement du solde';
        this.loading = false;
      }
    });
  }

  // Initialiser tous les soldes
  initialiserSoldes() {
    this.initLoading = true;
    this.initMessage = '';
    this.initError = '';
    this.soldeCongeService.initialiserTousLesSoldes().subscribe({
      next: (res: any) => {
        this.initMessage = res; // "Soldes initialisés pour tous les employés."
        this.initLoading = false;
      },
      error: (err) => {
        this.initError = 'Erreur lors de l’initialisation des soldes.';
        this.initLoading = false;
        console.error(err);
      }
    });
  }
}
