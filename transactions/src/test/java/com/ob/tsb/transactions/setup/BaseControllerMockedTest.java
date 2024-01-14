package com.ob.tsb.transactions.setup;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.transactions.client.TransactionsClient;
import com.ob.tsb.transactions.client.AuthClient;
import com.ob.tsb.transactions.controller.TransactionsController;
import com.ob.tsb.transactions.mock.MockController;
import com.ob.tsb.transactions.model.response.TransactionsResponse;
import com.ob.tsb.transactions.service.TransactionService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;

@WebFluxTest(TransactionsController.class)
public class BaseControllerMockedTest {

    @Autowired
    protected AuthClient authClient;

    @MockBean
    protected MockController mockController;

    @Autowired
    protected TransactionsClient transactionsClient;

    @Autowired
    protected TransactionService transactionService;

    @Autowired
    protected ObjectMapper objectMapper;

    protected TransactionsResponse transactionsResponse;


    @SneakyThrows
    @BeforeEach
    void setup(){
        transactionsResponse = objectMapper.readValue(new File("src/main/resources/openapi/fakeResponseAccId.json"), TransactionsResponse.class);
    }


}
