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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoItemService {

    private final ICarrito carritoRepository;
    private final ICarritoItem carritoItemRepository;
    private final IMoto motoRepository;

    private final CarritoItemMapper mapper;

    // Agregar una moto al carrito
    public CarritoItemResponseDTO agregarItemAlCarrito(Long carritoId, CarritoItemRequestDTO dto) {

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Moto moto = motoRepository.findById(dto.getIdMoto())
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));

        // Verificar si la moto ya está en el carrito —> sumar cantidad
        Optional<CarritoItem> existente = carrito.getItems().stream()
                .filter(item -> item.getMoto().getIdMoto().equals(dto.getIdMoto()))
                .findFirst();

        if (existente.isPresent()) {
            CarritoItem item = existente.get();
            item.setCantidad(item.getCantidad() + dto.getCantidad());
            CarritoItem guardado = carritoItemRepository.save(item);
            return mapper.toDTO(guardado);
        }

        // Si no existe, crear un nuevo item
        CarritoItem nuevo = new CarritoItem();
        nuevo.setCarrito(carrito);
        nuevo.setMoto(moto);
        nuevo.setCantidad(dto.getCantidad());

        CarritoItem guardado = carritoItemRepository.save(nuevo);

        carrito.getItems().add(guardado);
        carritoRepository.save(carrito);

        return mapper.toDTO(guardado);
    }

    // Actualizar cantidad de un item
    public CarritoItemResponseDTO actualizarItem(Long itemId, CarritoItemRequestDTO dto) {

        CarritoItem item = carritoItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        item.setCantidad(dto.getCantidad());

        CarritoItem guardado = carritoItemRepository.save(item);

        return mapper.toDTO(guardado);
    }

    // Eliminar un item del carrito
    public void eliminarItem(Long itemId) {

        if (!carritoItemRepository.existsById(itemId)) {
            throw new RuntimeException("Item no existe");
        }

        carritoItemRepository.deleteById(itemId);
    }
}
