package com.ob.tsb.transactions.controller;

import com.ob.tsb.transactions.api.TransactionsApi;
import com.ob.tsb.transactions.exception.CustomException;
import com.ob.tsb.transactions.model.response.TransactionsResponse;
import com.ob.tsb.transactions.service.TransactionService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.ob.tsb.transactions.util.ApplicationConstants.BULK_HEAD_FALLBACK_MSG;
import static com.ob.tsb.transactions.util.ApplicationConstants.RATE_LIMIT_FALLBACK_MSG;

@RestController
@Slf4j
public class TransactionsController implements TransactionsApi {

   // private final RateLimiterRegistry registry;

    private final TransactionService transactionService;
    public TransactionsController(TransactionService transactionService){ //RateLimiterRegistry registry,
      //  this.registry = registry;
        this.transactionService = transactionService;
    }

    @Override
    @RateLimiter(name = "transactionRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    @Bulkhead(name = "transactionBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<String>> healthCheck(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Health is good"));
    }

    @ExceptionHandler(CustomException.class)
    @Override
    @RateLimiter(name = "transactionRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    @Bulkhead(name = "transactionBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<TransactionsResponse>> transactionsById(String accountId, ServerWebExchange exchange) {
        return transactionService.getTransactionById(accountId);
    }


   public ResponseEntity rateLimitFallbackMethod(String token, RequestNotPermitted requestNotPermitted) {
        log.info("Auth Service Fallback method called.");
        log.info("RequestNotPermitted exception message: {}", requestNotPermitted.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Retry-After", "60s");

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(responseHeaders)
                .body(RATE_LIMIT_FALLBACK_MSG);
    }

    public ResponseEntity bulkheadFallback(String token, RequestNotPermitted requestNotPermitted) {
        log.info("Bulkhead applied no further calls are accepted");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(BULK_HEAD_FALLBACK_MSG);
    }

    /*@PostConstruct
    public void postConstruct() {
        io.github.resilience4j.ratelimiter.RateLimiter.EventPublisher eventPublisher = registry
                .rateLimiter("authRateLimiterEvents")
                .getEventPublisher();
        eventPublisher.onEvent(event -> System.out.println("Auth Rate Limit - On Event. Event Details: " + event));
        eventPublisher.onSuccess(event -> System.out.println("Auth Rate Limit - On Success. Event Details: " + event));
        eventPublisher.onFailure(event -> System.out.println("Auth Rate Limit - On Failure. Event Details: " + event));
    }*/

}
