import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.html',
  styleUrl: './card.css',
})
export class Card {
  @Input() imagen!: string;
  @Input() titulo!: string;
  @Input() modelo!: any;
  @Input() precio!: number;

  @Input() hp!: string;
  @Input() torque!: string;
  @Input() peso!: string;
}

