package com.motoextreme.motoextreme.mappers;

import com.motoextreme.motoextreme.dtos.response.CarritoItemResponseDTO;
import com.motoextreme.motoextreme.models.entities.CarritoItem;
import org.springframework.stereotype.Component;

@Component
public class CarritoItemMapper {

    public CarritoItemResponseDTO toDTO(CarritoItem item) {
        CarritoItemResponseDTO dto = new CarritoItemResponseDTO();
        dto.setIdItem(item.getIdCarritoItem());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setSubtotal(item.getSubtotal());

        if (item.getMoto() != null) {
            dto.setTipo("MOTO");
            dto.setReferenciaId(item.getMoto().getIdMoto());
            dto.setNombre(item.getMoto().getNombre()); // ajusta al campo real
        } else if (item.getAccesorio() != null) {
            dto.setTipo("ACCESORIO");
            dto.setReferenciaId(item.getAccesorio().getIdAccesorio());
            dto.setNombre(item.getAccesorio().getNombre()); // ajusta al campo real
        } else {
            dto.setTipo("DESCONOCIDO");
            dto.setNombre("Ítem sin referencia");
        }

        return dto;
    }
}
