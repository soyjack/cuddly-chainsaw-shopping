package com.hamy.tradeshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.spec.SecretKeySpec;

/**
 * The SecurityConfig class configures the security settings for the application.
 * It sets up CORS, CSRF protection, request authorization, JWT decoding, and session management.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain.
     * 
     * @param http the HttpSecurity object used to configure security.
     * @return the configured SecurityFilterChain object.
     * @throws Exception if an error occurs while configuring security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Enable CORS and disable CSRF protection
            .cors().and()
            .csrf().disable()
            // Configure request authorization
            .authorizeHttpRequests(auth -> auth
                // Allow public access to specific endpoints
                .requestMatchers("/public/**", "/authenticate/**", "/api/users/sync").permitAll()
                // Allow access to API endpoints (adjust as needed)
                .requestMatchers("/api/**").permitAll()
            )
            // Configure OAuth2 resource server to use JWT
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.decoder(jwtDecoder()))
            )
            // Configure session management to be stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    /**
     * Configures the JWT decoder.
     * 
     * @return the configured JwtDecoder object.
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        // Secret key used for signing JWTs (ensure it matches the key used in mySecurity)
        String secretKey = "X8u3tQw9kZ2pLm7eRd5yNhV4fGj6Bs8W";
        byte[] keyBytes = secretKey.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }

    /**
     * Configures the CORS filter.
     * 
     * @return the configured CorsFilter object.
     */
    @Bean
    public CorsFilter corsFilter() {
        // Configure CORS settings
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Adjust as per your front-end URL
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
