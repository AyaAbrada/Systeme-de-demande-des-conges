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

  constructor(private demandeService: DemandeCongeServiceService) {}

  ngOnInit(): void {
    this.chargerDemandes();
  }

  chargerDemandes(): void {
    this.demandeService.getAllDemandes().subscribe((data: DemandeConge[]) => {
      this.demandes = data;
    });
  }

  accepter(demandeId: number): void {
    const managerId = 20;
    this.demandeService.validerDemande(demandeId, managerId).subscribe(() => {
      this.chargerDemandes();
    });
  }

  refuser(demandeId: number): void {
    const managerId = 20;
    this.demandeService.refuserDemande(demandeId, managerId).subscribe(() => {
      this.chargerDemandes();
    });
  }
}
