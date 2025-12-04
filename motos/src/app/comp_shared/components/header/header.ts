import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterModule],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  menuOpen: boolean = false;

  desplegableMenu(){
    this.menuOpen = !this.menuOpen
  }
}
