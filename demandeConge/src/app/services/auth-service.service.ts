import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginRequest, LoginResponse, User} from '../model/user.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private apiUrl = 'http://localhost:8083/api/v1/auth';

  constructor(private http : HttpClient) { }

  login(date : LoginRequest): Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`,date);
  }

  register(user : User):Observable<any>{
    return this.http.post(`${this.apiUrl}/register`,user)
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null{
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean{
    return this.getToken() !== null ;
  }
}
