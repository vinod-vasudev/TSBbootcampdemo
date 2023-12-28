package com.tsb.accountsDetails.controller.impl;

import com.tsb.accountsDetails.model.response.token.TokenResponse;
import com.tsb.accountsDetails.setup.BaseControllerMockTest;
import com.tsb.accountsDetails.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


public class AccountDetailsControllerMock extends BaseControllerMockTest {

    @Test
    @DisplayName("Success Response - Internal Api - Account Details Controller API")
    void getAccountDetailsControllerSuccessResponseMock(){



        TokenResponse tokenResponse11 = new TokenResponse("Bearer Az90SAOJklae", "JWT");
       
        when(authService.getToken(auth))
                .thenReturn(Mono.just(tokenResponse11.token()));


        when(accountDetailsService.getAccountDetails(fapiAuthDate,fapiCustomerIpAddress,fapiInteractionId,accept,auth,accountId))
                .thenReturn(Mono.just(accountDetailsResponse));

        webTestClient
                .get()
                .uri("/api/accountDetails/v1/accounts/"+accountId)
                .header("x-fapi-auth-date", fapiAuthDate)
                .header("x-fapi-customer-ip-address", fapiCustomerIpAddress)
                .header("x-fapi-interaction-id", fapiInteractionId)
                .header("Accept", accept)
                .header("Authorization", auth)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(JsonUtil.toJson(accountDetailsResponse));
    }
}
