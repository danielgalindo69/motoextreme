package com.motoextreme.motoextreme.models.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "accesorio")
public class Accesorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accesorio")
    private Long idAccesorio;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    private String descripcion;

    @PositiveOrZero(message = "El precio debe ser un valor positivo")
    @Column(precision = 10, scale = 2)
    private BigDecimal precio = BigDecimal.ZERO;

    @PositiveOrZero
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id",referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;
}
