package com.motoextreme.motoextreme.models.repositories;

import com.motoextreme.motoextreme.models.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMoto extends JpaRepository<Moto, Long> {
}
