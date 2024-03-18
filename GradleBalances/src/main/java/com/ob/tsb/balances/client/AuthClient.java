package com.ob.tsb.balances.client;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Objects;

@Component
@Slf4j
public class AuthClient {

    private final Key key;
    public AuthClient(Key key) {
        this.key = key;
    }
    /**
     * Validates the provided token.
     *
     * @param token The token to validate.
     * @return True if the token is valid, otherwise false.
     */
    public boolean validatePrivilege(String token){
        log.info("Validate token ...  " + token);
        Claims claims;
        token = token.replace("Bearer ", "");
        try {
            claims =  Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            claims = null;
        }

        if(Objects.nonNull(claims)){
            String consentId = claims.get("consentId").toString();
            if(Objects.nonNull(consentId)){
                return true;
            }
        }

        log.info("Token validation is successfully.");
        return false;
    }
}
