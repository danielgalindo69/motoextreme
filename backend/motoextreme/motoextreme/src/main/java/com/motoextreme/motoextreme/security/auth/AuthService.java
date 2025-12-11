package com.motoextreme.motoextreme.security.auth;

import com.motoextreme.motoextreme.models.entities.Usuario;
import com.motoextreme.motoextreme.models.enums.Rol;
import com.motoextreme.motoextreme.models.repositories.IUsuario;
import com.motoextreme.motoextreme.security.jwt.JwtUtils;
import com.motoextreme.motoextreme.security.userDetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsuario usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * LOGIN
     */
    public AuthResponse login(AuthRequest request) {

        // Validar credenciales (Spring Security se encarga)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Buscar usuario en la BD
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Generar token
        String token = jwtUtils.generateToken(new UserDetailsImpl(usuario));

        return new AuthResponse(token);
    }

    /**
     * REGISTRO
     */
    public AuthResponse register(RegisterRequest request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        // Si no manda rol, por defecto queda USER
        usuario.setRol(request.getRol() != null ? request.getRol() : Rol.USER);

        usuarioRepository.save(usuario);

        // Generar token tras registrarse
        String token = jwtUtils.generateToken(new UserDetailsImpl(usuario));

        return new AuthResponse(token);
    }
}
