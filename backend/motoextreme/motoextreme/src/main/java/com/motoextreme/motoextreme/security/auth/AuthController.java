package com.motoextreme.motoextreme.security.auth;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.Map;
import com.motoextreme.motoextreme.security.jwt.JwtService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    /**
     * REGISTRO
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * LOGIN
     */
    
   @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthRequest req) {
    Authentication auth = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
    );

    UserDetails user = userDetailsService.loadUserByUsername(req.getEmail());
    String rol = user.getAuthorities().iterator().next().getAuthority(); // e.g. ROLE_ADMIN
    String email = user.getUsername();
    String token = jwtService.generarToken(email, rol);

    Map<String, Object> body = new HashMap<>();
    body.put("token", token);
    body.put("email", user.getUsername());
    body.put("rol", rol);
    return ResponseEntity.ok(body);
}

}
