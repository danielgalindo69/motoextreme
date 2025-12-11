package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.request.CarritoItemRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CarritoItemResponseDTO;
import com.motoextreme.motoextreme.mappers.CarritoItemMapper;
import com.motoextreme.motoextreme.models.entities.Accesorio;
import com.motoextreme.motoextreme.models.entities.Carrito;
import com.motoextreme.motoextreme.models.entities.CarritoItem;
import com.motoextreme.motoextreme.models.entities.Moto;
import com.motoextreme.motoextreme.models.repositories.IAccesorio;
import com.motoextreme.motoextreme.models.repositories.ICarrito;
import com.motoextreme.motoextreme.models.repositories.ICarritoItem;
import com.motoextreme.motoextreme.models.repositories.IMoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoItemService {

    private final ICarrito carritoRepository;
    private final ICarritoItem carritoItemRepository;
    private final IMoto motoRepository;
    private final IAccesorio accesorioRepository; // agrega este repo
    private final CarritoItemMapper mapper;

    public CarritoItemResponseDTO agregarItemAlCarrito(Long carritoId, CarritoItemRequestDTO dto) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if ((dto.getIdMoto() == null && dto.getIdAccesorio() == null) ||
            (dto.getIdMoto() != null && dto.getIdAccesorio() != null)) {
            throw new RuntimeException("Debes enviar idMoto o idAccesorio (solo uno)");
        }

        // Buscar si ya existe el mismo producto en el carrito
        Optional<CarritoItem> existente = carrito.getItems().stream().filter(item -> {
            if (dto.getIdMoto() != null && item.getMoto() != null) {
                return item.getMoto().getIdMoto().equals(dto.getIdMoto());
            }
            if (dto.getIdAccesorio() != null && item.getAccesorio() != null) {
                return item.getAccesorio().getIdAccesorio().equals(dto.getIdAccesorio());
            }
            return false;
        }).findFirst();

        if (existente.isPresent()) {
            CarritoItem item = existente.get();
            item.setCantidad(item.getCantidad() + dto.getCantidad());
            // subtotal se recalcula en @PreUpdate
            CarritoItem guardado = carritoItemRepository.save(item);
            return mapper.toDTO(guardado);
        }

        // Crear nuevo ítem
        CarritoItem nuevo = new CarritoItem();
        nuevo.setCarrito(carrito);
        nuevo.setCantidad(dto.getCantidad());

        if (dto.getIdMoto() != null) {
            Moto moto = motoRepository.findById(dto.getIdMoto())
                    .orElseThrow(() -> new RuntimeException("Moto no encontrada"));
            nuevo.setMoto(moto);
            nuevo.setPrecioUnitario(moto.getPrecio().doubleValue()); // ajusta al campo real
        } else {
            Accesorio acc = accesorioRepository.findById(dto.getIdAccesorio())
                    .orElseThrow(() -> new RuntimeException("Accesorio no encontrado"));
            nuevo.setAccesorio(acc);
            nuevo.setPrecioUnitario(acc.getPrecio()); // ajusta al campo real
        }

        CarritoItem guardado = carritoItemRepository.save(nuevo);

        // Relación inversa
        carrito.getItems().add(guardado);
        carritoRepository.save(carrito);

        return mapper.toDTO(guardado);
    }

    public CarritoItemResponseDTO actualizarItem(Long itemId, CarritoItemRequestDTO dto) {
        CarritoItem item = carritoItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        item.setCantidad(dto.getCantidad());
        CarritoItem guardado = carritoItemRepository.save(item);
        return mapper.toDTO(guardado);
    }

    public void eliminarItem(Long itemId) {
        if (!carritoItemRepository.existsById(itemId)) {
            throw new RuntimeException("Item no existe");
        }
        carritoItemRepository.deleteById(itemId);
    }
}
