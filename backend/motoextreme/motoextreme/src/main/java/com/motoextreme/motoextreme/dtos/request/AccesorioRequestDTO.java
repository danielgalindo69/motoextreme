package com.motoextreme.motoextreme.dtos.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccesorioRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "La categoría es obligatoria")
    private Long idCategoria;
}
