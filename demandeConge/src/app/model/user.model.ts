export interface LoginResponse {
  token: string;
  role: 'MANAGER' | 'EMPLOYE';
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
