import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccesorioService, AccesorioResponseDTO } from '../../../service/accesorios/accesorios';
import { UserHeader } from '../../../../../comp_shared/components/user-header/user-header';
import { Footer } from '../../../../../comp_shared/components/footer/footer';

@Component({
  selector: 'app-useraccesorios',
  standalone: true,
  imports: [CommonModule, Footer, UserHeader],
  templateUrl: './user-accesorios.html',
  styleUrls: ['./user-accesorios.css']
})
export class UserAccesorios implements OnInit {
  accesorios: AccesorioResponseDTO[] = [];

  constructor(private accesorioService: AccesorioService) {}

  ngOnInit(): void {
    this.accesorioService.getAccesorios().subscribe({
      next: (data) => this.accesorios = data,
      error: (err) => console.error('Error cargando accesorios', err)
    });
  }
}
