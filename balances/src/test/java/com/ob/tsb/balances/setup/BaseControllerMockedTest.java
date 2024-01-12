package com.ob.tsb.balances.setup;


import com.ob.tsb.balances.controller.BalancesController;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.response.BalanceByIdResponse;
import com.ob.tsb.balances.model.response.BalancesResponse;
import com.ob.tsb.balances.service.BalancesService;
import com.ob.tsb.balances.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest({BalancesController.class})
public class BaseControllerMockedTest {

    @Autowired
    protected WebTestClient webTestClient;

    @MockBean
    protected BalancesService balancesService;


  /*  @MockBean
    protected GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler;*/

    protected BalancesResponse balancesResponse;

    protected BalanceByIdResponse balanceByIdResponse;



    protected CustomException customException;


   /* @BeforeEach
    void setup() {



        balancesResponse = JsonUtil.toObjectFromFile(BalancesResponse.class);

        balanceByIdResponse = JsonUtil.toObjectFromFile(BalanceByIdResponse.class);

        customException = new CustomException("451", "Failed to fetch user information");



    }*/


    @Test
    @DisplayName("Success - Get Balances API")
    void BalancesSuccessTest(){
        String authorization = "";
        String xFapiAuthDate = "";
        String xFapiCustomerIpAddress = "";
        String xFapiInteractionId = "";
        String accept = "application/json";

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<BalancesResponse> responseEntity = new ResponseEntity<>(
                balancesResponse,
                header,
                HttpStatus.OK
        );

        when(balancesService.getBalanceInfo()).thenReturn(Mono.just(responseEntity));

        webTestClient.get().uri("/balances/api/v1/balances")
                .header(HttpHeaders.AUTHORIZATION,authorization)
                .header("x-fapi-auth-date",xFapiAuthDate)
                .header("x-fapi-customer-ip-address",xFapiCustomerIpAddress)
                .header("x-fapi-interaction-id",xFapiInteractionId)
                .header(HttpHeaders.ACCEPT,accept)
                .exchange()
                .expectStatus().isOk().expectBody().returnResult().equals(balancesResponse);



    }




}
