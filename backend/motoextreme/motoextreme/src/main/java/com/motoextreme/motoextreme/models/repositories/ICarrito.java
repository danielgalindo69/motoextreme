package com.motoextreme.motoextreme.models.repositories;

import com.motoextreme.motoextreme.models.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarrito extends JpaRepository<Carrito, Long> {
}
