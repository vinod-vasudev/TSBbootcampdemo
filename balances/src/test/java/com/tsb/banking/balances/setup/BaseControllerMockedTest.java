package com.tsb.banking.balances.setup;


import com.tsb.banking.controller.BalanceByAccountIdController;
import com.tsb.banking.controller.BalancesController;
import com.tsb.banking.exception.CustomException;
import com.tsb.banking.exception.GlobalErrorWebExceptionHandler;
import com.tsb.banking.model.BalanceByIdResponse;
import com.tsb.banking.model.BalancesResponse;
import com.tsb.banking.service.BalancesService;
import com.tsb.banking.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest({BalancesController.class, BalanceByAccountIdController.class})
public class BaseControllerMockedTest {

    @Autowired
    protected WebTestClient webTestClient;

    @MockBean
    protected BalancesService balancesService;


    @MockBean
    protected GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler;

    protected BalancesResponse balancesResponse;

    protected BalanceByIdResponse balanceByIdResponse;



    protected CustomException customException;


    @BeforeEach
    void setup() {



        balancesResponse = JsonUtil.toObjectFromFile(BalancesResponse.class);

        balanceByIdResponse = JsonUtil.toObjectFromFile(BalanceByIdResponse.class);

        customException = new CustomException("451", "Failed to fetch user information");



    }

}
