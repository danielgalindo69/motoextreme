package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.dtos.request.CategoriaRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CategoriaResponseDTO;
import com.motoextreme.motoextreme.models.entities.Categoria;
import com.motoextreme.motoextreme.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> obtenerCategorias() {
        return ResponseEntity.ok(service.traerCategorias());
    }

    // Crear categoría
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(
            @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {

        CategoriaResponseDTO creada = service.crearCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // Obtener categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return service.buscarCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {

        CategoriaResponseDTO actualizada = service.actualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        service.borrarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
