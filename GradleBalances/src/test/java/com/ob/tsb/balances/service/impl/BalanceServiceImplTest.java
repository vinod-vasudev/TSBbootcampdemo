package com.ob.tsb.balances.service.impl;


import com.ob.tsb.balances.exception.ObCustomException;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.setup.BaseWebClientMockedTest;
import com.ob.tsb.balances.util.ApplicationConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.ob.tsb.balances.util.ApplicationConstants.STATUS_AUTHORISED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;




class BalanceServiceImplTest extends BaseWebClientMockedTest {
    @InjectMocks
    BalanceServiceImpl balanceService;



    @Test
    @DisplayName("Success - Balance API")
    void getBalancesTest() {
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        assertEquals(consentDto.consentStatus(), STATUS_AUTHORISED);
        Mono<List<OBReadBalance.Balance>> listMono = Mono.just(obBalanceList);
        when(balancesClient.processBalanceApiRequest(any(), any(), any())).thenReturn(listMono);
        when(validationService.validateObBalance(listMono.block())).thenReturn(errors);
        OBReadBalance obReadBalanceActual = balanceService.getBalances(consentId).block();
        assertEquals(obBalanceList, obReadBalanceActual.Data().Balance());

    }

    @Test
    @DisplayName("Failure - Balance API")
    void getBalancesFailureTest() {
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        String consentStatus = "Awaiting";
        assertNotEquals(consentStatus, STATUS_AUTHORISED);
        Mono<List<OBReadBalance.Balance>> listMono = Mono.just(obBalanceList);
        when(balancesClient.processBalanceApiRequest(any(), any(), any())).thenReturn(listMono);
        when(validationService.validateObBalance(listMono.block())).thenReturn(errors);
        OBReadBalance obReadBalanceActual = balanceService.getBalances(consentId).block();
        assertEquals(obBalanceList, obReadBalanceActual.Data().Balance());

    }



    @Test
    @DisplayName("Success - Balance By Id API")
    void getBalanceByIdDetails() {
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        Mono<List<OBReadBalance.Balance>> listMono = Mono.just(obBalance);
        when(validationService.validateObBalance(listMono.block())).thenReturn(errors);
        when(balancesClient.processBalanceApiRequest(any(), any(), any())).thenReturn(listMono);
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        OBReadBalance obReadBalanceActual = balanceService.getBalanceById(consentId, accountId).block();
        assertEquals(obBalance, obReadBalanceActual.Data().Balance());
    }


    @Test
    @DisplayName("Failure - Fallback method")
    void testFallBackMethod() {
        StepVerifier.create(balanceService.balanceServiceCbFallback(throwable))
                .expectNextMatches(stringResponseEntity ->
                        stringResponseEntity.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
                && stringResponseEntity.getBody().equals(ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG))
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Failure - Consent Unauthorized exception test ")
    void testConsentNotAuthoriseException() {
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(unAuthroizedConsentDto);
        assertThrows(ObCustomException.class, ()->balanceService.getBalanceById(consentId, accountId).block()
        , "Expected getBalancesDetails to throw ObCustomException when consent is not authorised");
    }

    @Test
    @DisplayName("Failure - Consent permission is not Granted exception test ")
    void testConsentPermissionIsNotGrantedException() {
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(permissionNotGrantedConsentDto);
        assertThrows(ObCustomException.class, ()->balanceService.getBalanceById(consentId, accountId).block()
                , "Expected getBalancesDetails to throw ObCustomException when Consent permission is not Granted");
    }

    @Test
    @DisplayName("Failure - No account details found exception test ")
    void testAccountDetailNotFoundException(){
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        assertThrows(ObCustomException.class, ()->balanceService.getBalanceById(consentId, "31821").block()
                , "Expected getBalancesDetails to throw ObCustomException when No account details found for specific account Id");
    }

    @Test
    @DisplayName("Failure -No authorised account found exception test ")
    void testAuthorisedAccountsFoundException() {
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(noAuthorisedAccountConsentDto);
        assertThrows(ObCustomException.class, ()->balanceService.getBalanceById(consentId, "").block()
                , "Expected getBalancesDetails to throw ObCustomException when No account details found for specific account Id");
    }



    @Test
    @DisplayName("Failure - No  authorised accounts found (BIAN) exception test ")
    void testAuthorisedAccountFoundException() {
        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        Mono<List<OBReadBalance.Balance>> listMono = null;
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        when(balancesClient.processBalanceApiRequest(any(), any(), any())).thenReturn(listMono);
        assertThrows(ObCustomException.class, ()->balanceService.getBalanceById(consentId, "31820").block()
                , "Expected getBalancesDetails to throw ObCustomException when No account details found for specific account Id");
    }


    @Test
    @DisplayName("Failure - Schema Validation exception test")
    void getValidationExceptionTest() {

        ReflectionTestUtils.setField(balanceService, "bianUrlMap", bianUrlMap);
        ReflectionTestUtils.setField(balanceService, "balancesClient", balancesClient);
        ReflectionTestUtils.setField(balanceService, "validationService", validationService);
        when(consentService.getBalancesConsent(consentId)).thenReturn(consentDto);
        Mono<List<OBReadBalance.Balance>> listMono = Mono.just(balancesList);
        when(balancesClient.processBalanceApiRequest(any(), any(), any())).thenReturn(listMono);
        when(validationService.validateObBalance(listMono.block())).thenReturn( validateObBalance(balancesList));

        assertThrows(ObCustomException.class, ()->balanceService.getBalanceById(consentId, "31820").block()
                , "Expected getBalancesDetails to throw ObCustomException for  Schema Validation ");


    }










}