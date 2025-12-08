package com.motoextreme.motoextreme.dtos.response;

import lombok.Data;

@Data
public class CarritoItemResponseDTO {

    private Long id;
    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;

    private Long motoId;
    private Long accesorioId;
}