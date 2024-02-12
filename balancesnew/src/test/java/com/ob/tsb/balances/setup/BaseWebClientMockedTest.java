package com.ob.tsb.balances.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.service.ConsentService;
import com.ob.tsb.balances.service.ValidationService;
import com.ob.tsb.balances.service.impl.BalanceServiceImpl;
import com.ob.tsb.balances.util.JsonUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource(locations = "classpath:application-local.yml")
public class BaseWebClientMockedTest {

    @InjectMocks
    protected BalanceServiceImpl balanceService ;

      @MockBean
      WebClient webClient;

      @MockBean
      ObjectMapper objectMapper;



      @Mock
      protected BalancesClient balancesClient;
      @Mock
        ConsentService consentService;
      @Mock
      protected ValidationService validationService;

      AutoCloseable autoCloseable;



    @Mock
      protected Errors errors;

   /* @Value("#{${bianurls}}")
    private Map<String, String> bianUrlMap;*/

    @Value("${auth.url}")
      String authurl;

      protected Map<String, String> authorizedAccounts;

    protected Map<String, String> bianUrlMap;


      protected List<String> bianUrls;




    // AutoCloseable autoCloseableb


      String consentId;


      protected List<OBReadBalance.Balance> obBalanceList;

      OBReadBalance.Balance obBalance;

//    @Value("${bianurls}")
//    private Map<String, String> bianUrlMap;


    @BeforeEach
    public void setUp() {
      balanceService  = new BalanceServiceImpl(balancesClient, consentService, validationService);
        MockitoAnnotations.openMocks(this);
        authorizedAccounts = new HashMap<>();
        authorizedAccounts.put("22289", "PersonalCurrentAccount");
        authorizedAccounts.put("31820", "BusinessCurrentAccount");
        authorizedAccounts.put("41820", "BusinessCreditCard");
        bianUrlMap = new HashMap<>();
        bianUrlMap.put("PersonalCurrentAccount", "http://localhost:8080/balances/api/v1/mock/currentAccbalances");
        bianUrlMap.put("BusinessCurrentAccount", "http://localhost:8080/balances/api/v1/mock/corporateAccBalances");
        bianUrlMap.put("BusinessCreditCard", "http://localhost:8080/balances/api/v1/mock/creditCardAccBalances");
        bianUrls = new ArrayList<>();
        bianUrls.add("http://localhost:8080/balances/api/v1/mock/currentAccbalances");
        bianUrls.add("http://localhost:8080/balances/api/v1/mock/corporateAccBalances");
        bianUrls.add("http://localhost:8080/balances/api/v1/mock/creditCardAccBalances");
        obBalanceList = JsonUtil.toObjectFromFileBalanceList(OBReadBalance.Balance.class);
        obBalance = JsonUtil.toObjectFromFileBalance(OBReadBalance.Balance.class).get(0);
        consentId = "12345";
    }

    @AfterEach
    public void free(){
        obBalanceList = null;
        obBalance = null;

    }


}
