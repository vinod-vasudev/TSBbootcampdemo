package com.ob.tsb.balances.config;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class JwtConfig {
    private final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    @Bean
    public Key secretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Bean
    public SignatureAlgorithm signatureAlgorithm(){
        return SignatureAlgorithm.HS256;
    }
}
