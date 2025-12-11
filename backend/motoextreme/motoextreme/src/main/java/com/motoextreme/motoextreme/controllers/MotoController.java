package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.request.MotoRequestDTO;
import com.motoextreme.motoextreme.dtos.response.MotoResponseDTO;
import com.motoextreme.motoextreme.services.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService service;

    // GET — público
    @GetMapping
    public ResponseEntity<List<MotoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET por id — público
    @GetMapping("/{id}")
    public ResponseEntity<Optional<MotoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByid(id));
    }

    // POST — ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MotoResponseDTO> crear(@Valid @RequestBody MotoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearMoto(dto));
    }

    // PUT — ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MotoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MotoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizarMoto(id, dto));
    }

    // DELETE — ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarMoto(id);
        return ResponseEntity.noContent().build();
    }
}


