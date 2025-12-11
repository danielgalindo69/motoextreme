import { Routes } from '@angular/router';

// Tus componentes de features
import { Inicio } from './features/home/components/inicio/inicio.component';
import { ModelosComponent } from './features/motos/components/modelos/modelos';
import { AccesoriosComponent } from './features/motos/components/accesorios/accesorios';
import { Login } from './features/auth/components/login/login';
import { Register } from './features/auth/components/register/register';
import { MotoDetalleComponent } from './features/motos/components/modelo-id/modelo-id';
import { UserInicio } from './features/home/components/userinicio/userinicio';
import { CarritoComponent } from './features/carrito/components/carrito/carrito';
import { UserModelos } from './features/motos/components/userModelos/user-modelos/user-modelos';


import { GestionUsuarios } from './features/admin/gestion-usuarios/gestion-usuarios';
import { GestionAccesorios } from './features/admin/gestion-accesorios/gestion-accesorios';
import { GestionModelos } from './features/admin/gestion-modelos/gestion-modelos';


import { authGuard, roleGuard } from '../app/features/auth/guard/authguard-guard';
import { AdminComponent } from './features/admin/admin-panel/admin-panel';
import { UserAccesorios } from './features/motos/components/userAccesorios/user-accesorios/user-accesorios';

export const routes: Routes = [
  { path: '', component: Inicio },
  { path: 'modelos', component: ModelosComponent },
  { path: 'accesorios', component: AccesoriosComponent},
  { path: 'login', component: Login }, 
  { path: 'registrar', component: Register },
  { path: 'user/inicio', component: UserInicio },
  { path: 'user/modelos', component: UserModelos},
  { path: 'user/accesorios', component: UserAccesorios },
  { path: 'user/carrito', component: CarritoComponent },

  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [roleGuard('ADMIN')],
    children: [
      { path: 'usuarios', component: GestionUsuarios },
      { path: 'motos', component: GestionModelos },
      { path: 'accesorios', component:  GestionAccesorios}
    ]
  },

  {
  path: 'modelos/:id',
  component: MotoDetalleComponent
},
 { path: '**', redirectTo: '' }

];
