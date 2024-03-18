package com.ob.tsb.balances.config;


import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class JwtConfig {
    private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    @Bean
    public Key secretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

}
