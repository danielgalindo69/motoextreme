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

    @Autowired
    private CategoriaService service;


    //traer categorias
    @GetMapping
  public ResponseEntity<List<CategoriaResponseDTO>> buscarCategoriaPorId() {
     return ResponseEntity.ok(service.traerCategorias());
    }

    //crear categoria
    @PostMapping
   public ResponseEntity<CategoriaResponseDTO> crearCategoria(@Valid @RequestBody CategoriaRequestDTO categoria){
        CategoriaResponseDTO nuevaCategoria = service.crearCategoria(categoria);
        if (nuevaCategoria != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //borrar categorias
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
        service.borrarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    //actualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO categoriaDTO){
        Optional<CategoriaResponseDTO> categoriaOpt = service.buscarCategoriaPorId(id);
        if (categoriaOpt.isPresent()){
            CategoriaResponseDTO categoriaActualizada = service.crearCategoria(categoriaDTO);
            return ResponseEntity.ok().body(categoriaActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //buscar categoria por id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarCAtegoriaporId(@PathVariable Long id){
        Optional<CategoriaResponseDTO> categoriaOpt = service.buscarCategoriaPorId(id);
        return categoriaOpt
                .map(categoria -> ResponseEntity.ok().body(categoria))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
