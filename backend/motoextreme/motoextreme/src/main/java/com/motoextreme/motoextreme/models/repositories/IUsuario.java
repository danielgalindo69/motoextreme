package com.motoextreme.motoextreme.models.repositories;

import com.motoextreme.motoextreme.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuario extends JpaRepository<Usuario, Long> {
    //metodo para buscar por email
    Optional<Usuario> findByEmail(String email);
}
