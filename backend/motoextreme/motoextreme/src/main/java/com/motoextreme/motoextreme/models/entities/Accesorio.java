package com.motoextreme.motoextreme.models.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Accesorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accesorio")
    private Long idAccesorio;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    private String descripcion;

    private Double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id",referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;
}
