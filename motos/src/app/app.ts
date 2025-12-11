import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Login } from './features/auth/components/login/login';
import { Header } from "./comp_shared/components/header/header";
import { Footer } from "./comp_shared/components/footer/footer";
import { Register } from './features/auth/components/register/register';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'motos';
}
