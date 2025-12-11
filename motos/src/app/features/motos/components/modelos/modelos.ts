import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MotoService, MotoResponseDTO } from '../../service/modelos/modelos';
import { RouterLink } from '@angular/router';
import { Footer } from "../../../../comp_shared/components/footer/footer";
import { Header } from "../../../../comp_shared/components/header/header";

@Component({
  selector: 'app-modelos',
  standalone: true,
  imports: [CommonModule, RouterLink, Footer, Header],
  templateUrl: './modelos.html',
  styleUrl: './modelos.css',
})
export class ModelosComponent implements OnInit {
   motos: MotoResponseDTO[] = [];

  constructor(private motoService: MotoService) {}

  ngOnInit(): void {
  this.motoService.getMotos().subscribe({
    next: (data) => this.motos = data,
    error: (err) => console.error('Error cargando motos', err)
  });
}
  eliminarMoto(id: number) {
    this.motoService.eliminarMoto(id).subscribe({
      next: () => this.motos = this.motos.filter(m => m.idMoto !== id),
      error: (err) => console.error('Error eliminando moto', err)
    });
  }
}
