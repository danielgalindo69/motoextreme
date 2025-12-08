package com.motoextreme.motoextreme.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccesorioRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    private Double precio;

    @NotNull(message = "La categoría es obligatoria")
    private Long idCategoria;
}
