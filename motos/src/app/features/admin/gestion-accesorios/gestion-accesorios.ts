import { Component, OnInit } from '@angular/core';
import { AccesorioService, AccesorioRequest, AccesorioResponse } from '../service/accesorios/accesorios';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-accesorios',
  templateUrl: './gestion-accesorios.html',
  styleUrls: ['./gestion-accesorios.css'],
  imports: [FormsModule, CommonModule],
  standalone: true,
})
export class GestionAccesorios implements OnInit {

  accesorios: (AccesorioResponse & { editando?: boolean, backup?: any })[] = [];
  nuevo: AccesorioRequest = { nombre: '', descripcion: '', precio: 0, idCategoria: 0 };

  constructor(private accesorioService: AccesorioService) {}

  ngOnInit(): void {
    this.cargarAccesorios();
  }

  cargarAccesorios() {
    this.accesorioService.listar().subscribe({
      next: (data) => {
        // Agregamos propiedad editando=false
        this.accesorios = data.map(a => ({ ...a, editando: false }));
      },
      error: (err) => console.error('Error cargando accesorios', err)
    });
  }

  crearAccesorio() {
    this.accesorioService.crear(this.nuevo).subscribe({
      next: (resp) => {
        this.accesorios.push({ ...resp, editando: false });
        this.nuevo = { nombre: '', descripcion: '', precio: 0, idCategoria: 0 };
      },
      error: (err) => console.error('Error creando accesorio', err)
    });
  }

  habilitarEdicion(acc: any) {
    acc.editando = true;
    acc.backup = { ...acc };  // copia de seguridad
  }

  cancelarEdicion(acc: any) {
    Object.assign(acc, acc.backup); // restaura valores originales
    acc.editando = false;
  }

  guardarEdicion(acc: any) {
    const dto = {
      nombre: acc.nombre,
      descripcion: acc.descripcion,
      precio: acc.precio,
      idCategoria: acc.idCategoria
    };

    this.accesorioService.actualizar(acc.idAccesorio, dto).subscribe({
      next: (resp) => {
        Object.assign(acc, resp); // se actualiza con lo que devuelva backend
        acc.editando = false;
      },
      error: (err) => console.error('Error actualizando accesorio', err)
    });
  }

  eliminarAccesorio(id: number) {
    this.accesorioService.eliminar(id).subscribe({
      next: () => this.accesorios = this.accesorios.filter(a => a.idAccesorio !== id),
      error: (err) => console.error('Error eliminando accesor')})}
}
