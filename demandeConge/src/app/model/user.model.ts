export interface LoginResponse {
  token: string;
  role: 'MANAGER' | 'EMPLOYE';
  employeId?: number; // 👈 ajouté pour correspondre au backend
}

export interface User {
  name: string;
  prenom: string;
  username: string;
  password: string;
  role: 'MANAGER' | 'EMPLOYE';
}

export interface LoginRequest {
  username: string;
  password: string;
}
