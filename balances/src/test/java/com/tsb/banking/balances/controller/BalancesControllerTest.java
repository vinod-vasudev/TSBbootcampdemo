package com.tsb.banking.balances.controller;

import com.tsb.banking.balances.setup.BaseControllerMockedTest;
import com.tsb.banking.model.BalancesResponse;
import com.tsb.banking.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class BalancesControllerTest extends BaseControllerMockedTest {


    @Test
    @DisplayName("Success - Internal Api - Balances ")
    void balanceSuccessResponseMock() {

        String fapiAuthDate = "Sun, 10 Sep 2017 19:43:31 GMT";
        String fapiCustomerIpAddress = "104.25.212.99";
        String fapiInteractionId = "93bac548-d2de-4546-b106-880a5018460d";
        String accept = "application/json";
        String auth = "Bearer Az90SAOJklae";

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<BalancesResponse> responseEntity = new ResponseEntity<>(
                balancesResponse,
                header,
                HttpStatus.OK
        );


        when(balancesService.getBalanceInfo()).thenReturn(Mono.just(responseEntity));


        webTestClient.get().uri("/balance/v1/balances")
                .header("x-fapi-auth-date", fapiAuthDate)
                .header("x-fapi-customer-ip-address", fapiCustomerIpAddress)
                .header("x-fapi-interaction-id", fapiInteractionId)
                .header("Accept", accept)
                .header("Authorization", auth)
                .exchange().expectStatus().isOk()
                .expectBody()
                .json(JsonUtil.toJson(balancesResponse));
    }

}