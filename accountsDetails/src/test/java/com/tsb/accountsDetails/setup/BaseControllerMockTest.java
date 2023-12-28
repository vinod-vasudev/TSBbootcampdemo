package com.tsb.accountsDetails.setup;

import com.tsb.accountsDetails.controller.impl.AccountDetailsController;
import com.tsb.accountsDetails.enums.ConsentType;
import com.tsb.accountsDetails.exception.CustomException;
import com.tsb.accountsDetails.exception.GlobalErrorWebExceptionHandler;
import com.tsb.accountsDetails.model.request.token.TokenRequest;
import com.tsb.accountsDetails.model.response.accountResponse.AccountDetailsResponse;
import com.tsb.accountsDetails.model.response.token.TokenResponse;
import com.tsb.accountsDetails.service.AccountDetailsService;
import com.tsb.accountsDetails.service.AuthService;
import com.tsb.accountsDetails.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


@WebFluxTest({
        AccountDetailsController.class
})
public class BaseControllerMockTest {
    @Mock
    protected WebTestClient webTestClient;
    @MockBean
    protected AuthService authService;

    @MockBean
    protected TokenRequest tokenRequest;
    @MockBean
    protected AccountDetailsService accountDetailsService;
    protected CustomException customException;
    protected TokenResponse tokenResponse;

    protected AccountDetailsResponse accountDetailsResponse;

    @Value("Sun, 10 Sep 2017 19:43:31 GMT")
    protected String fapiAuthDate;
    @Value("104.25.212.99")
    protected String fapiCustomerIpAddress;
    @Value("93bac548-d2de-4546-b106-880a5018460d")
    protected String fapiInteractionId;
    @Value("application/json")
    protected String accept;
    @Value("Bearer Az90SAOJklae")
    protected String auth;

    @Value("22289")
    protected String accountId;

    @Value("${api.accountDetails.uri}")
    protected String accountDetails;


    @MockBean
   protected GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler;

    @BeforeEach
    void setup() {

        accountDetailsResponse = JsonUtil.toObjectFromFile(AccountDetailsResponse.class, ConsentType.NONE);

        tokenResponse = TokenResponse.builder()
                .token("Az90SAOJklae")
                .tokenType("Bearer")
                .build();
        tokenRequest = TokenRequest.builder()
                .clientId("clientId")
                .clientSecret("secret")
                .build();

        customException = new CustomException("451","Failed to fetch user information");




          }

}
