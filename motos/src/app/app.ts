import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Login } from './features/auth/components/login/login';
import { Header } from "./comp_shared/components/header/header";
import { Footer } from "./comp_shared/components/footer/footer";


@Component({
  selector: 'app-root',
  imports: [Header, RouterOutlet, Footer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'motos';
}
