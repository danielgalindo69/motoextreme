package com.motoextreme.motoextreme.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CarritoItemRequestDTO {
    // Enviar uno de los dos: idMoto o idAccesorio
    private Long idMoto;
    private Long idAccesorio;

    @NotNull
    @Positive
    private Integer cantidad;
}
