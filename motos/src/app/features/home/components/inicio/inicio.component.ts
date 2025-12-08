import { Component } from '@angular/core';
import { Card } from "../../../../components/card/card";
import { CommonModule } from '@angular/common';
import { InfoCard } from "../../../../components/info-card/info-card";

@Component({
  selector: 'app-inicio',
  imports: [Card, CommonModule, InfoCard],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class Inicio {
  motos = [
  {
    imagen: 'assets/moto.jpg',
    titulo: 'DEPORTIVA',
    modelo: 'Thunder',
    precio: 18.990,
    hp: '200 HP',
    torque: '115 Nm',
    peso: '201 Kg'
  },
  {
    imagen: 'assets/moto3.jpg',
    titulo: 'ADVENTURE',
    modelo: 'Adventure X',
    precio: 16.490,
    hp: '136 HP',
    torque: '143 Nm',
    peso: '244 Kg'
  },
  {
    imagen: 'assets/moto2.jpg',
    titulo: 'CRUISER',
    modelo: 'Shadow Cruiser',
    precio: 14.990,
    hp: '86 HP',
    torque: '108 Nm',
    peso: '305 Kg'
  }
];

info = [
  {
    svg: 'assets/phone.svg',
    titulo: 'Conectividad Total',
    descripcion: 'Sistema de navegación integrado, control por voz y conexión con tu smartphone'
  },
  {
    svg: 'assets/shield.svg',
    titulo: 'Mantenimiento Premium',
    descripcion: '3 años de mantenimiento gratuito en nuestra red oficial de concesionarios'
  },
  {
    svg: 'assets/wrench.svg',
    titulo: 'Seguridad Avanzada',
    descripcion: '3 años de mantenimiento gratuito en nuestra red oficial de concesionariosABS de última generación, control de tracción y modos de conducción inteligentes'
  },
  {
    svg: 'assets/headphones.svg',
    titulo: 'Soporte 24/7',
    descripcion: 'Asistencia en carretera las 24 horas y asesoramiento técnico personalizado'
  }
];
}
