package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.request.AccesorioRequestDTO;
import com.motoextreme.motoextreme.dtos.response.AccesorioResponseDTO;
import com.motoextreme.motoextreme.services.AccesorioService;

import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping
    public ResponseEntity<List<AccesorioResponseDTO>> obtenerAccesorios() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccesorioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }


    // Crear accesorio
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccesorioResponseDTO> crearAccesorio(@Valid @RequestBody AccesorioRequestDTO dto) {

        AccesorioResponseDTO nuevo = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
public ResponseEntity<AccesorioResponseDTO> actualizarAccesorio(
        @PathVariable Long id,
        @Valid @RequestBody AccesorioRequestDTO dto,
        HttpServletRequest request) {

    System.out.println(">>> Método llamado desde: " + request.getRemoteAddr() +
                       " - " + request.getMethod() +
                       " - " + System.currentTimeMillis());

    System.out.println("Actualizando accesorio con id " + id + " y dto: " + dto);
    AccesorioResponseDTO actualizado = service.actualizar(id, dto);
    System.out.println(">>> Finalizó correctamente");
    return ResponseEntity.ok(actualizado);
}



    // Eliminar accesorio
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarAccesorio(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
