import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavbarComponent} from './navbar/navbar.component';
import {FoterComponent} from './foter/foter.component';

@Component({
  selector: 'app-root',
  imports: [NavbarComponent, RouterOutlet, FoterComponent ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'demandeConge';
}
