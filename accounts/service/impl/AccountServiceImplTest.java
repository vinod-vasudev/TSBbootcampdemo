package com.tsb.accounts.service.impl;

import com.tsb.accounts.model.response.accountResponse.AccountResponse;
import com.tsb.accounts.model.response.token.TokenResponse;
import com.tsb.accounts.setup.BaseWebClientMockedTest;
import com.tsb.accounts.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountServiceImplTest extends BaseWebClientMockedTest {

    @Test
    @DisplayName("Success - External API - Account API")
    void externalApiAccountSuccessTest() {

        String fapiAuthDate = "Sun, 10 Sep 2017 19:43:31 GMT";
        String fapiCustomerIpAddress = "104.25.212.99";
        String fapiInteractionId = "93bac548-d2de-4546-b106-880a5018460d";
        String accept = "application/json";
        String auth = "Bearer Az90SAOJklae";

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(accountsUrl);

        //when(authService.getToken(auth)).thenReturn(Mono.just("Az90SAOJklae"));

        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);

        when(requestHeadersUriSpecMock.uri(uriComponentsBuilder.toUriString()))
                .thenReturn(requestHeadersSpecMock);

        ArgumentCaptor<Consumer<HttpHeaders>> headerCaptor = ArgumentCaptor.forClass(Consumer.class);

        when(requestHeadersSpecMock.headers(headerCaptor.capture())).thenReturn(requestHeadersSpecMock);

        when(requestHeadersSpecMock.retrieve())
                .thenReturn(customWeClientResponseSpec);

        when(customWeClientResponseSpec.getStatus())
                .thenReturn(HttpStatus.OK);

        when(customWeClientResponseSpec.onStatus(any(Predicate.class), any(Function.class)))
                .thenCallRealMethod();

        when(customWeClientResponseSpec.bodyToMono(String.class))
                .thenReturn(Mono.just(JsonUtil.toJson(accountResponse)));

        TokenResponse tokenResponse = new TokenResponse(auth.substring(7), "JWT");

        when(authService.getToken("Bearer Az90SAOJklae")).thenReturn(Mono.just(tokenResponse.token()));

        //     Mono<String> tokenResponseMono = authServiceImpl.getToken(auth);

        Mono<AccountResponse> getAccountResponse = accountServiceImpl.getAccount(fapiAuthDate,
                fapiCustomerIpAddress,
                fapiInteractionId,
                accept,
                auth);

        System.out.println("getAccountResponse is : " + getAccountResponse);
        StepVerifier.create(getAccountResponse)
                .expectNext(accountResponse)
                        .verifyComplete();




        // TokenResponse tokenResponse1 = new TokenResponse(auth.substring(7), "JWT");




    }
}