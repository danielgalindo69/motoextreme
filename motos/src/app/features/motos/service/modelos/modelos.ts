import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface MotoResponseDTO {
  idMoto: number;
  nombre: string;
  descripcion: string;
  precio: number;
  marca: string;
  modelo: string;
  cilindraje: string;
  potencia: string;
  idCategoria: number;
  categoriaNombre: string;
}

export interface MotoRequestDTO {
  nombre: string;
  descripcion: string;
  precio: number;
  marca: string;
  modelo: string;
  cilindraje: string;
  potencia: string;
  idCategoria: number;
}

@Injectable({
  providedIn: 'root'
})

export class MotoService {
  private apiUrl = 'http://localhost:9595/motos';
  private apiMarcas = 'http://localhost:9595/categoria';


  constructor(private http: HttpClient) {}
  getMotos(): Observable<MotoResponseDTO[]> {
    return this.http.get<MotoResponseDTO[]>(`http://localhost:9595/motos`);
  }


  getMotoById(id: number): Observable<MotoResponseDTO> {
    return this.http.get<MotoResponseDTO>(`${this.apiUrl}/${id}`);
  }

  crearMoto(dto: MotoRequestDTO): Observable<MotoResponseDTO> {
    return this.http.post<MotoResponseDTO>(this.apiUrl, dto);
  }

  actualizarMoto(id: number, dto: MotoRequestDTO): Observable<MotoResponseDTO> {
    return this.http.put<MotoResponseDTO>(`${this.apiUrl}/${id}`, dto);
  }

  eliminarMoto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getMarcas(): Observable<string[]> {
    return this.http.get<string[]>(this.apiMarcas);
  }
}
