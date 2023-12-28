package com.tsb.accounts.setup;

import com.tsb.accounts.controller.impl.AccountController;
import com.tsb.accounts.enums.ConsentType;
import com.tsb.accounts.exception.CustomException;
import com.tsb.accounts.exception.GlobalErrorWebExceptionHandler;
import com.tsb.accounts.model.response.accountResponse.AccountResponse;
import com.tsb.accounts.model.response.token.TokenResponse;
import com.tsb.accounts.service.AccountService;
import com.tsb.accounts.service.AuthService;
import com.tsb.accounts.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest({AccountController.class})
public class BaseControllerMockedTest {

    @Autowired
    protected WebTestClient webTestClient;

    @MockBean
    protected AccountService accountService;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler;

    protected AccountResponse accountResponse;

    protected CustomException customException;

    protected TokenResponse tokenResponse;

@BeforeEach
    void setup(){
accountResponse = JsonUtil.toObjectFromFile(AccountResponse.class, ConsentType.NONE);

    tokenResponse = TokenResponse.builder()
            .token("Az90SAOJklae")
            .tokenType("Bearer")
            .build();


    customException = new CustomException("451", "Failed to fetch user information");

}

}
