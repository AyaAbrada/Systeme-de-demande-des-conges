import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginRequest, LoginResponse, User} from '../model/user.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private apiUrl = 'http://localhost:8083/api/v1/auth';

  constructor(private http: HttpClient) { }

  login(date: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, date);
  }

  register(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  saveRole(role: 'MANAGER' | 'EMPLOYE'): void {
    localStorage.setItem('role', role);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getRole(): 'MANAGER' | 'EMPLOYE' | null {
    const role = localStorage.getItem('role');
    if (role === 'MANAGER' || role === 'EMPLOYE') {
      return role;
    }
    return null;
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  registerEmployee(employee: any): Observable<any> {
    const token = this.getToken(); // récupérer token stocké
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(`${this.apiUrl}/register`, employee, { headers });
  }
}
