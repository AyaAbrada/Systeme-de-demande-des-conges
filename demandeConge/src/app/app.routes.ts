import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {DemandesComponent} from './demandes/demandes.component';
import {HistoriqueComponent} from './historique/historique.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {DashboardEmployeComponent} from './dashboard-employe/dashboard-employe.component';
import {DashboardManagerComponent} from './dashboard-manager/dashboard-manager.component';
import {DemandeFormComponent} from './demande-form/demande-form.component';
import {SoldeComponent} from './solde/solde.component';
import {HistoriqueEmployeComponent} from './historique-employe/historique-employe.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {path: "login",component: LoginComponent},
  {path: "register",component: RegisterComponent},
  {path: "demandes",component: DemandesComponent},
  {path: "historique", component: HistoriqueComponent},
  {path: "historique-employe", component: HistoriqueEmployeComponent},
  {path: "dashboard", component: DashboardComponent},
  {path: "employe-manager", component: DashboardEmployeComponent},
  {path: "dashboard-manager", component: DashboardManagerComponent},
  {path: "demande-form", component: DemandeFormComponent},
  {path: "solde", component: SoldeComponent}


];
