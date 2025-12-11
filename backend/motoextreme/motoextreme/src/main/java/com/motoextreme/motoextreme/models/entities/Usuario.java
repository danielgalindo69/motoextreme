package com.motoextreme.motoextreme.models.entities;

import com.motoextreme.motoextreme.models.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    private String email;
    private String password;

    @NotNull (message = "El rol no puede estar vacio")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carrito carrito;

    private boolean enabled = true;
}
