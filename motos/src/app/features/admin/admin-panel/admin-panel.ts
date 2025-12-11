import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterOutlet],
  templateUrl: './admin-panel.html',
  styleUrls: ['./admin-panel.css']
})
export class AdminComponent {
  constructor(private router: Router) {} // 👈 ahora sí funciona

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
