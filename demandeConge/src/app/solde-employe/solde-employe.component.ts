import {Component, OnInit} from '@angular/core';
import {SoldeConge, SoldeCongeService} from '../services/solde.service';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-solde-employe',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './solde-employe.component.html',
  styleUrl: './solde-employe.component.css'
})
export class SoldeEmployeComponent implements OnInit{
  soldes: SoldeConge[] = [];
  loading = true;
  error = '';

  constructor(private soldeCongeService: SoldeCongeService) {}

  ngOnInit(): void {
    const employeId = Number(localStorage.getItem('employeId'));

    if (!employeId) {
      this.error = 'Employé non identifié.';
      this.loading = false;
      return;
    }

    this.soldeCongeService.getSoldeParEmploye(employeId).subscribe({
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
