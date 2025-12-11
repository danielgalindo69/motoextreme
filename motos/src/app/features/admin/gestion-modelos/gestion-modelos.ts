import { Component, OnInit } from '@angular/core';
import { MotoService } from '../service/modelos/modelos';
import { MotoRequest, MotoResponse } from '../service/modelos/modelos';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gestion-motos',
  templateUrl: './gestion-modelos.html',
  imports: [FormsModule, CommonModule],
  standalone: true,
  styleUrls: ['./gestion-modelos.css']
})
export class GestionModelos implements OnInit {

  motos: MotoResponse[] = [];

  // 🔥 ESTA ES LA VARIABLE QUE FALTABA
  nuevaMoto: MotoRequest = {
    nombre: '',
    descripcion: '',
    precio: 0,
    marca: '',
    modelo: '',
    cilindraje: '',
    potencia: '',
    idCategoria: 0
  };

  constructor(private motoService: MotoService) {}

  ngOnInit(): void {
    this.cargarMotos();
  }

  cargarMotos() {
    this.motoService.listar().subscribe({
      next: (resp) => this.motos = resp,
      error: (err) => console.error('Error cargando motos', err)
    });
  }

  crearMoto() {
    this.motoService.crear(this.nuevaMoto).subscribe({
      next: (resp) => {
        this.motos.push(resp);
        this.motos = [...this.motos];

        // 🔥 REINICIAR FORMULARIO
        this.nuevaMoto = {
          nombre: '',
          descripcion: '',
          precio: 0,
          marca: '',
          modelo: '',
          cilindraje: '',
          potencia: '',
          idCategoria: 0
        };
      },
      error: (err) => console.error('Error creando moto', err)
    });
  }

  guardarEdicionMoto(moto: MotoResponse) {
    const dto: MotoRequest = {
      nombre: moto.nombre,
      descripcion: moto.descripcion,
      precio: moto.precio,
      marca: moto.marca,
      modelo: moto.modelo,
      cilindraje: moto.cilindraje,
      potencia: moto.potencia,
      idCategoria: moto.idCategoria
    };

    this.motoService.actualizar(moto.idMoto, dto).subscribe({
      next: (resp) => {
        const idx = this.motos.findIndex(m => m.idMoto === resp.idMoto);
        if (idx > -1) {
          this.motos[idx] = { ...resp, editando: false };
        }
      },
      error: (err) => console.error('Error actualizando moto', err)
    });
  }

  eliminarMoto(id: number) {
    this.motoService.eliminar(id).subscribe({
      next: () => {
        this.motos = this.motos.filter(m => m.idMoto !== id);
      },
      error: (err) => console.error('Error eliminando moto', err)
    });
  }
}
