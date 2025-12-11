import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MotoService, MotoResponseDTO } from '../../../service/modelos/modelos';
import { CarritoItemRequestDTO, CarritoService } from '../../../../carrito/components/services';

import { Footer } from "../../../../../comp_shared/components/footer/footer";
import { UserHeader } from '../../../../../comp_shared/components/user-header/user-header';
import { UserAccesorios } from "../../userAccesorios/user-accesorios/user-accesorios";

@Component({
  selector: 'app-usermodelos',
  standalone: true,
  imports: [CommonModule, RouterLink, Footer, UserHeader, UserAccesorios],
  templateUrl: './user-modelos.html',
  styleUrl: './user-modelos.css',
})
export class UserModelos implements OnInit {
  motos: MotoResponseDTO[] = [];
  usuarioId: number = 1;   // ⚠️ Ajusta según el usuario logueado
  carritoId: number = 0;   // se obtiene al cargar el carrito

  constructor(
    private motoService: MotoService,
    private carritoService: CarritoService
  ) {}

   ngOnInit(): void {
    // cargar motos
    this.motoService.getMotos().subscribe({
      next: (data) => this.motos = data,
      error: (err) => console.error('Error cargando motos', err)
    });

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
}
