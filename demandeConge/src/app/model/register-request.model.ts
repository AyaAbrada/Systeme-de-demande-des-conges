export interface RegisterRequest {
  name: string;
  prenom: string;
  username: string;
  password: string;
  role: 'MANAGER' | 'EMPLOYE';
}
