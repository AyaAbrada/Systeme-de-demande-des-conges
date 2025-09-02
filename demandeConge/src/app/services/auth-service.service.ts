import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest, LoginResponse } from '../model/user.model';
import { RegisterRequest } from '../model/register-request.model';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private apiUrl = 'http://localhost:8083/api/v1/auth';

  constructor(private http: HttpClient) {}

  login(data: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, data, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  register(user: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  registerEmployee(employee: {
    password: string;
    role: string;
    name: string;
    prenom: string;
    username: string
  }): Observable<any> {
    const token = this.getToken();
    if (!token) throw new Error('No JWT token found');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.post(`${this.apiUrl}/register`, employee, { headers });
  }

  saveToken(token: string) { localStorage.setItem('token', token); }
  saveRole(role: 'MANAGER' | 'EMPLOYE') { localStorage.setItem('role', role); }
  getToken(): string | null { return localStorage.getItem('token'); }
  getRole(): 'MANAGER' | 'EMPLOYE' | null {
    const role = localStorage.getItem('role');
    return role === 'MANAGER' || role === 'EMPLOYE' ? role : null;
  }
  isAuthenticated(): boolean { return this.getToken() !== null; }

  clearAuth() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('employeId');
  }
}
