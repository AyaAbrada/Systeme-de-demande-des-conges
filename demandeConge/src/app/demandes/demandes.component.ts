import { Component, OnInit } from '@angular/core';
import { DemandeConge, DemandeCongeServiceService } from '../services/demande-conge-service.service';
import { CommonModule } from '@angular/common';

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
    const managerId = 17;
    this.demandeService.validerDemande(demandeId, managerId).subscribe(() => {
      this.chargerDemandes();
    });
  }

  refuser(demandeId: number): void {
    const managerId = 17;
    this.demandeService.refuserDemande(demandeId, managerId).subscribe(() => {
      this.chargerDemandes();
    });
  }
}
