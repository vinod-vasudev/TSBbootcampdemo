package com.ob.tsb.balances.setup;


import com.ob.tsb.balances.client.AuthClient;
import com.ob.tsb.balances.service.AuthService;
import com.ob.tsb.balances.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource(locations = "classpath:application-local.yml")
public class BaseWebAuthClientMokedTest {
    @Mock
    protected AuthService authService;
    @Mock
    protected AuthClient authClient;
    protected Key key;

    protected Throwable throwable;


    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        key= generateSecureKey();
        authService = new AuthServiceImpl(authClient,key);
        throwable = new Throwable(" some error ");
    }

    private Key generateSecureKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
        return keyGenerator.generateKey();
    }

}
