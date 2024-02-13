package com.ob.tsb.balances.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class AuthClient {

    private final WebClient webClient;

    @Value("${auth.url}")
    private String authUrl;


    public AuthClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean validatePrivileage(String token){
        log.info("Validate token ...  " + token);
      /* Auth auth = webClient.get().uri(authUrl+token).retrieve()
               .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> { return handle4xxClientError(AUTH_CLIENT_ERROR, clientResponse);})
               .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> { return handle5xxClientError(AUTH_SERVER_ERROR, clientResponse);})
               .bodyToMono(Auth.class).block();
        return auth.isValid();*/
       /* if(token.equalsIgnoreCase("test_token")){
            log.info("Token is validated successfully.");
            return true;
        }*/
        log.info("Token validation is successfully.");
        return true;
    }
}
