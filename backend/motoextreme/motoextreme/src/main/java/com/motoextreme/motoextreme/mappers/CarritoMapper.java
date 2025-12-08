package com.motoextreme.motoextreme.mappers;

import com.motoextreme.motoextreme.dtos.response.CarritoResponseDTO;
import com.motoextreme.motoextreme.models.entities.Carrito;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CarritoMapper {

    private final CarritoItemMapper carritoItemMapper;

    public CarritoResponseDTO toDTO(Carrito carrito) {

        if (carrito == null) return null;

        CarritoResponseDTO dto = new CarritoResponseDTO();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setIdUsuario(carrito.getUsuario().getIdUsuario());
        dto.setTotal(carrito.getTotal() != null ? java.math.BigDecimal.valueOf(carrito.getTotal()) : java.math.BigDecimal.ZERO);

        dto.setItems(
                carrito.getItems()
                        .stream()
                        .map(carritoItemMapper::toDTO)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}