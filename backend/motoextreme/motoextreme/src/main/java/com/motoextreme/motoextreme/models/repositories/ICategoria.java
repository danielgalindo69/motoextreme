package com.motoextreme.motoextreme.models.repositories;

import com.motoextreme.motoextreme.models.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoria extends JpaRepository<Categoria, Long> {
}
