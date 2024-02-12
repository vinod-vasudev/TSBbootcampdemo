package com.ob.tsb.balances.service;

import io.jsonwebtoken.Claims;

public interface AuthService {

    boolean validateToken(String token);

    String generateJwtToken(String consentId);

    Claims getClaimsFromJWt(String token);
}
