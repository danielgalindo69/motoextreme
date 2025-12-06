package com.motoextreme.motoextreme.models.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Carrito {

    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoItem> items = new ArrayList<>();
}
