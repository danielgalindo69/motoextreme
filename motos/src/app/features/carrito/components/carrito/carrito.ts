import { Component, OnInit } from '@angular/core';
import { CarritoService } from '../services';
import { CarritoResponseDTO } from '../services';
import { CarritoItemResponseDTO } from '../services';
import { CarritoItemRequestDTO } from '../services';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.html',
  styleUrls: ['./carrito.css'],
  standalone: true,
  imports: [CommonModule]
})
export class CarritoComponent implements OnInit {

  carrito: CarritoResponseDTO | null = null;
  usuarioId: number = 1; // ⚠️ Ajusta según el usuario logueado
  nuevoItem: CarritoItemRequestDTO = { cantidad: 1 };

  constructor(private carritoService: CarritoService) {}

  ngOnInit(): void {
    this.cargarCarrito();
  }

  // 🔹 Obtener carrito del usuario
  cargarCarrito(): void {
    this.carritoService.obtenerCarrito(this.usuarioId).subscribe({
      next: (res) => this.carrito = res,
      error: (err) => console.error('Error al cargar carrito', err)
    });
  }

  // 🔹 Agregar item (ejemplo con idMoto)
  agregarItem(idMoto: number): void {
    const dto: CarritoItemRequestDTO = { idMoto, cantidad: this.nuevoItem.cantidad };
    this.carritoService.agregarItem(this.carrito!.idCarrito, dto).subscribe({
      next: (item) => {
        this.carrito?.items.push(item);
        this.actualizarTotal();
      },
      error: (err) => console.error('Error al agregar item', err)
    });
  }

  // 🔹 Actualizar cantidad de un item
  actualizarItem(item: CarritoItemResponseDTO, nuevaCantidad: number): void {
    const dto: CarritoItemRequestDTO = { cantidad: nuevaCantidad };
    this.carritoService.actualizarItem(item.idItem, dto).subscribe({
      next: (actualizado) => {
        const index = this.carrito?.items.findIndex(i => i.idItem === item.idItem);
        if (index !== undefined && index! >= 0) {
          this.carrito!.items[index!] = actualizado;
          this.actualizarTotal();
        }
      },
      error: (err) => console.error('Error al actualizar item', err)
    });
  }

  // 🔹 Eliminar item
  eliminarItem(idItem: number): void {
    this.carritoService.eliminarItem(idItem).subscribe({
      next: () => {
        this.carrito!.items = this.carrito!.items.filter(i => i.idItem !== idItem);
        this.actualizarTotal();
      },
      error: (err) => console.error('Error al eliminar item', err)
    });
  }

  // 🔹 Recalcular total en frontend (opcional)
  actualizarTotal(): void {
    if (this.carrito) {
      this.carrito.total = this.carrito.items
        .map(i => i.subtotal)
        .reduce((acc, val) => acc + val, 0);
    }
  }
}
