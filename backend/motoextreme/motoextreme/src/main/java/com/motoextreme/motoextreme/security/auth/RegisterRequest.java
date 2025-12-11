package com.motoextreme.motoextreme.security.auth;


import com.motoextreme.motoextreme.models.enums.Rol;
import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre;
    private String email;
    private String password;
    private Rol rol;  // ADMIN o USER
}
