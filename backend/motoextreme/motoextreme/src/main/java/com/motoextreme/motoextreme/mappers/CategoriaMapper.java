package com.motoextreme.motoextreme.mappers;

import com.motoextreme.motoextreme.dtos.request.CategoriaRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CategoriaResponseDTO;
import com.motoextreme.motoextreme.models.entities.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    //convertir de respnsse dto a entidad y viceversa
    CategoriaResponseDTO toCategoriaDto(Categoria categoria);

    //para traer los datos en lista
    List<CategoriaResponseDTO> toResponseDTOList(List<Categoria> categorias);

    //convertir de dto a entidad
    Categoria toEntity(CategoriaRequestDTO categoriaRequestDTO);
}
