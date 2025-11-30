package com.motoextreme.motoextreme.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito_item")
    private Long idCarritoItem;

    @Positive(message = "La cantidad debe ser positiva")
    private int cantidad;

    @Positive(message = "El precio unitario debe ser positivo")
    private Double precioUnitario;

    @Positive(message = "El subtotal debe ser positivo")
    private Double subtotal;

    // Relación con Carrito
    @ManyToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "moto_id", referencedColumnName = "id_moto")
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "accesorio_id", referencedColumnName = "id_accesorio")
    private Accesorio accesorio;
}
