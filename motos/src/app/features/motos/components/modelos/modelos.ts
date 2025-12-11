<<<<<<< HEAD
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CardModelos } from '../../../../components/card-modelos/card-modelos';
import { MotosService } from '../../../../servicios/motosService'

@Component({
  selector: 'app-modelos',
  imports: [CommonModule],
  templateUrl: './modelos.html',
  styleUrl: './modelos.css',
})

export class Modelos implements OnInit{

  // Datos originales desde API
  motosOriginal: any[] = [];

  // Datos filtrados (lo que se muestra en pantalla)
  motosFiltradas: any[] = [];

  // Valores de filtro
  minValue: number = 3000000;
  maxValue: number = 70000000;

  categoriasSeleccionadas: string[] = [];

  constructor(private motosService: MotosService) {}

  ngOnInit(): void {
    this.motosService.getMotos().subscribe(data => {
      this.motosOriginal = data;
      this.motosFiltradas = data; // mostrar todo al inicio
    });
  }

  // Rango mínimo
  updateMinPrice(event: any) {
    const value = Number(event.target.value);

    if (value >= this.maxValue) {
      this.minValue = this.maxValue - 100000;
    } else {
      this.minValue = value;
    }
    this.applyFilters();
  }

  // Rango máximo
  updateMaxPrice(event: any) {
    const value = Number(event.target.value);

    if (value <= this.minValue) {
      this.maxValue = this.minValue + 100000;
    } else {
      this.maxValue = value;
    }
    this.applyFilters();
  }

  // Checkbox de categorías
  toggleCategory(event: any) {
    const categoria = event.target.value;

    if (event.target.checked) {
      this.categoriasSeleccionadas.push(categoria);
    } else {
      this.categoriasSeleccionadas =
        this.categoriasSeleccionadas.filter(c => c !== categoria);
    }

    this.applyFilters();
  }

  // FILTRADO COMPLETO
  applyFilters() {
    let data = [...this.motosOriginal];

    // 1. Filtrar por categoría
    if (this.categoriasSeleccionadas.length > 0 &&
        !this.categoriasSeleccionadas.includes('todas')) {

      data = data.filter(moto =>
        this.categoriasSeleccionadas.includes(moto.categoria.toLowerCase())
      );
    }

    // 2. Filtrar por precio
    data = data.filter(moto =>
      moto.precio >= this.minValue && moto.precio <= this.maxValue
    );

    this.motosFiltradas = data;
  }
=======
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
>>>>>>> 2ae4d97587b6b62a4b41d460c97db426298bf37e
}
