package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.request.CategoriaRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CategoriaResponseDTO;
import com.motoextreme.motoextreme.exeptions.ResourceNotFoundExeption;
import com.motoextreme.motoextreme.mappers.CategoriaMapper;
import com.motoextreme.motoextreme.models.entities.Categoria;
import com.motoextreme.motoextreme.models.repositories.ICategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final ICategoria repo;
    private final CategoriaMapper mapper;

    // Traer categorias
    public List<CategoriaResponseDTO> traerCategorias() {
        List<Categoria> categorias = repo.findAll();
        return mapper.toResponseDTOList(categorias);
    }

    // Crear categoria
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO categoriaDTO) {
        Categoria categoria = mapper.toEntity(categoriaDTO);
        Categoria categoriaGuardada = repo.save(categoria);
        return mapper.toCategoriaDto(categoriaGuardada);
    }

    // Borrar categoria
    public void borrarCategoria(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundExeption("Categoría no encontrada");
        }
        repo.deleteById(id);
    }

    // Buscar categoria por id
    public Optional<CategoriaResponseDTO> buscarCategoriaPorId(Long id) {
        Optional<Categoria> categoria = repo.findById(id);
        return categoria.map(mapper::toCategoriaDto);
    }

    // Actualizar categoria
    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaRequestDTO categoriaDTO) {
        Categoria categoria = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Categoría no encontrada"));

        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria categoriaActualizada = repo.save(categoria);
        return mapper.toCategoriaDto(categoriaActualizada);
    }
}




