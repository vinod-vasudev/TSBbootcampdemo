package com.ob.tsb.balances.service.impl;

import com.ob.tsb.balances.client.AuthClient;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.service.AuthService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


     private final AuthClient authClient;

     private final Key key;
     private final SignatureAlgorithm signatureAlgorithm;



    public AuthServiceImpl(AuthClient authClient, Key key, SignatureAlgorithm signatureAlgorithm) {
        this.authClient = authClient;
        this.key = key;
        this.signatureAlgorithm = signatureAlgorithm;
    }

    @Override
    @CircuitBreaker(name = "authService", fallbackMethod = "authServiceCbFallback")
    public boolean validateToken(String token) {
        return authClient.validatePrivileage(token);
    }

    @Override
    public String generateJwtToken(String consentId) {
        return Jwts.builder().setIssuer("TSB")
                .setSubject("TestingToken")
                .claim("consentId", consentId)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusSeconds(864000)))
                .signWith(  key, signatureAlgorithm).compact();
    }

    @Override
    public Claims getClaimsFromJWt(String token) {
        Claims claims;
        token = token.replace("Bearer ", "");
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    public ResponseEntity<?> authServiceCbFallback(String token, RequestNotPermitted requestNotPermitted) {
        log.info("Auth Service Fallback method called.");
        log.info("RequestNotPermitted exception message: {}", requestNotPermitted.getMessage());
        //return false;
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG);
    }

}
