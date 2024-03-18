package com.ob.tsb.balances.setup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.client.AuthClient;
import com.ob.tsb.balances.client.ConsentClient;
import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.service.AuthService;
import com.ob.tsb.balances.service.impl.AuthServiceImpl;
import com.ob.tsb.balances.service.impl.ConsentServiceImpl;
import com.ob.tsb.balances.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource(locations = "classpath:application-local.yml")
public class BaseConsentClientMockedTest {

    @InjectMocks
    protected ConsentServiceImpl consentService;
    @Mock
    protected ConsentClient consentClient;
    @Mock
    protected AuthClient authClient;

    protected Key key;

    @Mock
    protected ObjectMapper objectMapper;
    @Mock
    protected AuthService authService;
    protected Map<String, String> authorizedAccounts;
    protected String consentId;
    protected JsonNode jsonNode;
    protected List<ConsentDto> consentDtoList;
    protected String accountId;

    @BeforeEach
    public void setUp() throws IOException, NoSuchAlgorithmException {
        consentService = new ConsentServiceImpl(consentClient, objectMapper, authService);
        key = generateSecureKey();
        authService = new AuthServiceImpl(authClient, key);

        authorizedAccounts = new HashMap<>();
        authorizedAccounts.put("31820", "PersonalCurrentAccount");

        jsonNode = JsonUtil.toJsonNode();
        consentDtoList = JsonUtil.toJsonNodeData();

        consentId = "12345";
        accountId = "31820";
    }

    private Key generateSecureKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        return keyGenerator.generateKey();
    }


}
