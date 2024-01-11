package com.ob.tsb.balances.setup;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.model.response.BalanceByIdResponse;
import com.ob.tsb.balances.model.response.BalancesResponse;
import com.ob.tsb.balances.service.impl.BalancesServiceImpl;
import com.ob.tsb.balances.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
   /* @Mock
    protected CustomWeClientResponseSpec customWebClientResponseSpec;*/
    @Mock
    protected WebClient webClient;

    @Mock
    protected ObjectMapper objectMapper;




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

        balancesServiceImpl = new BalancesServiceImpl(webClient, objectMapper);

        balancesResponse = JsonUtil.toObjectFromFile(BalancesResponse.class);

        balanceByIdResponse = JsonUtil.toObjectFromFile(BalanceByIdResponse.class);

    }


}
