import { Routes } from '@angular/router';
import { Inicio } from './features/home/components/inicio/inicio.component';
import { Modelos } from './features/motos/components/modelos/modelos';
import { Accesorios } from './features/motos/components/accesorios/accesorios';
import { Contacto } from './features/contact/components/contacto/contacto';
import { Login } from './features/auth/components/login/login';
import { Register } from './features/auth/components/register/register';

export const routes: Routes = [
  {path: '', component: Inicio},
  {path: 'modelos', component: Modelos},
  {path: 'accesorios', component: Accesorios},
  {path: 'contacto', component: Contacto},
  {path: 'login', component: Login},
  {path: 'registrar', component: Register}
];
