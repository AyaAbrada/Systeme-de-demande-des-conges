import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface DemandeConge {
  id: number;
  typeConge: string;
  employeNomComplet: string | null;
  managerNomComplet: string | null;
  dateDebut: string;
  dateFin: string;
  dateSoumission: string;
  dateTraitement: string | null;
  statut: string;
  motifRecu?: string | null;
}

@Injectable({
  providedIn: 'root'
})
export class DemandeCongeServiceService {
  private apiUrl = 'http://localhost:8083/api/demandes';

  constructor(private http: HttpClient) {}

  getAllDemandes(): Observable<DemandeConge[]> {
    return this.http.get<DemandeConge[]>(this.apiUrl);
  }

  validerDemande(id: number, managerId: number): Observable<DemandeConge> {
    return this.http.put<DemandeConge>(`${this.apiUrl}/${id}/valider?managerId=${managerId}`, {});
  }

  refuserDemande(id: number, managerId: number): Observable<DemandeConge> {
    return this.http.put<DemandeConge>(`${this.apiUrl}/${id}/refuser?managerId=${managerId}`, {});
  }
}
