package com.ob.tsb.balances.service.impl;


import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.model.response.BalancesResponse;
import com.ob.tsb.balances.service.BalanceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import reactor.core.publisher.Mono;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;

@Service
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    @Value("${balances.url}")
    private String balancesUrl;//mock later is TSB

    @Value("${balancesById.url}")
    private String balancesByIdUrl;

    private final BalancesClient balancesClient;

    public BalanceServiceImpl(BalancesClient balancesClient) {
        this.balancesClient = balancesClient;
    }


    @Override
    @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<ResponseEntity<BalancesResponse>> getBalances() {
          return balancesClient.processBalanceApiRequest(balancesUrl, HttpMethod.GET, new HttpHeaders(), new LinkedMultiValueMap()); //request body
    }

    @Override
    @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<ResponseEntity<BalancesResponse>> getBalanceById(String accountId) {
        String url = balancesByIdUrl.replace("{AccountId}", accountId);
        return balancesClient.processBalanceApiRequest(url, HttpMethod.GET, new HttpHeaders(), new LinkedMultiValueMap());
     }

    public ResponseEntity balanceServiceCbFallback(String token, RequestNotPermitted requestNotPermitted) {
        log.info("Balance Service Fallback method called.");
        log.info("RequestNotPermitted exception message: {}", requestNotPermitted.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG);
    }



}
