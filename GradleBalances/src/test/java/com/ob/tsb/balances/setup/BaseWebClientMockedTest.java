package com.ob.tsb.balances.setup;

import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.ConsentService;
import com.ob.tsb.balances.service.ValidationService;
import com.ob.tsb.balances.util.JsonUtil;
import jakarta.validation.Validation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;
import static com.ob.tsb.balances.util.ApplicationConstants.OB_SPECIFICATION_URL;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource(locations = "classpath:application-local.yml")
public class BaseWebClientMockedTest {

    @Mock
    protected ConsentService consentService;
    @Mock
    protected BalancesClient balancesClient;
    @Mock
    protected ValidationService validationService;
    @Mock
    protected Errors errors;
    protected ConsentDto consentDto;

    protected ConsentDto noAuthorisedAccountConsentDto;

    protected ConsentDto unAuthroizedConsentDto;
    protected ConsentDto permissionNotGrantedConsentDto;

    protected List<ConsentDto.Accounts> accountsList;
    protected Map<String, String> authorizedAccounts;
    protected Map<String, String> bianUrlMap;
    protected List<String> bianUrls;
    protected String consentId;
    protected String accountId;
    protected List<OBReadBalance.Balance> obBalanceList;
    protected List<OBReadBalance.Balance> obBalance;
    protected OBReadBalance obReadBalance;
    protected String token ;

    protected List<OBReadBalance.Balance> balancesList;

    protected  Errors errorsList;

    protected Mono<ResponseEntity<String>> responseEntityMonoString;

    protected Throwable throwable;

    @BeforeEach
    public void setUp() {

        token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJUU0IiLCJzdWIiOiJUZXN0aW5nVG9rZW4iLCJjb25zZW50SWQiOiIxMjM0NSIsImlhdCI6MTcwOTAzOTM2OSwiZXhwIjoyNTczMDM5MzY5fQ.JLXcjHRoK8FwkfbYyb4FWexdv3k1lNJsOv-Ko7ee3G4";
     //   balanceService = new BalanceServiceImpl(balancesClient, consentService, validationService);
        authorizedAccounts = new HashMap<>();
        accountsList = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        permissions.add("ReadBalances");


        ConsentDto.Accounts account1 = new ConsentDto.Accounts(
                "22289", "Enabled", "2018-01-01T06:06:06+00:00", "GBP", "Personal","Household", "CurrentAccount", "",  null);

        ConsentDto.Accounts account2 = new ConsentDto.Accounts(
                "31820", "Enabled", "2018-01-01T06:06:06+00:00", "GBP", "Business","Household", "CurrentAccount", "",  null);

        accountsList.add(account1);
        accountsList.add(account2);


        consentDto = new ConsentDto("12345", "Authorised", permissions, accountsList);

        noAuthorisedAccountConsentDto = new ConsentDto("12345", "Authorised", permissions, new ArrayList<ConsentDto.Accounts> ());

        unAuthroizedConsentDto = new ConsentDto("12345", "Awaiting", permissions, accountsList);

        permissionNotGrantedConsentDto =  new ConsentDto("12345", "Authorised", new ArrayList<>(), accountsList);

        authorizedAccounts.put("31820", "PersonalCurrentAccount");
        bianUrlMap = new HashMap<>();
        bianUrlMap.put("PersonalCurrentAccount", "https://ob-api.ob-cluster.mywv.p1.openshiftapps.com/balances/api/v1/mock/currentAccBalances");
        bianUrlMap.put("BusinessCurrentAccount", "https://ob-api.ob-cluster.mywv.p1.openshiftapps.com/balances/api/v1/mock/corporateAccBalances");
        bianUrlMap.put("BusinessCreditCard", "https://ob-api.ob-cluster.mywv.p1.openshiftapps.com/balances/api/v1/mock/creditCardAccBalances");
        bianUrls = new ArrayList<>();
        bianUrls.add("https://ob-api.ob-cluster.mywv.p1.openshiftapps.com/balances/api/v1/mock/currentAccbalances");
        bianUrls.add("https://ob-api.ob-cluster.mywv.p1.openshiftapps.com/balances/api/v1/mock/corporateAccBalances");
        bianUrls.add("https://ob-api.ob-cluster.mywv.p1.openshiftapps.com/balances/api/v1/mock/creditCardAccBalances");
        obBalanceList = JsonUtil.toObjectFromFileBalanceList(OBReadBalance.Balance.class);
        obBalance = JsonUtil.toObjectFromFileBalance(OBReadBalance.Balance.class);
        consentId = "12345";
        accountId = "31820";
        obReadBalance = new OBReadBalance(new OBReadBalance.Data(obBalanceList), new OBReadBalance.Links(OB_SPECIFICATION_URL), new OBReadBalance.Meta(1));
        responseEntityMonoString =  Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG));

        throwable = new Throwable(" some error ");

        balancesList= List.of(new OBReadBalance.Balance("1221",
                new OBReadBalance.Amount("sdf123.45","%$GBP"),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("sdf5000.00","GBP23")),"Available"))));




    }

    protected Errors validateObBalance(List<OBReadBalance.Balance> obBalance) {
        SpringValidatorAdapter validator = new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());
        Errors errors = new BeanPropertyBindingResult(obBalance, "balances");
        obBalance.forEach(balance -> validator.validate(balance, errors, "balance[" + obBalance.indexOf(balance) + "]"));
        return errors;
    }

    @AfterEach
    public void free() {
        obBalanceList = null;
        obBalance = null;

    }


}
