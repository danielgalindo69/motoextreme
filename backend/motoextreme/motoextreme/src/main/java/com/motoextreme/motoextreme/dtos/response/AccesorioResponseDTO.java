package com.motoextreme.motoextreme.dtos.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccesorioResponseDTO {
    private Long idAccesorio;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Long idCategoria;
    private int stock;
}