import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../service/usuarios/usuarios';
import { UsuarioRequest, UsuarioResponse } from '../service/usuarios/usuarios';

@Component({
  selector: 'app-gestion-usuarios',
  standalone: true,
  templateUrl: './gestion-usuarios.html',
  styleUrls: ['./gestion-usuarios.css'],
  imports: [CommonModule, FormsModule]
})
export class GestionUsuarios implements OnInit {

  usuarios: UsuarioResponse[] = [];

  nuevoUsuario: UsuarioRequest = {
    nombre: '',
    email: '',
    password: '',
    rol: 'ADMIN'
  };

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.usuarioService.listar().subscribe({
      next: (resp) => this.usuarios = resp,
      error: (err) => console.error('Error cargando usuarios', err)
    });
  }

  crearUsuario() {
    this.usuarioService.crear(this.nuevoUsuario).subscribe({
      next: (resp) => {
        this.usuarios.push(resp);
        this.nuevoUsuario = { nombre: '', email: '', password: '', rol: 'ADMIN' };
        this.usuarios = [...this.usuarios];
      },
      error: (err) => console.error('Error creando usuario', err)
    });
  }

  editar(usuario: UsuarioResponse) {
    usuario.editando = true;
  }

  guardar(usuario: UsuarioResponse) {

    const dto: UsuarioRequest = {
      nombre: usuario.nombre,
      email: usuario.email,
      password: usuario.password,
      rol: usuario.rol
    };

    this.usuarioService.actualizar(usuario.idUsuario, dto).subscribe({
      next: (resp) => {
        usuario.editando = false;
        const idx = this.usuarios.findIndex(u => u.idUsuario === resp.idUsuario);
        if (idx > -1) this.usuarios[idx] = resp;
        this.usuarios = [...this.usuarios];
      },
      error: (err) => console.error('Error actualizando usuario', err)
    });
  }

  eliminar(id: number) {
    this.usuarioService.eliminar(id).subscribe({
      next: () => this.usuarios = this.usuarios.filter(u => u.idUsuario !== id),
      error: (err) => console.error('Error eliminando usuario', err)
    });
  }
}
