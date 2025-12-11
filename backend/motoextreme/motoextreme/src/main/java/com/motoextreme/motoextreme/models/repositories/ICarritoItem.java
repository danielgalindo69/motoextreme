package com.motoextreme.motoextreme.models.repositories;

import com.motoextreme.motoextreme.models.entities.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICarritoItem extends JpaRepository<CarritoItem, Long> {
    List<CarritoItem> findByCarrito_IdCarrito(Long carritoId);

    // Buscar item por carrito + accesorio
    Optional<CarritoItem> findByCarrito_IdCarritoAndAccesorio_IdAccesorio(Long carritoId, Long accesorioId);

    // Buscar item por carrito + moto
    Optional<CarritoItem> findByCarrito_IdCarritoAndMoto_IdMoto(Long carritoId, Long motoId);
}
