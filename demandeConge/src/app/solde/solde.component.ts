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
  username: string = '';
  soldes: SoldeConge[] = [];
  loading = false;
  error = '';
  initLoading = false;
  initMessage = '';
  initError = '';

  constructor(private soldeCongeService: SoldeCongeService) {}

  getSolde() {
    if (!this.username) {
      this.error = 'Veuillez entrer le username';
      return;
    }

    this.loading = true;
    this.error = '';
    this.soldeCongeService.getSoldeParUsername(this.username).subscribe({
      next: (data) => {
        this.soldes = data;
        if (data.length === 0) {
          this.error = 'Aucun solde trouvé pour ce username';
        }
        this.loading = false;
      },
      error: () => {
        this.error = 'Erreur lors du chargement du solde';
        this.loading = false;
      }
    });
  }

  initialiserSoldes() {
    this.initLoading = true;
    this.initMessage = '';
    this.initError = '';
    this.soldeCongeService.initialiserTousLesSoldes().subscribe({
      next: (res) => {
        this.initMessage = " Soldes initialisés avec succès";
        this.initLoading = false;

      },
      error: (err) => {
        this.initError = ' Erreur lors de l’initialisation des soldes.';
        this.initLoading = false;
        console.error(err);
      }
    });
  }

}
