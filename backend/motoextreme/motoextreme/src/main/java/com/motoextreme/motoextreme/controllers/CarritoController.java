package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.request.CarritoItemRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CarritoItemResponseDTO;
import com.motoextreme.motoextreme.dtos.response.CarritoResponseDTO;
import com.motoextreme.motoextreme.models.repositories.ICarrito;
import com.motoextreme.motoextreme.models.repositories.IUsuario;
import com.motoextreme.motoextreme.services.CarritoItemService;
import com.motoextreme.motoextreme.services.CarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    // ============================
    //   OBTENER CARRITO USUARIO
    // ============================
    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> obtenerCarrito(@PathVariable String email) {
        return ResponseEntity.ok(carritoService.obtenerCarritoUsuario(email));
    }

    // ============================
    //        AGREGAR MOTO
    // ============================
    @PostMapping("/{email}/moto/{idMoto}/{cantidad}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> agregarMoto(
            @PathVariable String email,
            @PathVariable Long idMoto,
            @PathVariable int cantidad
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carritoService.agregarMoto(email, idMoto, cantidad));
    }

    // ============================
    //      AGREGAR ACCESORIO
    // ============================
    @PostMapping("/{email}/accesorio/{idAccesorio}/{cantidad}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> agregarAccesorio(
            @PathVariable String email,
            @PathVariable Long idAccesorio,
            @PathVariable int cantidad
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carritoService.agregarAccesorio(email, idAccesorio, cantidad));
    }

    // ============================
    //      ACTUALIZAR CANTIDAD
    // ============================
    @PutMapping("/{email}/item/{idItem}/{cantidad}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> actualizarCantidad(
            @PathVariable String email,
            @PathVariable Long idItem,
            @PathVariable int cantidad
    ) {
        return ResponseEntity.ok(
                carritoService.actualizarCantidad(email, idItem, cantidad)
        );
    }

    // ============================
    //         ELIMINAR ITEM
    // ============================
    @DeleteMapping("/{email}/item/{idItem}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> eliminarItem(
            @PathVariable String email,
            @PathVariable Long idItem
    ) {
        return ResponseEntity.ok(
                carritoService.eliminarItem(email, idItem)
        );
    }

    // ============================
    //         VACIAR CARRITO
    // ============================
    @DeleteMapping("/{email}/vaciar")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> vaciarCarrito(@PathVariable String email) {
        return ResponseEntity.ok(
                carritoService.vaciarCarrito(email)
        );
    }
}


