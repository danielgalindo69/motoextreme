package com.motoextreme.motoextreme.dtos.response;

import lombok.Data;

@Data
public class AccesorioResponseDTO {

    private Long idAccesorio;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long idCategoria;
}