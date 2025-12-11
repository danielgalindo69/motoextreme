package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.request.CarritoItemRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CarritoItemResponseDTO;
import com.motoextreme.motoextreme.exeptions.BadRequestException;
import com.motoextreme.motoextreme.exeptions.ResourceNotFoundExeption;
import com.motoextreme.motoextreme.mappers.CarritoItemMapper;
import com.motoextreme.motoextreme.models.entities.Accesorio;
import com.motoextreme.motoextreme.models.entities.Carrito;
import com.motoextreme.motoextreme.models.entities.CarritoItem;
import com.motoextreme.motoextreme.models.entities.Moto;
import com.motoextreme.motoextreme.models.repositories.IAccesorio;
import com.motoextreme.motoextreme.models.repositories.ICarrito;
import com.motoextreme.motoextreme.models.repositories.ICarritoItem;
import com.motoextreme.motoextreme.models.repositories.IMoto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CarritoItemService {

    private final ICarritoItem carritoItemRepository;
    private final ICarrito carritoRepository;
    private final IMoto motoRepository;
    private final IAccesorio accesorioRepository;

    // Agregar item al carrito (moto o accesorio)
    public CarritoItemResponseDTO agregarItemAlCarrito(Long carritoId, CarritoItemRequestDTO dto) {

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new ResourceNotFoundExeption("Carrito no encontrado"));

        if (dto.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a cero");
        }

        CarritoItem item;
        BigDecimal precioUnitario;

        // AGREGAR MOTO
        if (dto.getIdMoto() != null) {
            Moto moto = motoRepository.findById(dto.getIdMoto())
                    .orElseThrow(() -> new ResourceNotFoundExeption("Moto no encontrada"));

            Optional<CarritoItem> existente = carritoItemRepository
                    .findByCarrito_IdCarritoAndMoto_IdMoto(carritoId, dto.getIdMoto());

            if (existente.isPresent()) {
                item = existente.get();
                item.setCantidad(item.getCantidad() + dto.getCantidad());
            } else {
                item = new CarritoItem();
                item.setCarrito(carrito);
                item.setMoto(moto);
                item.setCantidad(dto.getCantidad());
            }

            precioUnitario = moto.getPrecio() != null ? moto.getPrecio() : BigDecimal.ZERO;
            item.setPrecioUnitario(precioUnitario);
            item.setSubtotal(precioUnitario.multiply(BigDecimal.valueOf(item.getCantidad())));
        }
        // AGREGAR ACCESORIO
        else if (dto.getIdAccesorio() != null) {
            Accesorio accesorio = accesorioRepository.findById(dto.getIdAccesorio())
                    .orElseThrow(() -> new ResourceNotFoundExeption("Accesorio no encontrado"));

            Optional<CarritoItem> existente = carritoItemRepository
                    .findByCarrito_IdCarritoAndAccesorio_IdAccesorio(carritoId, dto.getIdAccesorio());

            if (existente.isPresent()) {
                item = existente.get();
                item.setCantidad(item.getCantidad() + dto.getCantidad());
            } else {
                item = new CarritoItem();
                item.setCarrito(carrito);
                item.setAccesorio(accesorio);
                item.setCantidad(dto.getCantidad());
            }

            precioUnitario = accesorio.getPrecio() != null ? accesorio.getPrecio() : BigDecimal.ZERO;
            item.setPrecioUnitario(precioUnitario);
            item.setSubtotal(precioUnitario.multiply(BigDecimal.valueOf(item.getCantidad())));
        }
        else {
            throw new BadRequestException("Debes enviar idMoto o idAccesorio");
        }

        CarritoItem guardado = carritoItemRepository.save(item);

        // Recalcular total del carrito
        actualizarTotalCarrito(carrito);

        return mapToResponse(guardado);
    }

    // Actualizar cantidad de item
    public CarritoItemResponseDTO actualizarItem(Long itemId, CarritoItemRequestDTO dto) {
        CarritoItem item = carritoItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundExeption("Item no encontrado"));

        if (dto.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a cero");
        }

        item.setCantidad(dto.getCantidad());

        BigDecimal precioBase = item.getPrecioUnitario() != null ? item.getPrecioUnitario() : BigDecimal.ZERO;
        item.setSubtotal(precioBase.multiply(BigDecimal.valueOf(dto.getCantidad())));

        carritoItemRepository.save(item);

        actualizarTotalCarrito(item.getCarrito());
        return mapToResponse(item);
    }

    // Eliminar item
    public void eliminarItem(Long itemId) {
        CarritoItem item = carritoItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundExeption("Item no encontrado"));

        Carrito carrito = item.getCarrito();

        carritoItemRepository.delete(item);

        actualizarTotalCarrito(carrito);
    }

    // Recalcular total
    private void actualizarTotalCarrito(Carrito carrito) {
        BigDecimal total = carrito.getItems().stream()
                .map(i -> i.getSubtotal() != null ? i.getSubtotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrito.setTotal(total);
        carritoRepository.save(carrito);
    }

    // Mapper simple a DTO (mantén esto si no tienes carritoItemMapper)
    private CarritoItemResponseDTO mapToResponse(CarritoItem item) {
        CarritoItemResponseDTO dto = new CarritoItemResponseDTO();
        dto.setId(item.getIdCarritoItem());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setSubtotal(item.getSubtotal());

        if (item.getMoto() != null) dto.setMotoId(item.getMoto().getIdMoto());
        if (item.getAccesorio() != null) dto.setAccesorioId(item.getAccesorio().getIdAccesorio());
        return dto;
    }
}


