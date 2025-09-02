import { Component, OnInit } from '@angular/core';
import { DemandeCongeServiceService } from '../services/demande-conge-service.service';
import { CommonModule } from '@angular/common';
import {DemandeConge} from '../model/demande-conge.model';

@Component({
  selector: 'app-demandes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './demandes.component.html',
  styleUrls: ['./demandes.component.css']
})
export class DemandesComponent implements OnInit {
  demandes: DemandeConge[] = [];
  loading: boolean = false;
  error: string = '';

  constructor(private demandeService: DemandeCongeServiceService) {}

  ngOnInit(): void {
    this.chargerDemandes();
  }

  chargerDemandes(): void {
    this.loading = true;
    this.error = '';
    this.demandeService.getAllDemandes().subscribe({
      next: (data: DemandeConge[]) => {
        this.demandes = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des demandes';
        this.loading = false;
      }
    });
  }

  accepter(demandeId: number): void {
    const managerId = 1;
    this.demandeService.validerDemande(demandeId, managerId).subscribe(() => {
      this.chargerDemandes();
    });
  }

  refuser(demandeId: number): void {
    const managerId = 1;
    this.demandeService.refuserDemande(demandeId, managerId).subscribe(() => {
      this.chargerDemandes();
    });
  }
}
