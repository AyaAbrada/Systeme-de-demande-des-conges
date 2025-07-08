import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {DemandesComponent} from './demandes/demandes.component';
import {HistoriqueComponent} from './historique/historique.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {path: "login",component: LoginComponent},
  {path: "register",component: RegisterComponent},
  {path: "demandes",component: DemandesComponent},
  {path: "hisrorique", component: HistoriqueComponent}
];
