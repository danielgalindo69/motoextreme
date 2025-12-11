package com.motoextreme.motoextreme.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CategoriaResponseDTO {
    private Long idCategoria;
    private String nombre;
    private String descripcion;
}
