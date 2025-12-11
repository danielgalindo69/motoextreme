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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth

                        // ==============================
                        // 🔓 ENDPOINTS PÚBLICOS
                        // ==============================
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/motos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/accesorios/**").permitAll()

                        // ==============================
                        // 🔒 ENDPOINTS PROTEGIDOS
                        // ==============================

                        // Carrito (solo USER)
                        .requestMatchers("/carrito/**").hasRole("USER")

                        // ADMIN → motos
                        .requestMatchers(HttpMethod.POST, "/motos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/motos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/motos/**").hasRole("ADMIN")

                        // ADMIN → accesorios
                        .requestMatchers(HttpMethod.POST, "/accesorios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/accesorios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/accesorios/**").hasRole("ADMIN")

                        // Cualquier otra cosa → requiere login
                        .anyRequest().authenticated()
                );

        // Filtro JWT
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ==============================
    // 🔧 Authentication Provider
    // ==============================
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // ==============================
    // 🔐 PasswordEncoder
    // ==============================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ==============================
    // 🔐 AuthenticationManager
    // ==============================
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    // ==============================
    // 🌐 CORS
    // ==============================
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}

