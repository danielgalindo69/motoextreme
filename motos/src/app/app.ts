import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Login } from './features/auth/components/login/login';
import { Register } from "./features/auth/components/register/register";


@Component({
  selector: 'app-root',
  imports: [Register],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'motos';
}
