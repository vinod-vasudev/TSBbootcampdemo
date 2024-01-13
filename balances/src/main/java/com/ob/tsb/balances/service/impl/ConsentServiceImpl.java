package com.ob.tsb.balances.service.impl;

import com.ob.tsb.balances.client.ConsentClient;
import com.ob.tsb.balances.service.ConsentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;

@Service
@Slf4j
public class ConsentServiceImpl implements ConsentService {

     private final ConsentClient consentClient;

    public ConsentServiceImpl(ConsentClient consentClient) {
        this.consentClient = consentClient;
    }

    @Override
    @CircuitBreaker(name = "consentService", fallbackMethod = "consentServiceCbFallback")
    public boolean validateConsents(List<String> consents, String token) {
        return consentClient.validateConsent(consents, token);
    }

    public ResponseEntity consentServiceCbFallback(String token, RequestNotPermitted requestNotPermitted) {
        //log.info("Consent Service Fallback method called.");
       //log.info("RequestNotPermitted exception message: {}", requestNotPermitted.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG);
    }
}
