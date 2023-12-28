package com.tsb.banking.balances.setup;


import com.tsb.banking.model.BalanceByIdResponse;
import com.tsb.banking.model.BalancesResponse;
import com.tsb.banking.service.BalancesService;
import com.tsb.banking.service.impl.BalancesServiceImpl;
import com.tsb.banking.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.http.ResponseEntity;
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
    protected CustomWeClientResponseSpec customWebClientResponseSpec;
    @Mock
    protected WebClient webClient;




    @InjectMocks
    protected BalancesServiceImpl balancesServiceImpl;
    protected BalancesResponse balancesResponse;

    protected BalanceByIdResponse balanceByIdResponse;


    @Value("${api.balances.uri}")
    protected String balancesUrl;



    @Value("${api.balancesByAccountId.uri}")
    protected String balancesByAccountIdUrl;



    @BeforeEach
    public void setUp() {

        balancesServiceImpl = new BalancesServiceImpl(webClient);

        balancesResponse = JsonUtil.toObjectFromFile(BalancesResponse.class);

        balanceByIdResponse = JsonUtil.toObjectFromFile(BalanceByIdResponse.class);

    }


}
