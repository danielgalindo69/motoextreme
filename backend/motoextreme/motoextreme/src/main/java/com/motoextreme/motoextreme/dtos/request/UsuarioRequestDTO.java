package com.motoextreme.motoextreme.dtos.request;

import com.motoextreme.motoextreme.models.enums.Rol;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class UsuarioRequestDTO {
    private String nombre;

    @Email
    private String email;

    private String password;
    private Rol rol;
}
