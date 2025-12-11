import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CardModelos } from '../../../../components/card-modelos/card-modelos';

@Component({
  selector: 'app-modelos',
  imports: [CommonModule, CardModelos],
  templateUrl: './modelos.html',
  styleUrl: './modelos.css',
})

export class Modelos {
  motos = [
    { nombre: 'Yamaha R1', categoria: 'deportiva', precio: 65000000 },
    { nombre: 'Honda CBR 500', categoria: 'deportiva', precio: 32000000 },
    { nombre: 'Harley Davidson', categoria: 'cruiser', precio: 72000000 },
    { nombre: 'Kawasaki Z650', categoria: 'naked', precio: 29000000 },
    { nombre: 'BMW GS 1200', categoria: 'adventure', precio: 58000000 },
    { nombre: 'Honda Goldwing', categoria: 'touring', precio: 90000000 }
  ];

  motosFiltradas = [...this.motos]

  minValue: number = 3000000;
  maxValue: number = 70000000;

  updateMinPrice(event: any){
    const value = Number(event.target.value);

    if(value >= this.maxValue){
      this.minValue = this.maxValue - 100000;
    }else {
      this.minValue = value;
    }
    this.applyFilters(); // Llama filtro automáticamente
  }

  updateMaxPrice(event: any){
    const value = Number(event.target.value);

    if(value <= this.minValue){
      this.minValue = this.maxValue + 100000;
    }else{
      this.maxValue = value
    }
    this.applyFilters();
  }


  categoriasSeleccionadas: string[] = [];

  toggleCategory(event: any){
    const categoria = event.target.value;

    if(event.target.checked){
      this.categoriasSeleccionadas.push(categoria);
    }else{
      this.categoriasSeleccionadas = this.categoriasSeleccionadas.filter(c => c !== categoria);
    }
    this.applyFilters();
  }

  applyFilters(){
    this.motosFiltradas = this.motos.filter(moto => {

      const coincideCategoria =
        this.categoriasSeleccionadas.length === 0 ||
        this.categoriasSeleccionadas.includes(moto.categoria)

      const coincidePrecio =
        moto.precio >= this.minValue &&
        moto.precio <= this.maxValue;

      return coincideCategoria && coincidePrecio;
    })
  }
}
