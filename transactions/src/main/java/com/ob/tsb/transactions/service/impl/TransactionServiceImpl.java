package com.ob.tsb.transactions.service.impl;


import com.ob.tsb.transactions.client.TransactionsClient;
import com.ob.tsb.transactions.model.response.TransactionsResponse;
import com.ob.tsb.transactions.service.TransactionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import reactor.core.publisher.Mono;

import static com.ob.tsb.transactions.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Value("${transactionsById.url}")
    private String transactionsByIdUrl;

    private final TransactionsClient transactionsClient;

    public TransactionServiceImpl(TransactionsClient transactionsClient) {
        this.transactionsClient = transactionsClient;
    }


   @Override
    @CircuitBreaker(name = "transactionService", fallbackMethod = "transactionServiceCbFallback")
    public Mono<ResponseEntity<TransactionsResponse>> getTransactionById(String accountId) {
        String url = transactionsByIdUrl.replace("{AccountId}", accountId);
        return transactionsClient.processTransactionApiRequest(url, HttpMethod.GET, new HttpHeaders(), new LinkedMultiValueMap());
     }

    public ResponseEntity transactionServiceCbFallback(String token, RequestNotPermitted requestNotPermitted) {
        log.info("Transaction Service Fallback method called.");
        log.info("RequestNotPermitted exception message: {}", requestNotPermitted.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG);
    }



}
