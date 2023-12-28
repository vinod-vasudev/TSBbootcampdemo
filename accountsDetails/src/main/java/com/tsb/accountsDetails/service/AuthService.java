package com.tsb.accountsDetails.service;


import com.tsb.accountsDetails.model.response.token.TokenResponse;
import reactor.core.publisher.Mono;


public interface AuthService {

    Mono<String> getToken(String auth);

    Mono<TokenResponse> getTokens(String auth);
}
