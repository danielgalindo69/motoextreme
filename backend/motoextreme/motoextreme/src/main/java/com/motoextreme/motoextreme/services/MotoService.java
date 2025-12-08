package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.request.MotoRequestDTO;
import com.motoextreme.motoextreme.dtos.response.MotoResponseDTO;
import com.motoextreme.motoextreme.mappers.CategoriaMapper;
import com.motoextreme.motoextreme.mappers.MotoMapper;
import com.motoextreme.motoextreme.models.entities.Categoria;
import com.motoextreme.motoextreme.models.entities.Moto;
import com.motoextreme.motoextreme.models.repositories.ICategoria;
import com.motoextreme.motoextreme.models.repositories.IMoto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotoService {


    private final IMoto service;

    private final MotoMapper mapper;

    private final CategoriaMapper categoriaMapper;

    private final ICategoria categoriaRepository;

    //traer todas las motos
    public List<MotoResponseDTO> findAll() {
        List<Moto> motos = service.findAll();
        return mapper.toDtoList(motos);
    }

    //crear una moto
    public MotoResponseDTO crearMoto(MotoRequestDTO dto) {

        Moto moto = mapper.toEntity(dto);

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        moto.setCategoria(categoria);

        service.save(moto);

        return mapper.toDto(moto);
    }

    //actualizar moto
    public MotoResponseDTO actualizarMoto(Long id, MotoRequestDTO dto) {

        Moto moto = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));

        moto.setNombre(dto.getNombre());
        moto.setModelo(dto.getModelo());
        moto.setPrecio(dto.getPrecio());
        moto.setDescripcion(dto.getDescripcion());

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        moto.setCategoria(categoria);

        Moto actualizada = service.save(moto);

        return mapper.toDto(actualizada);
    }

    //buscar moto por id
    public Optional<MotoResponseDTO> findByid(Long id){
        Optional<Moto> moto = service.findById(id);
        return moto.map(mapper::toDto);
    }

    //eliminar por id
    public void eliminarMoto(Long id){
        service.deleteById(id);
    }
}
