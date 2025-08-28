import { Component, OnInit } from '@angular/core';
import { HistoriqueService } from '../services/historique.service';
import { CommonModule } from '@angular/common';
import {DemandeConge} from '../model/demande-conge.model';

@Component({
  selector: 'app-historique',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './historique.component.html',
  styleUrls: ['./historique.component.css']
})
export class HistoriqueComponent implements OnInit {
  demandes: DemandeConge[] = [];
  loading = true;
  errorMessage = '';

  constructor(private historiqueService: HistoriqueService) {}

  ngOnInit(): void {
    this.historiqueService.getAllDemandes().subscribe({
      next: (data) => {
        this.demandes = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Impossible de charger les demandes.';
        this.loading = false;
      }
    });
  }
}
