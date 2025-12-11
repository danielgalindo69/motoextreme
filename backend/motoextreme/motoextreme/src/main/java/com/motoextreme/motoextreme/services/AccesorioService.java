package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.request.AccesorioRequestDTO;
import com.motoextreme.motoextreme.dtos.response.AccesorioResponseDTO;
import com.motoextreme.motoextreme.mappers.AccesorioMapper;
import com.motoextreme.motoextreme.models.entities.Accesorio;
import com.motoextreme.motoextreme.models.entities.Categoria;
import com.motoextreme.motoextreme.models.repositories.IAccesorio;
import com.motoextreme.motoextreme.models.repositories.ICategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccesorioService {

    private final IAccesorio accesorioRepository;
    private final ICategoria categoriaRepository;
    private final AccesorioMapper accesorioMapper;

    // Crear un accesorio
    public AccesorioResponseDTO crear(AccesorioRequestDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Accesorio accesorio = accesorioMapper.toEntity(dto, categoria);

        Accesorio guardado = accesorioRepository.save(accesorio);

        return accesorioMapper.toDTO(guardado);
    }

    // Actualizar un accesorio existente
    public AccesorioResponseDTO actualizar(Long id, AccesorioRequestDTO dto) {

        Accesorio accesorio = accesorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accesorio no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Actualizar campos
        accesorio.setNombre(dto.getNombre());
        accesorio.setDescripcion(dto.getDescripcion());
        accesorio.setPrecio(dto.getPrecio());
        accesorio.setCategoria(categoria);

        Accesorio actualizado = accesorioRepository.save(accesorio);

        return accesorioMapper.toDTO(actualizado);
    }

    // Obtener un accesorio por ID
    public AccesorioResponseDTO obtenerPorId(Long id) {
        Accesorio accesorio = accesorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accesorio no encontrado"));

        return accesorioMapper.toDTO(accesorio);
    }

    // Obtener todos
    public List<AccesorioResponseDTO> obtenerTodos() {
        List<Accesorio> accesorios = accesorioRepository.findAll();
        return accesorioMapper.toDTOList(accesorios);
    }

    // Eliminar accesorio
    public void eliminar(Long id) {
        if (!accesorioRepository.existsById(id)) {
            throw new RuntimeException("El accesorio no existe");
        }
        accesorioRepository.deleteById(id);
    }
}
