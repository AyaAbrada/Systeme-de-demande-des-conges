import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {DemandesComponent} from './demandes/demandes.component';
import {HistoriqueComponent} from './historique/historique.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {DashboardEmployeComponent} from './dashboard-employe/dashboard-employe.component';
import {DashboardManagerComponent} from './dashboard-manager/dashboard-manager.component';
import {DemandeFormComponent} from './demande-form/demande-form.component';
import {SoldeComponent} from './solde/solde.component';
import {HistoriqueEmployeComponent} from './historique-employe/historique-employe.component';
import {SoldeEmployeComponent} from './solde-employe/solde-employe.component';
import {CreateEmployeeComponent} from './create-employee/create-employee.component';
import {ProposComponent} from './propos/propos.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {path: "login",component: LoginComponent},
  {path: "demandes",component: DemandesComponent},
  {path: "historique", component: HistoriqueComponent},
  {path: "historique-employe", component: HistoriqueEmployeComponent},
  {path: "dashboard", component: DashboardComponent},
  {path: "dashboard-employe", component: DashboardEmployeComponent},
  {path: "dashboard-manager", component: DashboardManagerComponent},
  {path: "demande-form", component: DemandeFormComponent},
  {path: "solde", component: SoldeComponent},
  {path: "solde-employe", component: SoldeEmployeComponent},
  {path: "create-employee", component: CreateEmployeeComponent},
  {path: "propos", component: ProposComponent}

];
