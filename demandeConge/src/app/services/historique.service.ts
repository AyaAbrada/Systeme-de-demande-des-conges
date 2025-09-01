import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DemandeConge } from '../model/demande-conge.model';

@Injectable({
  providedIn: 'root'
})
export class HistoriqueService {

  private apiUrl = 'http://localhost:8083/api/demandes';

  constructor(private http: HttpClient) {}

  getAllDemandes(): Observable<DemandeConge[]> {
    const token = localStorage.getItem('token'); // JWT token
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<DemandeConge[]>(this.apiUrl, { headers });
  }
}
