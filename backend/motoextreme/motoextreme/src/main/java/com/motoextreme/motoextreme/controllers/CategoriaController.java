package com.motoextreme.motoextreme.controllers;

import com.motoextreme.motoextreme.models.entities.Categoria;
import com.motoextreme.motoextreme.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> getcategorias(){
        return  ResponseEntity.ok(service.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(service.crearCategoria(categoria));
    }
}
