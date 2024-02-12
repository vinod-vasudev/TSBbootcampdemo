package com.ob.tsb.balances.service.impl;


import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.setup.BaseWebClientMockedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;



//@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
//@TestPropertySource(locations = "classpath:application-local.yml")
class BalanceServiceImplTest extends BaseWebClientMockedTest {
    /**
     *
     */
    @Test
    void getBalanceDetails() {



        ReflectionTestUtils.setField(balanceService, "bianUrlMap",bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient",balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService",validationService);




        assertNotEquals(null, authorizedAccounts);
        assertNotEquals(Collections.EMPTY_LIST, authorizedAccounts);
        List<String> actualBianUrls = authorizedAccounts.values().stream().map(bianUrlMap::get).filter(Objects::nonNull).collect(Collectors.toList());
        assertEquals(bianUrls.size(), actualBianUrls.size());
        Mono<List<OBReadBalance.Balance>> listMono = Mono.just(obBalanceList);
        Mockito.when(balancesClient.processBalanceApiRequest(any(),any(),any(),any(),any()))
                .thenReturn(listMono);
        listMono.block();
        Mockito.when(validationService.validateObBalance(listMono.block())).thenReturn(errors);
        OBReadBalance res =  balanceService.getBalanceDetails(authorizedAccounts).block();
        assertNotNull(res);
        //assertThrows(ValidationException.class, () -> {balanceService.getBalanceDetails(any());});
/*
       Mono<List<OBReadBalance.Balance>> actualObBalanceList = balancesClient.processBalanceApiRequest(bianUrls, HttpMethod.GET, new HttpHeaders(), authorizedAccounts, bianUrlMap);

       assertNotNull(actualObBalanceList);
        assertEquals(listMono, actualObBalanceList);*/


     /*  Errors actualErrors =  validationService.validateObBalance(actualObBalanceList.block());
        assertEquals(errors, actualErrors);*/
        ///assertNotEquals(errors.hasErrors(), false);

        ///Mockito.verify( balanceService.getBalanceDetails(authorizedAccounts)).block();
       /// assertNotNull(actualObBalance.block().getBody().Data());


    }
}