package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.response.CarritoResponseDTO;
import com.motoextreme.motoextreme.exeptions.BadRequestException;
import com.motoextreme.motoextreme.exeptions.ResourceNotFoundExeption;
import com.motoextreme.motoextreme.mappers.CarritoMapper;
import com.motoextreme.motoextreme.models.entities.*;
import com.motoextreme.motoextreme.models.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class CarritoService {

    private final IUsuario usuarioRepo;
    private final IMoto motoRepo;
    private final IAccesorio accesorioRepo;
    private final ICarrito carritoRepo;
    private final ICarritoItem itemRepo;

    // Obtener carrito por email
    public Carrito obtenerCarritoUsuario(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getCarrito() == null) {
            Carrito carrito = new Carrito();
            carrito.setUsuario(usuario);
            carritoRepo.save(carrito);
            usuario.setCarrito(carrito);
        }

        return usuario.getCarrito();
    }

    // ============================
    //       AGREGAR MOTO
    // ============================

    public Carrito agregarMoto(String email, Long idMoto, int cantidad) {

        Carrito carrito = obtenerCarritoUsuario(email);

        Moto moto = motoRepo.findById(idMoto)
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));

        CarritoItem item = new CarritoItem();
        item.setCarrito(carrito);
        item.setMoto(moto);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(moto.getPrecio());
        item.setSubtotal(moto.getPrecio().multiply(BigDecimal.valueOf(cantidad)));

        carrito.getItems().add(item);

        carrito.setTotal(carrito.calcularTotal());

        carritoRepo.save(carrito);

        return carrito;
    }

    // ============================
    //     AGREGAR ACCESORIO
    // ============================

    public Carrito agregarAccesorio(String email, Long idAccesorio, int cantidad) {

        Carrito carrito = obtenerCarritoUsuario(email);

        Accesorio accesorio = accesorioRepo.findById(idAccesorio)
                .orElseThrow(() -> new RuntimeException("Accesorio no encontrado"));

        CarritoItem item = new CarritoItem();
        item.setCarrito(carrito);
        item.setAccesorio(accesorio);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(accesorio.getPrecio());
        item.setSubtotal(accesorio.getPrecio().multiply(BigDecimal.valueOf(cantidad)));

        carrito.getItems().add(item);

        carrito.setTotal(carrito.calcularTotal());
        carritoRepo.save(carrito);

        return carrito;
    }

    // ============================
    //     ACTUALIZAR CANTIDAD
    // ============================

    public Carrito actualizarCantidad(String email, Long idItem, int cantidad) {

        Carrito carrito = obtenerCarritoUsuario(email);

        CarritoItem item = carrito.getItems().stream()
                .filter(i -> i.getIdCarritoItem().equals(idItem))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        item.setCantidad(cantidad);
        item.setSubtotal(item.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidad)));

        carrito.setTotal(carrito.calcularTotal());
        carritoRepo.save(carrito);

        return carrito;
    }

    // ============================
    //     ELIMINAR ITEM
    // ============================

    public Carrito eliminarItem(String email, Long idItem) {

        Carrito carrito = obtenerCarritoUsuario(email);

        carrito.getItems().removeIf(
                item -> item.getIdCarritoItem().equals(idItem)
        );

        carrito.setTotal(carrito.calcularTotal());
        carritoRepo.save(carrito);

        return carrito;
    }

    // ============================
    //     VACIAR CARRITO
    // ============================

    public Carrito vaciarCarrito(String email) {

        Carrito carrito = obtenerCarritoUsuario(email);

        carrito.getItems().clear();
        carrito.setTotal(BigDecimal.ZERO);

        carritoRepo.save(carrito);

        return carrito;
    }
}


