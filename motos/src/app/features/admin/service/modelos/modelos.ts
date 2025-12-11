import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface MotoRequest {
  nombre: string;
  descripcion: string;
  precio: number;
  marca: string;        // Enum en backend, aquí string
  modelo: string;
  cilindraje: string;
  potencia: string;
  idCategoria: number;
}
export interface MotoResponse {
  idMoto: number;
  nombre: string;
  descripcion: string;
  precio: number;
  marca: string;
  modelo: string;
  cilindraje: string;
  potencia: string;
  idCategoria: number;

  // 🔥 Campo opcional solo para el frontend
  editando?: boolean;
}

@Injectable({ providedIn: 'root' })
export class MotoService {
  private apiUrl = 'http://localhost:9595/motos';

  constructor(private http: HttpClient) {}

  listar(): Observable<MotoResponse[]> {
    return this.http.get<MotoResponse[]>(this.apiUrl);
  }

  crear(dto: MotoRequest): Observable<MotoResponse> {
    return this.http.post<MotoResponse>(this.apiUrl, dto);
  }

  actualizar(id: number, dto: MotoRequest): Observable<MotoResponse> {
    return this.http.put<MotoResponse>(`${this.apiUrl}/${id}`, dto);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
