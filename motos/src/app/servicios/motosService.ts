import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})
export class MotosService {
  private apiUrl = 'http://localhost:9595/api/motos';

  constructor(private http: HttpClient) {}

  getMotos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
