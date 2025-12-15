import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/authservice';
import { LoginRequest } from '../../interface/login';
import { Header } from "../../../../comp_shared/components/header/header";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  loginForm: FormGroup;

  constructor(private authService: AuthService, private router: Router) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });
  }

  onLogin() {
    if (this.loginForm.valid) {
      const request: LoginRequest = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      };

      this.authService.login(request).subscribe({
        next: (resp) => {
          // Guardar token
          const token = resp.token;
          localStorage.setItem('token', token);

          // Decodificar payload del JWT
          const payload = JSON.parse(atob(token.split('.')[1]));
          const rol = payload.rol; // viene de JwtService

          // Redirigir según rol
        if (rol === 'ROLE_ADMIN' || rol === 'ADMIN') {
          this.router.navigate(['/admin/usuarios']);
        } else {
          this.router.navigate(['/user/inicio']);
        }

        },
        error: (err) => {
          console.error('Error en login', err);
        }
      });
    }
  }
}
