package com.tsb.accountsDetails.service.impl;


import com.tsb.accountsDetails.model.response.token.TokenResponse;
import com.tsb.accountsDetails.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final WebClient webClient;

//    @Value("tokenUrl")
//    private String tokenUrl;

//    @Value("${api.max-attempt}")
//    private Integer maxAttempt;
//    @Value("${api.delay-millis}")
//    private Integer delayMillis;


    public AuthServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<String> getToken(String auth) {
        TokenResponse tokenResponse = new TokenResponse(auth.substring(7), "JWT");
        System.out.println(tokenResponse);

        return Mono.just(tokenResponse.token());
    }

    @Override
    public Mono<TokenResponse> getTokens(String auth) {

        TokenResponse tokenResponse = new TokenResponse(auth.substring(7), "JWT");

        return Mono.just(tokenResponse);
    }


}
