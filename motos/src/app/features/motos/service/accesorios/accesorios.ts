import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AccesorioResponseDTO {
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
  private apiUrl = 'http://localhost:9595/accesorios';

  constructor(private http: HttpClient) {}

  getAccesorios(): Observable<AccesorioResponseDTO[]> {
    return this.http.get<AccesorioResponseDTO[]>(this.apiUrl);
  }
}
