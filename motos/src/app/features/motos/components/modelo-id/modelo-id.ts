import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  moto: MotoResponseDTO | null = null;

  constructor(
    private route: ActivatedRoute,
    private motoService: MotoService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.motoService.getMotoById(id).subscribe({
      next: (data) => this.moto = data,
      error: (err) => console.error('Error cargando moto por ID', err)
    });
  }
}
