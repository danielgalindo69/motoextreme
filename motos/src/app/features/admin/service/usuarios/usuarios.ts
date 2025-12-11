import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UsuarioResponse {
  idUsuario: number;
  nombre: string;
  email: string;
  password: string;
  rol: string;
  enabled: boolean;

  editando?: boolean; // 👈 Para mostrar inputs de edición
}

export interface UsuarioRequest {
  nombre: string;
  email: string;
  password: string;
  rol: string;
}


@Injectable({ providedIn: 'root' })
export class UsuarioService {

  private url = 'http://localhost:9595/usuarios';

  constructor(private http: HttpClient) {}

  listar(): Observable<UsuarioResponse[]> {
    return this.http.get<UsuarioResponse[]>(this.url);
  }

  crear(dto: UsuarioRequest): Observable<UsuarioResponse> {
    return this.http.post<UsuarioResponse>(this.url, dto);
  }

  actualizar(id: number, dto: UsuarioRequest): Observable<UsuarioResponse> {
    return this.http.put<UsuarioResponse>(`${this.url}/${id}`, dto);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
