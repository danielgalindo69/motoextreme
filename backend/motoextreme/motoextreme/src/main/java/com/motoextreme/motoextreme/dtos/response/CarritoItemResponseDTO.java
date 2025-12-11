package com.motoextreme.motoextreme.dtos.response;

import lombok.Data;

@Data
public class CarritoItemResponseDTO {
    private Long idItem;
    private String tipo;           // "MOTO" o "ACCESORIO"
    private Long referenciaId;     // idMoto o idAccesorio
    private String nombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
