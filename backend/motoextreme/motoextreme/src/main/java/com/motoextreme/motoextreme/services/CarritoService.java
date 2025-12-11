package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.response.CarritoItemResponseDTO;
import com.motoextreme.motoextreme.dtos.response.CarritoResponseDTO;
import com.motoextreme.motoextreme.mappers.CarritoItemMapper;
import com.motoextreme.motoextreme.models.entities.Carrito;
import com.motoextreme.motoextreme.models.entities.Usuario;
import com.motoextreme.motoextreme.models.repositories.ICarrito;
import com.motoextreme.motoextreme.models.repositories.IUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final ICarrito carritoRepository;
    private final IUsuario usuarioRepository;
    private final CarritoItemMapper itemMapper;

    public CarritoResponseDTO obtenerCarritoPorUsuario(Long usuarioId) {
        Carrito carrito = carritoRepository.findByUsuario_IdUsuario(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario"));

        return toDTO(carrito);
    }

    public CarritoResponseDTO crearCarritoParaUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoRepository.findByUsuario_IdUsuario(usuarioId).orElse(null);
        if (carrito == null) {
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito = carritoRepository.save(carrito);
        }
        return toDTO(carrito);
    }

    public CarritoResponseDTO obtenerCarrito(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        return toDTO(carrito);
    }

    public void vaciarCarrito(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.getItems().clear();
        carritoRepository.save(carrito);
    }

    private CarritoResponseDTO toDTO(Carrito carrito) {
        CarritoResponseDTO dto = new CarritoResponseDTO();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setUsuarioId(carrito.getUsuario().getIdUsuario()); // ajusta si el campo difiere
        dto.setTotal(BigDecimal.valueOf(carrito.getTotal()));


        List<CarritoItemResponseDTO> items = carrito.getItems().stream()
                .map(itemMapper::toDTO)
                .toList();
        dto.setItems(items);
        return dto;
    }
}
