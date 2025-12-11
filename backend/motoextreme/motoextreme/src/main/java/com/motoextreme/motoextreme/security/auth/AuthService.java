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
@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsuario usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    // LOGIN
    public AuthResponse login(AuthRequest request) {

        // Autenticación (si falla, lanza BadCredentialsException)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Buscar usuario en BD
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.isEnabled()) {
            throw new RuntimeException("El usuario está deshabilitado");
        }

        // Generar token
        String token = jwtUtils.generateToken(new UserDetailsImpl(usuario));

        return new AuthResponse(
                token,
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name()
        );
    }

    // REGISTRO
    public AuthResponse register(RegisterRequest request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol() != null ? request.getRol() : Rol.USER);
        usuario.setEnabled(true);

        usuarioRepository.save(usuario);

        // Generar token
        String token = jwtUtils.generateToken(new UserDetailsImpl(usuario));

        return new AuthResponse(
                token,
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name()
        );
    }
}

