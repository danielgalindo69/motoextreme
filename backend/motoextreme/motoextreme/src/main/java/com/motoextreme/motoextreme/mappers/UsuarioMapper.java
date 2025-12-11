package com.motoextreme.motoextreme.mappers;

import com.motoextreme.motoextreme.dtos.request.UsuarioRequestDTO;
import com.motoextreme.motoextreme.dtos.response.UsuarioResponseDTO;
import com.motoextreme.motoextreme.models.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    @Mapping(source = "carrito.idCarrito", target = "idCarrito")
    UsuarioResponseDTO toDto(Usuario usuario);
}