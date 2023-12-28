package com.tsb.accountsDetails.service.impl;

import com.tsb.accountsDetails.exception.CustomException;
import com.tsb.accountsDetails.model.response.accountResponse.AccountDetailsResponse;
import com.tsb.accountsDetails.model.response.token.TokenResponse;
import com.tsb.accountsDetails.setup.BaseServiceMockTest;
import com.tsb.accountsDetails.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class AccountDetailsServiceImplTest extends BaseServiceMockTest {

         @Test
         @DisplayName("Success - External API - Account Details API")
         void getAccountDetailsServiceSuccessResponseMock(){


        String fapiAuthDate = "Sun, 10 Sep 2017 19:43:31 GMT";
        String fapiCustomerIpAddress = "104.25.212.99";
        String fapiInteractionId = "93bac548-d2de-4546-b106-880a5018460d";
        String accept = "application/json";
        String auth = "Bearer Az90SAOJklae";
        String accountId="22289";



        UriComponents uriComponentsBuilder = UriComponentsBuilder.fromUriString(accountDetails)
                .path(accountId)
                .build();
        when(authService.getToken(auth)).thenReturn(Mono.just("Az90SAOJklae"));


        when(webClientMock.get())
                .thenReturn(requestHeadersUriMock);

        when(requestHeadersUriMock.uri(uriComponentsBuilder.toUriString()))
                .thenReturn(requestHeadersMock);

        ArgumentCaptor<Consumer<HttpHeaders>> headersCaptor = ArgumentCaptor.forClass(Consumer.class);

        when(requestHeadersMock.headers(headersCaptor.capture()))
                .thenReturn(requestHeadersMock);

        when(requestHeadersMock.retrieve())
                .thenReturn(responseSpecMock);

        when(responseSpecMock.getStatus())
                .thenReturn(HttpStatus.OK);

        when(responseSpecMock.onStatus(any(Predicate.class), any(Function.class)))
                .thenCallRealMethod();


        when(responseSpecMock.bodyToMono(String.class))
                .thenReturn(Mono.just(JsonUtil.toJson(accountDetailsResponse)));


        Mono<AccountDetailsResponse> accountDetailsResponseMono = accountDetailsServiceImpl.getAccountDetails(fapiAuthDate,fapiCustomerIpAddress,fapiInteractionId,accept,auth,accountId);


        StepVerifier.create(accountDetailsResponseMono)
                .expectNext(accountDetailsResponse)
                .verifyComplete();


    }



}