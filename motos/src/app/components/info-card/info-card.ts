import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-info-card',
  imports: [],
  templateUrl: './info-card.html',
  styleUrl: './info-card.css',
})
export class InfoCard {
  @Input() svg!: string;
  @Input() titulo!: string;
  @Input() descripcion!: string;
}
