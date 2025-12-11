package com.motoextreme.motoextreme.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito_item")
    private Long idCarritoItem;

    @Positive(message = "La cantidad debe ser positiva")
    private int cantidad;

    @PositiveOrZero(message = "El precio unitario debe ser positivo")
    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario = BigDecimal.ZERO;

    @PositiveOrZero(message = "El subtotal debe ser positivo")
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    // Relación con Carrito
    @ManyToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "moto_id", referencedColumnName = "id_moto")
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "accesorio_id", referencedColumnName = "id_accesorio")
    private Accesorio accesorio;
}
