import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SoldeConge {
  id: number;
  typeConge: string;
  joursRestants: number;
}

@Injectable({
  providedIn: 'root'
})
export class SoldeCongeService {

  private apiUrl = 'http://localhost:8083/api/solde';

  constructor(private http: HttpClient) {}

  // Récupérer les soldes par employé
  getSoldeParEmploye(employeId: number): Observable<SoldeConge[]> {
    return this.http.get<SoldeConge[]>(`${this.apiUrl}/${employeId}`);
  }

  // Initialiser les soldes pour tous les employés
  initialiserTousLesSoldes(): Observable<string> {
    // @ts-ignore
    return this.http.post(`${this.apiUrl}/init`, {});
  }
}
