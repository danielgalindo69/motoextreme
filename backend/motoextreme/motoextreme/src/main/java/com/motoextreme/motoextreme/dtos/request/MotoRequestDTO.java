package com.motoextreme.motoextreme.dtos.request;
import com.motoextreme.motoextreme.models.enums.Marca;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class MotoRequestDTO {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Marca marca;
    private String modelo;
    private String cilindraje;
    private String potencia;
    private Long idCategoria;
}
