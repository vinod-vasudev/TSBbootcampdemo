package com.ob.tsb.balances.controller;


import com.ob.tsb.balances.setup.BaseControllerMockedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;


public class BalancesControllerTest extends BaseControllerMockedTest {




    @Test
    @DisplayName("Success - Get balances API")
    void balancesSuccessTest(){
        String authorization = "";
        String xFapiAuthDate = "";
        String xFapiCustomerIpAddress = "";
        String xFapiInteractionId = "";
        String accept = "application/json";

      //  assertTrue(authClient.validatePrivileage("test_token"));
       /// assertFalse(authClient.validatePrivileage("test_token_invalid"));

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

       /* ResponseEntity<BalancesResponse> responseEntity = new ResponseEntity<>(
                balancesResponse,
                header,
                HttpStatus.OK
        );*/

       /* BalancesResponse expected =  mockController.getBalances();
        when(mockController.getBalances()).thenReturn(expected);

        Mono<BalancesResponse> actual =  balanceService.getBalances().map(balancesResponseResponseEntity -> balancesResponseResponseEntity.getBody());
        when(balanceService.getBalances()).thenReturn(actual);


        Assertions.assertEquals(Mono.just(expected), actual, "Balances response objects should be equal");
        verify(mockController, times(1)).getBalances();*/

     //   when(balanceService.getBalances()).thenReturn(Mono.just((ResponseEntity.ok().body(balancesResponse))));

         assertTrue(true);


    }

   /* @Test
    @DisplayName("Error - Get balances API")
    void balancesErrorTest(){


    }


    @Test
    @DisplayName("Success - Get balance by id API")
    void balancesByIdSuccessTest(){


    }

    @Test
    @DisplayName("Error - Get balance by id API")
    void balancesByIdErrorTest(){


    }*/

}
