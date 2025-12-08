package com.motoextreme.motoextreme.mappers;
import com.motoextreme.motoextreme.dtos.request.CarritoItemRequestDTO;
import com.motoextreme.motoextreme.dtos.response.CarritoItemResponseDTO;
import com.motoextreme.motoextreme.models.entities.CarritoItem;
import org.springframework.stereotype.Component;

@Component
public class CarritoItemMapper {

    public CarritoItemResponseDTO toDTO(CarritoItem item) {
        CarritoItemResponseDTO dto = new CarritoItemResponseDTO();

        dto.setId(item.getIdCarritoItem());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setSubtotal(item.getSubtotal());

        if (item.getMoto() != null) {
            dto.setMotoId(item.getMoto().getIdMoto());
        }

        if (item.getAccesorio() != null) {
            dto.setAccesorioId(item.getAccesorio().getIdAccesorio());
        }

        return dto;
    }

    public CarritoItem toEntity(CarritoItemRequestDTO dto) {
        CarritoItem item = new CarritoItem();

        item.setCantidad(dto.getCantidad());

        return item;
    }
}
