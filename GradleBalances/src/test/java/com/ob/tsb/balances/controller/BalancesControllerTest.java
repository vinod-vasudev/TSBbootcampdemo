package com.ob.tsb.balances.controller;


import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.setup.BaseWebClientMockedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(BalancesController.class)
class BalancesControllerTest extends BaseWebClientMockedTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    BalanceService balanceService;



    @Test
    @DisplayName("Success - Health check")
    void healthCheckSuccessTest(){
        webTestClient.get().uri("/api/v1/health-check")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Health is good");
    }

    @Test
    @DisplayName("Success - Get Balances")
    void balancesSuccessTest(){
        when(consentService.getConsentId(anyString())).thenReturn(consentId);
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        when(balanceService.getBalances(consentId)).thenReturn(Mono.just(obReadBalance));
        webTestClient.get().uri("/api/v1/balances")
                .header(HttpHeaders.AUTHORIZATION, token)
                .exchange()
                .expectStatus().isOk().expectBody(OBReadBalance.class).isEqualTo(obReadBalance);
    }

    @Test
    @DisplayName("Failure - Get Balances")
    void balancesFailureTest(){
        when(consentService.getConsentId(anyString())).thenReturn(consentId);
        when(balanceService.getBalances(consentId)).thenReturn(Mono.just(obReadBalance));
        webTestClient.get().uri("/api/v1/balances")
                .header(HttpHeaders.AUTHORIZATION, "")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    @DisplayName("Success - Get Balances by id")
    void balancesByIdSuccessTest(){
        when(consentService.getConsentId(anyString())).thenReturn(consentId);
        when(balanceService.getBalanceById(consentId, accountId)).thenReturn(Mono.just(obReadBalance));
        webTestClient.get().uri("/api/v1/accounts/"+accountId+"/balances")
                .header(HttpHeaders.AUTHORIZATION, token)
                .exchange()
                .expectStatus().isOk().expectBody(OBReadBalance.class).isEqualTo(obReadBalance);
    }

    @Test
    @DisplayName("Failure - Get Balances by id")
    void balancesByIdFailureTest(){
        when(consentService.getConsentId(anyString())).thenReturn(consentId);
        when(balanceService.getBalanceById(consentId, accountId)).thenReturn(Mono.just(obReadBalance));
        webTestClient.get().uri("/api/v1/balancesById")
                .header(HttpHeaders.AUTHORIZATION, "")
                .exchange()
                .expectStatus().isUnauthorized();
    }

}
