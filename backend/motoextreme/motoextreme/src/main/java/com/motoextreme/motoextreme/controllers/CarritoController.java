package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.response.CarritoResponseDTO;
import com.motoextreme.motoextreme.services.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carritos")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService service;

    // Obtener carrito de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoResponseDTO> obtenerCarritoPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.obtenerCarritoPorUsuario(usuarioId));
    }

    // Crear un carrito para un usuario (si no existe)
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoResponseDTO> crearCarrito(@PathVariable Long usuarioId) {
        CarritoResponseDTO nuevo = service.crearCarritoParaUsuario(usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Obtener carrito por ID
    @GetMapping("/{carritoId}")
    public ResponseEntity<CarritoResponseDTO> obtenerCarrito(@PathVariable Long carritoId) {
        return ResponseEntity.ok(service.obtenerCarrito(carritoId));
    }

    // Vaciar carrito
    @DeleteMapping("/{carritoId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long carritoId) {
        service.vaciarCarrito(carritoId);
        return ResponseEntity.noContent().build();
    }
}
