
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MotoService, MotoResponseDTO } from '../../../service/modelos/modelos';
import { CarritoItemRequestDTO, CarritoService } from '../../../../carrito/components/services';
import { MotoDetalleComponent } from '../../modelo-id/modelo-id';

import { Footer } from "../../../../../comp_shared/components/footer/footer";
import { UserHeader } from '../../../../../comp_shared/components/user-header/user-header';
import { UserAccesorios } from "../../userAccesorios/user-accesorios/user-accesorios";



@Component({
  selector: 'app-usermodelos',
  standalone: true,
  imports: [CommonModule, Footer ,UserHeader, MotoDetalleComponent],
  templateUrl: './user-modelos.html',
  styleUrl: './user-modelos.css',
})

export class UserModelos implements OnInit {
  motos: MotoResponseDTO[] = [];
  usuarioId: number = 1;   // ⚠️ Ajusta según el usuario logueado
  carritoId: number = 0;   // se obtiene al cargar el carrito

  motosFiltradas: any[] = [];
  minValor: number = 3000000;
  maxValor: number = 1000000000;
  categoriaSeleccionadas: any[] = [];

  constructor(
    private motoService: MotoService,
    private carritoService: CarritoService
  ) {}

  ngOnInit(): void {
    // cargar motos
    this.getMotos();

    // cargar carrito del usuario
    this.carritoService.obtenerCarrito(this.usuarioId).subscribe({
      next: (carrito) => {
        this.carritoId = carrito.idCarrito;
      },
      error: (err) => {
        console.error('Error cargando carrito', err);

        // si no existe, lo creamos
        this.carritoService.crearCarrito(this.usuarioId).subscribe({
          next: (nuevo) => this.carritoId = nuevo.idCarrito,
          error: (e) => console.error('Error creando carrito', e)
        });
      }
    });
  }

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

  // 🔹 Método para agregar moto al carrito
  agregarAlCarrito(moto: MotoResponseDTO) {
    const dto: CarritoItemRequestDTO = {
      idMoto: moto.idMoto,
      cantidad: 1
    };

    this.carritoService.agregarItem(this.carritoId, dto).subscribe({
      next: (item) => console.log('Moto agregada al carrito', item),
      error: (err) => console.error('Error agregando moto al carrito', err)
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
      moto.precio <= this.maxValor;
      return CoincidePrecio && coincideCategoria;
    });
  }

  // precio
  onPrecioChange(event: any){
    this.maxValor = Number(event.target.value);
    this.aplicarFiltro();
  }
}
