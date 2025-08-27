import { Component } from '@angular/core';
import {SoldeConge, SoldeCongeService} from '../services/solde.service';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-solde',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './solde.component.html',
  styleUrl: './solde.component.css'
})
export class SoldeComponent {
  employeId: number = 0;
  soldes: SoldeConge[] = [];
  loading = false;
  error = '';

  constructor(private soldeCongeService: SoldeCongeService) {}

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

}
