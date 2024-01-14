package com.ob.tsb.transactions.controller;


import com.ob.tsb.transactions.setup.BaseControllerMockedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;


public class TransactionsControllerTest extends BaseControllerMockedTest {




    @Test
    @DisplayName("Success - Get transactions API")
    void transactionsSuccessTest(){
        String authorization = "";
        String xFapiAuthDate = "";
        String xFapiCustomerIpAddress = "";
        String xFapiInteractionId = "";
        String accept = "application/json";

        assertTrue(authClient.validatePrivileage("test_token"));
        assertFalse(authClient.validatePrivileage("test_token_invalid"));

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

       /* ResponseEntity<TransactionResponse> responseEntity = new ResponseEntity<>(
                transactionsResponse,
                header,
                HttpStatus.OK
        );*/

       /* TrasactionsResponse expected =  mockController.getTransactions();
        when(mockController.getTransactions()).thenReturn(expected);

        Mono<TransactionResponse> actual =  transactionService.getTransactions().map(transactionsResponseResponseEntity -> transactionsResponseResponseEntity.getBody());
        when(transactionService.getTransactions()).thenReturn(actual);


        Assertions.assertEquals(Mono.just(expected), actual, "Transactions response objects should be equal");
        verify(mockController, times(1)).getTransactions();*/

     //   when(transactionService.getTransactions()).thenReturn(Mono.just((ResponseEntity.ok().body(transactionsResponse))));



    }

   /* @Test
    @DisplayName("Error - Get transactions API")
    void transactionsErrorTest(){


    }


    @Test
    @DisplayName("Success - Get transactions by id API")
    void transactionsByIdSuccessTest(){


    }

    @Test
    @DisplayName("Error - Get transactions by id API")
    void transactionsByIdErrorTest(){


    }*/

}
