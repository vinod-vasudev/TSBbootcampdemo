package com.tsb.accounts.controller.impl;

import com.tsb.accounts.setup.BaseControllerMockedTest;
import com.tsb.accounts.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

class AccountControllerTest extends BaseControllerMockedTest {

    @Test
    @DisplayName("Success - Internal Api - Account")
    void accountSuccessResponseMock() {

        String fapiAuthDate = "Sun, 10 Sep 2017 19:43:31 GMT";
        String fapiCustomerIpAddress = "104.25.212.99";
        String fapiInteractionId = "93bac548-d2de-4546-b106-880a5018460d";
        String accept = "application/json";
        String auth = "Bearer Az90SAOJklae";

        when(accountService.getAccount(fapiAuthDate, fapiCustomerIpAddress,
                fapiInteractionId, accept, auth)).thenReturn(Mono.just(accountResponse));


        webTestClient.get().uri("/api/accounts/v1/accounts")
                .header("x-fapi-auth-date", fapiAuthDate)
                .header("x-fapi-customer-ip-address", fapiCustomerIpAddress)
                .header("x-fapi-interaction-id", fapiInteractionId)
                .header("Accept", accept).header("Authorization", auth)
                .exchange().expectStatus().isOk().expectHeader()
                .contentType(MediaType.APPLICATION_JSON).expectBody()
                .json(JsonUtil.toJson(accountResponse));
    }

}