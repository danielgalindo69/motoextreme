package com.motoextreme.motoextreme.mappers;

import com.motoextreme.motoextreme.dtos.request.AccesorioRequestDTO;
import com.motoextreme.motoextreme.dtos.response.AccesorioResponseDTO;
import com.motoextreme.motoextreme.models.entities.Accesorio;
import com.motoextreme.motoextreme.models.entities.Categoria;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccesorioMapper {

    private final CategoriaMapper categoriaMapper; // solo si lo necesitas para versiones extendidas


    public Accesorio toEntity(AccesorioRequestDTO dto, Categoria categoria) {

        if (dto == null) return null;

        Accesorio accesorio = new Accesorio();
        accesorio.setNombre(dto.getNombre());
        accesorio.setDescripcion(dto.getDescripcion());
        accesorio.setPrecio(dto.getPrecio());
        accesorio.setCategoria(categoria);

        return accesorio;
    }

    public AccesorioResponseDTO toDTO(Accesorio accesorio) {

        if (accesorio == null) return null;

        AccesorioResponseDTO dto = new AccesorioResponseDTO();
        dto.setIdAccesorio(accesorio.getIdAccesorio());
        dto.setNombre(accesorio.getNombre());
        dto.setDescripcion(accesorio.getDescripcion());
        dto.setPrecio(accesorio.getPrecio());

        if (accesorio.getCategoria() != null) {
            dto.setIdCategoria(accesorio.getCategoria().getIdCategoria());
        }

        return dto;
    }

    public List<AccesorioResponseDTO> toDTOList(List<Accesorio> accesorios) {
        return accesorios.stream()
                .map(this::toDTO)
                .toList();
    }

    private AccesorioResponseDTO mapToResponse(Accesorio accesorio) {

        AccesorioResponseDTO dto = new AccesorioResponseDTO();

        dto.setIdAccesorio(accesorio.getIdAccesorio());
        dto.setNombre(accesorio.getNombre());
        dto.setDescripcion(accesorio.getDescripcion());
        dto.setPrecio(accesorio.getPrecio());
        dto.setStock(accesorio.getStock());

        return dto;
    }

}