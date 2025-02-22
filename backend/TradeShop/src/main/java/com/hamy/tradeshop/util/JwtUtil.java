package com.hamy.tradeshop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final String jwtIssuer;

    public JwtUtil(@Value("${security.jwt.secret-key}") String secretKey,
                   @Value("${security.jwt.issuer}") String jwtIssuer) {
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");

        JWK jwk = new OctetSequenceKey.Builder(secretKeySpec).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableSecret<>(secretKeySpec);
        this.jwtEncoder = new NimbusJwtEncoder(jwkSource);

        this.jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        this.jwtIssuer = jwtIssuer.trim();
    }

    public boolean validateToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            String actualIssuer = jwt.getIssuer().toString().trim();
            String expectedIssuer = jwtIssuer;

            logger.info("Actual JWT Issuer: '{}'", actualIssuer);
            logger.info("Expected JWT Issuer: '{}'", expectedIssuer);
            logger.info("Actual Issuer Length: {}", actualIssuer.length());
            logger.info("Expected Issuer Length: {}", expectedIssuer.length());

            for (int i = 0; i < Math.min(actualIssuer.length(), expectedIssuer.length()); i++) {
                logger.info("Char {}: Actual '{}' vs Expected '{}'", i, actualIssuer.charAt(i), expectedIssuer.charAt(i));
            }

            logger.info("Actual Issuer Bytes: {}", new String(actualIssuer.getBytes(StandardCharsets.UTF_8)));
            logger.info("Expected Issuer Bytes: {}", new String(expectedIssuer.getBytes(StandardCharsets.UTF_8)));

            logger.info("JWT Subject: {}", jwt.getSubject());
            logger.info("JWT Claims: {}", jwt.getClaims());
            logger.info("JWT Expiration: {}", jwt.getExpiresAt());

            boolean isExpired = isTokenExpired(jwt);
            logger.info("Is Token Expired: {}", isExpired);

            boolean isValidIssuer = Objects.equals(expectedIssuer, actualIssuer);
            logger.info("Is Valid Issuer: {}", isValidIssuer);

            boolean isValidSignatureAlgorithm = jwt.getHeaders().get("alg").equals("HS256");
            logger.info("Is Valid Signature Algorithm: {}", isValidSignatureAlgorithm);

            return isValidIssuer && !isExpired && isValidSignatureAlgorithm;
        } catch (JwtException e) {
            logger.error("Token validation error: ", e);
            return false;
        }
    }

    private boolean isTokenExpired(Jwt jwt) {
        return jwt.getExpiresAt().isBefore(Instant.now());
    }
}
