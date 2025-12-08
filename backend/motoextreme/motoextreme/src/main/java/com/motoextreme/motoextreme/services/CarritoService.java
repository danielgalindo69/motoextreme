package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.response.CarritoResponseDTO;
import com.motoextreme.motoextreme.mappers.CarritoMapper;
import com.motoextreme.motoextreme.models.entities.Carrito;
import com.motoextreme.motoextreme.models.entities.Usuario;
import com.motoextreme.motoextreme.models.repositories.ICarrito;
import com.motoextreme.motoextreme.models.repositories.IUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final ICarrito carritoRepository;
    private final IUsuario usuarioRepository;
    private final CarritoMapper carritoMapper;

    // Crear carrito para un usuario
    public CarritoResponseDTO crearCarritoParaUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getCarrito() != null) {
            throw new RuntimeException("El usuario ya tiene un carrito asignado");
        }

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);

        Carrito guardado = carritoRepository.save(carrito);

        usuario.setCarrito(guardado);
        usuarioRepository.save(usuario);

        return carritoMapper.toDTO(guardado);
    }

    // Obtener carrito por id
    public CarritoResponseDTO obtenerCarrito(Long idCarrito) {

        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        return carritoMapper.toDTO(carrito);
    }

    // Obtener carrito por usuario
    public CarritoResponseDTO obtenerCarritoPorUsuario(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getCarrito() == null) {
            throw new RuntimeException("El usuario no tiene carrito asignado");
        }

        return carritoMapper.toDTO(usuario.getCarrito());
    }

    // Vaciar carrito
    public CarritoResponseDTO vaciarCarrito(Long idCarrito) {

        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        carrito.getItems().clear(); //los borra de DB

        Carrito guardado = carritoRepository.save(carrito);

        return carritoMapper.toDTO(guardado);
    }

    // Eliminar carrito completamente
    public void eliminarCarrito(Long idCarrito) {

        if (!carritoRepository.existsById(idCarrito)) {
            throw new RuntimeException("El carrito no existe");
        }

        carritoRepository.deleteById(idCarrito);
    }
}

