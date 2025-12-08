package com.motoextreme.motoextreme.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarritoResponseDTO {
    private Long idCarrito;
    private Long idUsuario;
    private BigDecimal total;
    private List<CarritoItemResponseDTO> items;
}