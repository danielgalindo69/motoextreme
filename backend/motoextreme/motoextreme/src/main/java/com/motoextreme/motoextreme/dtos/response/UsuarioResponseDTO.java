package com.motoextreme.motoextreme.dtos.response;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String rol;
    private Long idCarrito; // id del carrito asociado
}
