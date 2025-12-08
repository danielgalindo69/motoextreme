package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.request.CarritoItemRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CarritoItemResponseDTO;
import com.motoextreme.motoextreme.services.CarritoItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/carrito-items")
@RequiredArgsConstructor
public class CarritoItemController {

    private final CarritoItemService service;

    // Agregar un item al carrito
    @PostMapping("/{carritoId}/agregar")
    public ResponseEntity<CarritoItemResponseDTO> agregarItem(
            @PathVariable Long carritoId,
            @Valid @RequestBody CarritoItemRequestDTO dto) {

        CarritoItemResponseDTO nuevo = service.agregarItemAlCarrito(carritoId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar la cantidad de un item en el carrito
    @PutMapping("/{itemId}")
    public ResponseEntity<CarritoItemResponseDTO> actualizarItem(
            @PathVariable Long itemId,
            @Valid @RequestBody CarritoItemRequestDTO dto) {

        CarritoItemResponseDTO actualizado = service.actualizarItem(itemId, dto);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un item del carrito
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long itemId) {
        service.eliminarItem(itemId);
        return ResponseEntity.noContent().build();
    }
}