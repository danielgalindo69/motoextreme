package com.motoextreme.motoextreme.models.repositories;

import com.motoextreme.motoextreme.models.entities.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarritoItem extends JpaRepository<CarritoItem, Long> {
    List<CarritoItem> findByCarritoId(Long carritoId);
}
