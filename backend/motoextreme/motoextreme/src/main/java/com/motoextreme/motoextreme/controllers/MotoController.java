package com.motoextreme.motoextreme.controllers;


import com.motoextreme.motoextreme.dtos.request.MotoRequestDTO;
import com.motoextreme.motoextreme.dtos.response.MotoResponseDTO;
import com.motoextreme.motoextreme.services.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService service;

    // Obtener todas las motos
    @GetMapping
    public ResponseEntity<List<MotoResponseDTO>> obtenerMotos() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtener moto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<MotoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByid(id));
    }

    // Crear moto
    @PostMapping
    public ResponseEntity<MotoResponseDTO> crearMoto(
            @Valid @RequestBody MotoRequestDTO dto) {

        MotoResponseDTO nueva = service.crearMoto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar moto
    @PutMapping("/{id}")
    public ResponseEntity<MotoResponseDTO> actualizarMoto(@PathVariable Long id, @Valid @RequestBody MotoRequestDTO dto) {

        MotoResponseDTO actualizada = service.actualizarMoto(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar moto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMoto(@PathVariable Long id) {
        service.eliminarMoto(id);
        return ResponseEntity.noContent().build();
    }
}
