package com.motoextreme.motoextreme.mappers;
import com.motoextreme.motoextreme.dtos.request.MotoRequestDTO;
import com.motoextreme.motoextreme.dtos.response.MotoResponseDTO;
import com.motoextreme.motoextreme.models.entities.Moto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MotoMapper {

    // De DTO → Entidad (sin categoría, eso lo hace el service)
    @Mapping(target = "idMoto", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Moto toEntity(MotoRequestDTO dto);

    // De Entidad → DTO (devuelve solo el ID de la categoría)
    @Mapping(source = "categoria.idCategoria", target = "idCategoria")
    MotoResponseDTO toDto(Moto entity);

    List<MotoResponseDTO> toDtoList(List<Moto> motos);
}

