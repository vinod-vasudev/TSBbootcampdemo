package com.tsb.accounts.setup;

import com.tsb.accounts.enums.ConsentType;
import com.tsb.accounts.model.response.accountResponse.AccountResponse;
import com.tsb.accounts.service.AccountService;
import com.tsb.accounts.service.AuthService;
import com.tsb.accounts.service.impl.AccountServiceImpl;
import com.tsb.accounts.service.impl.AuthServiceImpl;
import com.tsb.accounts.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.NotExtensible;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
public class BaseWebClientMockedTest {

    @Mock
    protected WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    protected WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    protected WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    protected WebClient.RequestBodySpec requestBodySpec;
    @Mock
    protected CustomWeClientResponseSpec customWeClientResponseSpec;
    @Mock
    protected WebClient webClient;



    @Mock
    protected AuthService authService;

    protected AccountService accountService;

    protected AuthServiceImpl authServiceImpl;


    protected AccountServiceImpl accountServiceImpl;
    protected AccountResponse accountResponse;

//    protected CustomException customException;


//    protected TokenResponse tokenResponse;
//    protected CustomException customExceptionToken;

    @Value("${api.account.uri}")
    protected String accountsUrl;


    @BeforeEach
    public void setUp() {

        accountServiceImpl = new AccountServiceImpl(webClient, authService);
        ReflectionTestUtils.setField(accountServiceImpl,"accountsUrl", accountsUrl);
        ReflectionTestUtils.setField(accountServiceImpl,"maxAttempt",3);
        ReflectionTestUtils.setField(accountServiceImpl,"delayMillis",1000);

//        authService = new AuthServiceImpl(webClient);
//        ReflectionTestUtils.setField(authService, "auth", "Bearer Az90SAOJklae");
//        ReflectionTestUtils.setField(authService, "maxAttempt",3);
//        ReflectionTestUtils.setField(authService,"delayMillis",1000);


        accountResponse =   JsonUtil.toObjectFromFile(AccountResponse.class, ConsentType.NONE);



       // authServiceImpl = new AuthServiceImpl(webClient);
    }


}
