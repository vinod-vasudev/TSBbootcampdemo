package com.ob.tsb.balances.service.impl;

import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.setup.BaseConsentClientMockedTest;
import com.ob.tsb.balances.util.ApplicationConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


class ConsentServiceImplTest extends BaseConsentClientMockedTest {



    @Test
    @DisplayName("Success - Consent API")
    void getBalancesConsentsTest() throws IOException {

        ReflectionTestUtils.setField(consentService, "consentClient", consentClient);
        ReflectionTestUtils.setField(consentService, "objectMapper", objectMapper);
        ReflectionTestUtils.setField(consentService, "authService", authService);
        Mockito.when(objectMapper.readValue(anyString(), any(com.fasterxml.jackson.core.type.TypeReference.class))).thenReturn(consentDtoList);
        Mockito.when(objectMapper.readValue(anyString(), any(Class.class))).thenReturn(jsonNode);
        ConsentDto  consentDto = consentService.getBalancesConsent(consentId);
        assertNotNull(consentDto);
    }

    @Test
    @DisplayName("Success - ConsentById API")
    void getBalancesByIdConsentsTest() throws IOException {

        ReflectionTestUtils.setField(consentService, "consentClient", consentClient);
        ReflectionTestUtils.setField(consentService, "objectMapper", objectMapper);
        ReflectionTestUtils.setField(consentService, "authService", authService);
        Mockito.when(objectMapper.readValue(anyString(), any(com.fasterxml.jackson.core.type.TypeReference.class))).thenReturn(consentDtoList);
        Mockito.when(objectMapper.readValue(anyString(), any(Class.class))).thenReturn(jsonNode);
        ConsentDto consentDto = consentService.getBalancesConsent(consentId);
        assertNotNull(consentDto);
    }

    @Test
    @DisplayName("Success - ConsentId API")
    void getConsentIdTest() throws IOException {

        ReflectionTestUtils.setField(consentService, "consentClient", consentClient);
        ReflectionTestUtils.setField(consentService, "objectMapper", objectMapper);
        ReflectionTestUtils.setField(consentService, "authService", authService);
        String token1 = authService.generateJwtToken(consentId);
        String token = consentService.getConsentId(token1);
        assertNotNull(token);
        Mockito.when(consentService.validateConsents(List.of(consentId), token1)).thenReturn(true);

    }


    @Test
    @DisplayName("Failure - Fallback method")
    void testFallBackMethod() {
        ResponseEntity<String> responseEntity=consentService.consentServiceCbFallback();
        Mono<ResponseEntity<String>> fallback=Mono.just(responseEntity);

        StepVerifier.create(fallback)
                .expectNextMatches(stringResponseEntity ->
                        stringResponseEntity.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
                                && Objects.equals(stringResponseEntity.getBody(), ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG))
                .expectComplete()
                .verify();

    }



}