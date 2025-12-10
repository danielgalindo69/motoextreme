package com.motoextreme.motoextreme.config;

import com.motoextreme.motoextreme.security.jwt.JwtAuthenticationFilter;
import com.motoextreme.motoextreme.security.userDetails.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Luego lo activamos si quieres CORS global
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // Endpoints publicos (login / registro)
                        .requestMatchers("/auth/**").permitAll()

                        // Usuarios
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // registro
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")

                        // Accesorios
                        .requestMatchers(HttpMethod.GET, "/accesorios/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/accesorios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/accesorios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/accesorios/**").hasRole("ADMIN")

                        //  Carrito items
                        .requestMatchers("/carrito-items/**").hasRole("USER")

                        // Cualquier otro endpoint requiere autenticación
                        .anyRequest().authenticated()
                )

                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Servicio de autenticación con nuestro UserDetailsServiceImpl
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // AuthenticationManager para AuthService (login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Encriptación segura
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Aquí pones el origen frontend
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        // Métodos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        // Headers permitidos
        config.setAllowedHeaders(List.of("*"));

        // Necesario si envia JWT o cookies
        config.setAllowCredentials(true);

        // Tiempo que el navegador guarda la config en cache
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

}