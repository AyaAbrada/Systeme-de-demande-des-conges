import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface DemandeConge {
  id: number;
  typeConge: string;
  employeNomComplet: string;
  managerNomComplet: string;
  statut: string;
  dateDebut: string;
  dateFin: string;
  dateSoumission: string;
  dateTraitement: string;
  motifRecu: string;
}

@Injectable({
  providedIn: 'root'
})
export class HistoriqueService {

  private apiUrl = 'http://localhost:8083/api/demandes';

  constructor(private http: HttpClient) {}

  getAllDemandes(): Observable<DemandeConge[]> {
    return this.http.get<DemandeConge[]>(this.apiUrl);
  }
}
