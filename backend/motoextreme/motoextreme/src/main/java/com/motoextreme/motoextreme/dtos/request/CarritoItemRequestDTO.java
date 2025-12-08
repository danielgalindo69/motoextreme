package com.motoextreme.motoextreme.dtos.request;

import lombok.Data;

@Data
public class CarritoItemRequestDTO {
    private Long idMoto;
    private Long idAccesorio;
    private int cantidad;
}
