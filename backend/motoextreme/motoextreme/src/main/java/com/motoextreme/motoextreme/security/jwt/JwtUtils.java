package com.motoextreme.motoextreme.security.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMs;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    // GENERAR TOKEN
    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("roles", userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                .sign(algorithm());
    }

    // Extraer username
    public String getUsernameFromToken(String token) {
        try {
            return JWT.require(algorithm())
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    // EXTRAER ROLES DESDE TOKEN (opcional pero útil)
    public List<String> getRolesFromToken(String token) {
        try {
            return JWT.require(algorithm())
                    .build()
                    .verify(token)
                    .getClaim("roles")
                    .asList(String.class);
        } catch (Exception e) {
            return List.of();
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        try {
            Date expiration = JWT.require(algorithm())
                    .build()
                    .verify(token)
                    .getExpiresAt();

            return expiration.before(new Date());

        } catch (Exception e) {
            return true; // Si falla, lo marcamos como expirado
        }
    }
}

