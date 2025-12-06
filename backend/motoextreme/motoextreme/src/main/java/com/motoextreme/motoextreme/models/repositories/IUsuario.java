package com.motoextreme.motoextreme.models.repositories;

import com.motoextreme.motoextreme.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuario extends JpaRepository<Usuario, Long> {
}
