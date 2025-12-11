import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Importa las interfaces que definimos

export interface CarritoItemResponseDTO {
  idItem: number;
  tipo: string;          // "MOTO" o "ACCESORIO"
  referenciaId: number;  // idMoto o idAccesorio
  nombre: string;
  cantidad: number;
  precioUnitario: number;
  subtotal: number;
}

export interface CarritoResponseDTO {
  idCarrito: number;
  usuarioId: number;
  total: number;
  items: CarritoItemResponseDTO[];
}

export interface CarritoItemRequestDTO {
  idMoto?: number;
  idAccesorio?: number;
  cantidad: number;
}

export interface UsuarioDTO {
  idUsuario: number;
  nombre: string;
  email: string;
  rol: string;
}

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  private apiUrl = 'http://localhost:9595'; // ⚠️ Ajusta al puerto de tu backend

  constructor(private http: HttpClient) {}

  obtenerCarrito(usuarioId: number) {
  const token = localStorage.getItem('token'); // donde guardes tu JWT
  const headers = { Authorization: `Bearer ${token}` };
  return this.http.get<CarritoResponseDTO>(`${this.apiUrl}/carritos/usuario/${usuarioId}`, { headers });
}


  // 🔹 Crear carrito para un usuario (si no existe)
  crearCarrito(usuarioId: number): Observable<CarritoResponseDTO> {
    return this.http.post<CarritoResponseDTO>(`${this.apiUrl}/carritos/usuario/${usuarioId}`, {});
  }

  // 🔹 Agregar item al carrito
  agregarItem(carritoId: number, dto: CarritoItemRequestDTO): Observable<CarritoItemResponseDTO> {
    return this.http.post<CarritoItemResponseDTO>(`${this.apiUrl}/carrito-items/${carritoId}/agregar`, dto);
  }

  // 🔹 Actualizar cantidad de un item
  actualizarItem(itemId: number, dto: CarritoItemRequestDTO): Observable<CarritoItemResponseDTO> {
    return this.http.put<CarritoItemResponseDTO>(`${this.apiUrl}/carrito-items/${itemId}`, dto);
  }

  // 🔹 Eliminar un item del carrito
  eliminarItem(itemId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/carrito-items/${itemId}`);
  }
}
