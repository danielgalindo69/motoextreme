import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccesorioService, AccesorioResponseDTO } from '../../service/accesorios/accesorios';
import { Header } from "../../../../comp_shared/components/header/header";
import { Footer } from "../../../../comp_shared/components/footer/footer";

@Component({
  selector: 'app-accesorios',
  standalone: true,
  imports: [CommonModule, Header, Footer],
  templateUrl: './accesorios.html',
  styleUrls: ['./accesorios.css'] 
})
export class AccesoriosComponent implements OnInit {
  accesorios: AccesorioResponseDTO[] = [];

  constructor(private accesorioService: AccesorioService) {}

  ngOnInit(): void {
    this.accesorioService.getAccesorios().subscribe({
      next: (data) => this.accesorios = data,
      error: (err) => console.error('Error cargando accesorios', err)
    });
  }
}
