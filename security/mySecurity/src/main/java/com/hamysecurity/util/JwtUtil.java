package com.hamysecurity.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * The JwtUtil class provides methods for generating JWT tokens.
 */
@Component
public class JwtUtil {

    private final JwtEncoder jwtEncoder;
    private final String jwtIssuer;

    /**
     * Constructor to initialize JwtUtil with the secret key and issuer.
     * 
     * @param secretKey the secret key used for signing the JWT.
     * @param jwtIssuer the issuer of the JWT.
     */
    public JwtUtil(@Value("${security.jwt.secret-key}") String secretKey,
                   @Value("${security.jwt.issuer}") String jwtIssuer) {
        // Create a SecretKeySpec from the secret key
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        // Build a JWK from the SecretKeySpec
        JWK jwk = new OctetSequenceKey.Builder(secretKeySpec).build();
        // Create a JWKSource from the SecretKeySpec
        JWKSource<SecurityContext> jwkSource = new ImmutableSecret<>(secretKeySpec);
        // Initialize the JwtEncoder with the JWKSource
        this.jwtEncoder = new NimbusJwtEncoder(jwkSource);
        // Trim any extra whitespace from the issuer
        this.jwtIssuer = jwtIssuer.trim();
    }

    /**
     * Generates a JWT token for the given user details and user ID.
     * 
     * @param userDetails the user details.
     * @param userId the user ID.
     * @return the generated JWT token.
     */
    public String generateToken(UserDetails userDetails, Long userId) {
        Instant now = Instant.now();

        // Build the JWT claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600)) // 1 day expiration
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().toString())
                .claim("userId", userId)
                .build();

        // Build the JWT header and parameters
        JwtEncoderParameters params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        // Encode the JWT and return the token value
        return jwtEncoder.encode(params).getTokenValue();
    }
}
