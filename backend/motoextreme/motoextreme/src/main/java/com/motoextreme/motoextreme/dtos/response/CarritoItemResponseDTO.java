package com.motoextreme.motoextreme.dtos.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarritoItemResponseDTO {
    private Long id;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private Long motoId;
    private Long accesorioId;
}