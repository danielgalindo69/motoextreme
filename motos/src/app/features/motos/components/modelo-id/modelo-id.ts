import { Component, Input, OnInit } from '@angular/core';
import { MotoService, MotoResponseDTO } from '../../service/modelos/modelos';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-moto-detalle',
  templateUrl: './modelo-id.html',
  standalone: true,
  imports: [CommonModule],
  styleUrl: './modelo-id.css',
})

export class MotoDetalleComponent implements OnInit {

  @Input() idMoto!: number;
  moto: MotoResponseDTO | null = null;

  constructor(
    private motoService: MotoService
  ) {}

  ngOnInit(): void {
    if (this.idMoto){
      this.motoService.getMotoById(this.idMoto).subscribe({
        next: (data) => this.moto = data,
        error: (err) => console.error('Error cargando moto por ID', err)
    });
  }
    }
}
