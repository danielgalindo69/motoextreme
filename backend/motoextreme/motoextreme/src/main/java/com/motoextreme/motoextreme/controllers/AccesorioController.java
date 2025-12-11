package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.request.AccesorioRequestDTO;
import com.motoextreme.motoextreme.dtos.response.AccesorioResponseDTO;
import com.motoextreme.motoextreme.services.AccesorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/accesorios")
@RequiredArgsConstructor
public class AccesorioController {

    private final AccesorioService service;

    // GET — público
    @GetMapping
    public ResponseEntity<List<AccesorioResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // GET por id — público
    @GetMapping("/{id}")
    public ResponseEntity<AccesorioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // POST — ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccesorioResponseDTO> crear(@Valid @RequestBody AccesorioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    // PUT — ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccesorioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AccesorioRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    // DELETE — ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


