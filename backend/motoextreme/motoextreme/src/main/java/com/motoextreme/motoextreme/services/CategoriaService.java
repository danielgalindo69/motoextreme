package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.request.CategoriaRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CategoriaResponseDTO;
import com.motoextreme.motoextreme.mappers.CategoriaMapper;
import com.motoextreme.motoextreme.models.entities.Categoria;
import com.motoextreme.motoextreme.models.repositories.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private ICategoria repo;
    @Autowired
    private CategoriaMapper mapper;


    //traer los datos de las categorias
    public List<CategoriaResponseDTO> traerCategorias() {
        List<Categoria> categorias = repo.findAll();
        return mapper.toResponseDTOList(categorias);
    }

    //crear categoria
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO categoriaDTO) {

            Categoria categoria = mapper.toEntity(categoriaDTO);
            Categoria categoriaGuardada = repo.save(categoria);
            return mapper.toCategoriaDto(categoriaGuardada);
    }


    //borar categoria
    public void borrarCategoria(Long id) {
        repo.deleteById(id);
    }

    //buscar categoria por id
    public Optional<CategoriaResponseDTO> buscarCategoriaPorId(Long id) {
        Optional<Categoria> categoria = repo.findById(id);
        return categoria.map(mapper::toCategoriaDto);

    }

    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaRequestDTO categoriaDTO) {

        Categoria categoria = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria categoriaActualizada = repo.save(categoria);

        return new CategoriaResponseDTO(
                categoriaActualizada.getIdCategoria(),
                categoriaActualizada.getNombre(),
                categoriaActualizada.getDescripcion()
        );
    }

}



