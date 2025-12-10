package com.motoextreme.motoextreme.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "MI_SECRETO_SUPER_SEGURO_QUE_DEBE_SER_MAS_LARGO";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 horas

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY);
    }

    // Crear Token
    public String generarToken(String email, String rol) {
        return JWT.create()
                .withSubject(email)
                .withClaim("rol", rol)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(getAlgorithm());
    }

    // Validar token
    public boolean validarToken(String token) {
        try {
            JWT.require(getAlgorithm()).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Obtener email (subject) del token
    public String obtenerEmail(String token) {
        return getDecoded(token).getSubject();
    }

    // Obtener rol del token
    public String obtenerRol(String token) {
        return getDecoded(token).getClaim("rol").asString();
    }

    // Decodificar token
    private DecodedJWT getDecoded(String token) {
        return JWT.require(getAlgorithm()).build().verify(token);
    }
}
