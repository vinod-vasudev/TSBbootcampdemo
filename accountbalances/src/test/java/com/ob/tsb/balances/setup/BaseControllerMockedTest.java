package com.ob.tsb.balances.setup;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.client.AuthClient;
import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.controller.BalancesController;
import com.ob.tsb.balances.mock.MockController;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.AuthService;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.service.ConsentService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;

@WebFluxTest(BalancesController.class)
public class BaseControllerMockedTest {

    @Autowired
    protected AuthClient authClient;

    @MockBean
    protected MockController mockController;

    @Autowired
    protected BalancesClient balancesClient;

    @Autowired
    protected BalanceService balanceService;

    @Autowired
    protected ConsentService consentService;

    @MockBean
    protected AuthService authService;

    @Autowired
    protected ObjectMapper objectMapper;

    protected OBReadBalance balancesResponse;


    @SneakyThrows
    @BeforeEach
    void setup(){
      //  balancesResponse = objectMapper.readValue(new File("src/main/resources/openapi/fakeResponse.json"), OBReadBalance.class);
    }


}
