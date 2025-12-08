package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.request.AccesorioRequestDTO;
import com.motoextreme.motoextreme.dtos.response.AccesorioResponseDTO;
import com.motoextreme.motoextreme.services.AccesorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accesorios")
@RequiredArgsConstructor
public class AccesorioController {

    private final AccesorioService service;

    // Obtener todos los accesorios
    @GetMapping
    public ResponseEntity<List<AccesorioResponseDTO>> obtenerAccesorios() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // Obtener accesorio por id
    @GetMapping("/{id}")
    public ResponseEntity<AccesorioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // Crear accesorio
    @PostMapping
    public ResponseEntity<AccesorioResponseDTO> crearAccesorio(@Valid @RequestBody AccesorioRequestDTO dto) {

        AccesorioResponseDTO nuevo = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar accesorio
    @PutMapping("/{id}")
    public ResponseEntity<AccesorioResponseDTO> actualizarAccesorio(
            @PathVariable Long id,
            @Valid @RequestBody AccesorioRequestDTO dto) {

        AccesorioResponseDTO actualizado = service.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar accesorio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAccesorio(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
