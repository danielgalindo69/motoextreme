export interface RegisterRequest {
  nombre: string;
  email: string;
  password: string;
  rol?: string; // opcional
}
