package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.dtos.request.UsuarioRequestDTO;
import com.motoextreme.motoextreme.dtos.response.UsuarioResponseDTO;
import com.motoextreme.motoextreme.exeptions.ResourceNotFoundExeption;
import com.motoextreme.motoextreme.mappers.UsuarioMapper;
import com.motoextreme.motoextreme.models.entities.Carrito;
import com.motoextreme.motoextreme.models.entities.Usuario;
import com.motoextreme.motoextreme.models.repositories.IUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuario usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    // Listar usuarios
    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    // Crear usuario y asignarle carrito
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        usuario.setCarrito(carrito);

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(guardado);
    }

    // Buscar por id
    public Optional<UsuarioResponseDTO> findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(usuarioMapper::toDto);
    }

    // Eliminar usuario
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundExeption("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    // Actualizar usuario
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());

        Usuario actualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(actualizado);
    }
}
