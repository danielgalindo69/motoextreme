import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MotoService, MotoResponseDTO } from '../../service/modelos/modelos';
import { Footer } from "../../../../comp_shared/components/footer/footer";
import { Header } from "../../../../comp_shared/components/header/header";
import { MotoDetalleComponent } from '../modelo-id/modelo-id';

@Component({
  selector: 'app-modelos',
  standalone: true,
  imports: [CommonModule, MotoDetalleComponent,Footer, Header],
  templateUrl: './modelos.html',
  styleUrl: './modelos.css',
})

export class ModelosComponent implements OnInit {
  motos: MotoResponseDTO[] = [];
  motosFiltradas: any[] = [];
  minValor: number = 0;
  maxValor: number = 1000000000;
  categoriaSeleccionadas: any[] = [];;

  constructor(private motoService: MotoService) {}

  ngOnInit(): void {
    this.getMotos()
  }

  // mostrar motos - filtro
  getMotos(){
    this.motoService.getMotos().subscribe((data: any[]) => {
      this.motos = data;
      this.motosFiltradas = data;

    this.aplicarFiltro();
    })
  }

  eliminarMoto(id: number) {
    this.motoService.eliminarMoto(id).subscribe({
      next: () => this.motos = this.motos.filter(m => m.idMoto !== id),
      error: (err) => console.error('Error eliminando moto', err)
    });
  }

  // Control del modal
  mostrarModal = false;
  motoSeleccionada!: number;

  abrirModal(id: number){
    this.motoSeleccionada = id;
    this.mostrarModal = true;
  }

  cerrarModal(){
    this.mostrarModal = false;
  }


  // filtros de categias
  categoriasMap: Record<number, string> = {
    1: 'Naked',
    2: 'Scooter',
    3: 'Deportivas',
    4: 'Adventure',
    5: 'Cruiser',
    6: 'Scrambler',
    7: 'Supermotorad',
    8: 'Doble Propósito',
    9: 'Urbanas'
  }


  actualizarValorMin(event: any){
    const valor = Number(event.target.value);
    this.minValor = valor;
    this.aplicarFiltro();
  }

  actualizarValorMax(event: any){
    const valor = Number(event.target.value);
    this.maxValor = valor;
    this.aplicarFiltro();
  }

  toggleCategoria(event: any){
    const categoria = event.target.value;
    if (event.target.checked){
      this.categoriaSeleccionadas.push(categoria);
    }else {
      this.categoriaSeleccionadas = this.categoriaSeleccionadas.filter(c => c !== categoria);
    }
    this.aplicarFiltro();
  }

  // aplicar filtros a las motos
  aplicarFiltro(){
    this.motosFiltradas = this.motos.filter((moto) =>{

      const nombreCategoria = this.categoriasMap[moto.idCategoria];

      const coincideCategoria =
      this.categoriaSeleccionadas.length === 0 ||
      this.categoriaSeleccionadas.includes(nombreCategoria);

      const CoincidePrecio =
      moto.precio <=  this.maxValor;

      return CoincidePrecio && coincideCategoria;
    });
  }

  // precio
  onPrecioChange(event: any){
    this.maxValor = Number(event.target.value);
    this.aplicarFiltro();
  }
}
