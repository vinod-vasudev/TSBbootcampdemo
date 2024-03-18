package com.ob.tsb.balances.service;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;

/**
 * Interface for authentication-related operations.
 */
public interface AuthService {

    /**
     * Validates the provided token.
     *
     * @param token The token to validate.
     * @return True if the token is valid, otherwise false.
     */
    boolean validateToken(String token);
    /**
     * Generates a JWT token with the provided consent ID.
     *
     * @param consentId The consent ID to include in the token.
     * @return The generated JWT token.
     */
    String generateJwtToken(String consentId);
    /**
     * Parses the provided JWT token and retrieves its claims.
     *
     * @param token The JWT token to parse.
     * @return The claims extracted from the token.
     */
    Claims getClaimsFromJWt(String token);

    public ResponseEntity<Object> authServiceCbFallback(String token, RequestNotPermitted requestNotPermitted);
}
