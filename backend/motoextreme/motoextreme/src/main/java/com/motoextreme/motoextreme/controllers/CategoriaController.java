package com.motoextreme.motoextreme.controllers;
import org.springframework.security.access.prepost.PreAuthorize;

import com.motoextreme.motoextreme.dtos.request.CategoriaRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CategoriaResponseDTO;
import com.motoextreme.motoextreme.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@PreAuthorize(("hasRole('ADMIN')"))
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // Obtener todas las categorías
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CategoriaResponseDTO>> obtenerCategorias() {
        return ResponseEntity.ok(service.traerCategorias());
    }

    // Crear categoría
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(
            @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {

        CategoriaResponseDTO creada = service.crearCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // Obtener categoría por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return service.buscarCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaResponseDTO> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {

        CategoriaResponseDTO actualizada = service.actualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar categoría
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        service.borrarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
