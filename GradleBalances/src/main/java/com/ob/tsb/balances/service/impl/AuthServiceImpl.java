package com.ob.tsb.balances.service.impl;

import com.ob.tsb.balances.client.AuthClient;
import com.ob.tsb.balances.service.AuthService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;

/**
 * Service class for authentication operations.
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;

    private final Key key;


    /**
     * Constructs an instance of AuthServiceImpl.
     *
     * @param authClient         The client for authentication operations.
     * @param key                The key used for JWT signing.
     */
    public AuthServiceImpl(AuthClient authClient, Key key) {
        this.authClient = authClient;
        this.key = key;
    }

    /**
     * Validates the given token by calling an external service.
     * @param token The token to be validated.
     * @return True if the token is valid, otherwise false.
     */
    @Override
    // @CircuitBreaker(name = "authService", fallbackMethod = "authServiceCbFallback")
    public boolean validateToken(String token) {
        return authClient.validatePrivilege(token);
    }

    /**
     * Generates a JWT token with specified consent ID.
     *
     * @param consentId The consent ID to be included in the token.
     * @return The generated JWT token.
     */
    @Override
    public String generateJwtToken(String consentId) {

        return Jwts.builder().issuedAt(new Date()).claim("consentId", consentId)
                .expiration(Date.from(Instant.now().plusSeconds(864000))).signWith(key).compact();
    }

    /**
     * Parses the provided JWT token and retrieves its claims.
     *
     * @param token The JWT token to parse.
     * @return The claims extracted from the token, or null if parsing fails.
     */
    @Override
    public Claims getClaimsFromJWt(String token) {
        Claims claims;
        token = token.replace("Bearer ", "");
        try {
            claims =  Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Fallback method for validateToken in case of service failure.
     * Logs the failure and returns a service unavailable response.
     *
     * @param token               The token being validated.
     * @param requestNotPermitted The exception indicating service failure.
     * @return ResponseEntity with service unavailable status.
     */
    public ResponseEntity<Object> authServiceCbFallback(String token, RequestNotPermitted requestNotPermitted) {
        log.info("Auth Service Fallback method called.{} ", token);
        log.info("RequestNotPermitted exception message: {}", requestNotPermitted.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(CIRCUIT_BREAKER_FALLBACK_MSG);
    }

}
