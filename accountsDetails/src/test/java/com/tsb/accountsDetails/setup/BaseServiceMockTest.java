package com.tsb.accountsDetails.setup;

import com.tsb.accountsDetails.enums.ConsentType;
import com.tsb.accountsDetails.exception.CustomException;
import com.tsb.accountsDetails.model.request.token.TokenRequest;
import com.tsb.accountsDetails.model.response.accountResponse.AccountDetailsResponse;
import com.tsb.accountsDetails.model.response.token.TokenResponse;
import com.tsb.accountsDetails.service.AuthService;
import com.tsb.accountsDetails.service.impl.AccountDetailsServiceImpl;
import com.tsb.accountsDetails.service.impl.AuthServiceImpl;
import com.tsb.accountsDetails.util.JsonUtil;
import com.tsb.accountsDetails.util.PayloadUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
public class BaseServiceMockTest {


//    @Mock
//    protected WebClient.RequestBodyUriSpec requestBodyUriMock;
//
//    @Mock
//    protected WebClient.RequestBodySpec requestBodyMock;

    @Mock
    protected WebClient.RequestHeadersSpec requestHeadersMock;


//    @Mock
//    protected WebClient.RequestHeadersSpec requestHeadersSpecMock;
    @Mock
    protected WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    protected CustomWeClientResponseSpec responseSpecMock;
    @Mock
    protected WebClient webClientMock;

    @Mock
    protected AuthService authService;

//    @InjectMocks
//    protected AuthServiceImpl authServiceImpl;

    @InjectMocks
    protected AccountDetailsServiceImpl accountDetailsServiceImpl;
    protected TokenResponse tokenResponse;
    protected TokenRequest tokenRequest;
    @Value("${api.accountDetails.uri}")
    protected String accountDetails;

    protected AccountDetailsResponse accountDetailsResponse;

    protected CustomException customException;
    @BeforeEach
    public void setup(){


        accountDetailsServiceImpl = new AccountDetailsServiceImpl(webClientMock, authService);

        ReflectionTestUtils.setField(accountDetailsServiceImpl,"accountDetails", accountDetails);
        ReflectionTestUtils.setField(accountDetailsServiceImpl,"maxAttempt",3);
        ReflectionTestUtils.setField(accountDetailsServiceImpl,"delayMillis",1000);
        accountDetailsResponse = JsonUtil.toObjectFromFile(AccountDetailsResponse.class, ConsentType.NONE);

        ReflectionTestUtils.setField(PayloadUtil.class,"tokenClientId","clientId");
        ReflectionTestUtils.setField(PayloadUtil.class,"tokenClientSecret","secret");
        tokenResponse = TokenResponse.builder()
                .token("Az90SAOJklae")
                .tokenType("Bearer")
                .build();

        tokenRequest = TokenRequest.builder()
                .clientId("clientId")
                .clientSecret("secret")
                .build();

        customException = new CustomException("451","Failed to fetch account Details");
    }

}
