package com.ob.tsb.accounts.controller;


import com.ob.tsb.accounts.response.AccountsResponse;
import com.ob.tsb.accounts.setup.BaseControllerMockedTest;
import com.ob.tsb.accounts.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


public class AccountsControllerTest extends BaseControllerMockedTest {


    @Test
    @DisplayName("Success - Get accounts API")
    void accountsSuccessTest(){
        String authorization = "";
        String xFapiAuthDate = "";
        String xFapiCustomerIpAddress = "";
        String xFapiInteractionId = "";
        String accept = "application/json";

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<AccountsResponse> responseEntity = new ResponseEntity<>(
                accountsResponse,
                header,
                HttpStatus.OK
        );

        when(accountService.getAccounts()).thenReturn(Mono.just(responseEntity));

       webTestClient.get().uri("/api/v1/accounts")
               .header(HttpHeaders.AUTHORIZATION,authorization)
               .header("x-fapi-auth-date",xFapiAuthDate)
               .header("x-fapi-customer-ip-address",xFapiCustomerIpAddress)
               .header("x-fapi-interaction-id",xFapiInteractionId)
               .header(HttpHeaders.ACCEPT,accept)
               .exchange()
               .expectStatus().isOk().expectBody().returnResult().equals(accountsResponse);



    }

   /* @Test
    @DisplayName("Error - Get accounts API")
    void accountsErrorTest(){


    }


    @Test
    @DisplayName("Success - Get account by id API")
    void accountsByIdSuccessTest(){


    }

    @Test
    @DisplayName("Error - Get account by id API")
    void accountsByIdErrorTest(){


    }*/

}
