import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AccesorioRequest {
  nombre: string;
  descripcion: string;
  precio: number;
  idCategoria: number;
}

export interface AccesorioResponse {
  idAccesorio: number;
  nombre: string;
  descripcion: string;
  precio: number;
  idCategoria: number;
}

@Injectable({
  providedIn: 'root'
})
export class AccesorioService {
  private apiUrl = 'http://localhost:9595/accesorios'; // 👈 ajusta tu puerto

  constructor(private http: HttpClient) {}

  crear(dto: AccesorioRequest): Observable<AccesorioResponse> {
    return this.http.post<AccesorioResponse>(this.apiUrl, dto);
  }

  actualizar(id: number, dto: AccesorioRequest): Observable<AccesorioResponse> {
    return this.http.put<AccesorioResponse>(`${this.apiUrl}/${id}`, dto);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  listar(): Observable<AccesorioResponse[]> {
    return this.http.get<AccesorioResponse[]>(this.apiUrl);
  }
}
