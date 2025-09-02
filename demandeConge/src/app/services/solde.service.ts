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

  getSoldeParEmploye(employeId: number): Observable<SoldeConge[]> {
    return this.http.get<SoldeConge[]>(`${this.apiUrl}/${employeId}`);
  }

  initialiserTousLesSoldes(): Observable<string> {
    // @ts-ignore
    return this.http.post(`${this.apiUrl}/init`, {});
  }

  getSoldeParUsername(username: string): Observable<SoldeConge[]> {
    return this.http.get<SoldeConge[]>(`${this.apiUrl}/username/${username}`);
  }

}
