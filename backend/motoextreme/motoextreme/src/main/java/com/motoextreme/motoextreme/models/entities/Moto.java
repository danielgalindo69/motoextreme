package com.motoextreme.motoextreme.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.motoextreme.motoextreme.models.enums.Marca;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "moto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Moto {

    @Column(name = "id_moto") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    private String descripcion;

    @Positive(message = "El precio debe ser un valor positivo")
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "La marca no puede estar vacia")
    @Enumerated(EnumType.STRING)
    private Marca marca;

    @NotBlank(message = "Los datos no pueden estar vacios")
    private String modelo;
    private String cilindraje;
    private String potencia;

    @PositiveOrZero
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id",referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;
}
