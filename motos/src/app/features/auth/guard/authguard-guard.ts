import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/authservice';

// Guard básico: requiere token
export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const token = authService.getToken();
  if (token) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};

// Guard por rol: requiere token y rol específico
export function roleGuard(requiredRole: string): CanActivateFn {
  return (route, state) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    const token = authService.getToken();
    if (!token) {
      router.navigate(['/login']);
      return false;
    }

    try {
      // Decodificar el JWT para obtener el rol
      const payload = JSON.parse(atob(token.split('.')[1]));
      const userRole = payload.rol; // 👈 tu backend mete "rol" en el claim

      // Comparar contra "ADMIN" y "ROLE_ADMIN"
      if (userRole === requiredRole || userRole === `ROLE_${requiredRole}`) {
        return true;
      } else {
        router.navigate(['/']);
        return false;
      }
    } catch (e) {
      console.error('Error decodificando token', e);
      router.navigate(['/login']);
      return false;
    }
  };
}
