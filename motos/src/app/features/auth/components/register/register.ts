import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService} from '../../services/authservice';
import { RegisterRequest } from '../../interface/register-request';
import { Router, RouterLink } from '@angular/router';
import { Header } from "../../../../comp_shared/components/header/header";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, Header],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class Register {

  registerForm: FormGroup;

  constructor(private authService: AuthService, private router: Router) {
    this.registerForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      fechaNa: new FormControl('')
    });
  }

  onRegister() {
    if (this.registerForm.valid) {
      const request: RegisterRequest = {
        nombre: this.registerForm.value.username,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password
      };

      this.authService.register(request).subscribe({
        next: (resp) => {
          this.authService.saveToken(resp.token);
          this.router.navigate(['/']); // redirige al inicio
        },
        error: (err) => {
          console.error('Error en registro', err);
        }
      });
    }
  }
}
