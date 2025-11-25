import { Routes } from '@angular/router';
import { Inicio } from './pages/inicio/inicio';
import { Modelos } from './pages/modelos/modelos';
import { Configurador } from './pages/configurador/configurador';
import { Accesorios } from './pages/accesorios/accesorios';
import { Contacto } from './pages/contacto/contacto';

export const routes: Routes = [
  {path: '', component: Inicio},
  {path: 'modelos', component: Modelos},
  {path: 'configurador', component: Configurador},
  {path: 'accesorios', component: Accesorios},
  {path: 'contacto', component: Contacto}
];
