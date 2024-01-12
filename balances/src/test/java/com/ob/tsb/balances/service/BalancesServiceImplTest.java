package com.ob.tsb.balances.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class BalancesServiceImplTest  {

    @Test
    @DisplayName("Success - External API - Balances API")
    void externalApiBalancesSuccessTest() {

        String fapiAuthDate = "Sun, 10 Sep 2017 19:43:31 GMT";
        String fapiCustomerIpAddress = "104.25.212.99";
        String fapiInteractionId = "93bac548-d2de-4546-b106-880a5018460d";
        String accept = "application/json";
        String auth = "Bearer Az90SAOJklae";


        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

       /* ResponseEntity<BalancesResponse> responseEntity = new ResponseEntity<>(
                balancesResponse,
                HttpStatus.OK
        );


        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(balancesUrl);


        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);

        when(requestHeadersUriSpecMock.uri(uriComponentsBuilder.toUriString()))
                .thenReturn(requestHeadersSpecMock);

        ArgumentCaptor<Consumer<HttpHeaders>> headerCaptor = ArgumentCaptor.forClass(Consumer.class);

        when(requestHeadersSpecMock.headers(headerCaptor.capture())).thenReturn(requestHeadersSpecMock);

        when(requestHeadersSpecMock.retrieve())
                .thenReturn(customWebClientResponseSpec);

        when(customWebClientResponseSpec.getStatus())
                .thenReturn(HttpStatus.OK);

        when(customWebClientResponseSpec.onStatus(any(Predicate.class), any(Function.class)))
                .thenCallRealMethod();

        when(customWebClientResponseSpec.toEntity(BalancesResponse.class))
                .thenReturn(Mono.just(responseEntity));


        Mono<ResponseEntity<BalancesResponse>> getBalanceResponse = balancesServiceImpl.getBalanceInfo();


        StepVerifier.create(getBalanceResponse)
                .expectNext(responseEntity)
                        .verifyComplete();*/





    }



}