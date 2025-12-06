package com.motoextreme.motoextreme.dtos.request;

import com.motoextreme.motoextreme.models.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    private String email;
    private String password;
    private Rol rol;

}
