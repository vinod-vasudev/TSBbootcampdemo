package com.ob.tsb.balances.controller;

import com.ob.tsb.balances.api.BalancesApi;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.response.BalanceByIdResponse;
import com.ob.tsb.balances.model.response.BalancesResponse;
import com.ob.tsb.balances.service.BalanceService;
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

import static com.ob.tsb.balances.util.ApplicationConstants.BULK_HEAD_FALLBACK_MSG;
import static com.ob.tsb.balances.util.ApplicationConstants.RATE_LIMIT_FALLBACK_MSG;

@RestController
@Slf4j
public class BalancesController implements BalancesApi {

   // private final RateLimiterRegistry registry;

    private final BalanceService balanceService;
    public BalancesController(BalanceService balanceService){ //RateLimiterRegistry registry,
      //  this.registry = registry;
        this.balanceService = balanceService;
    }

    @Override
    @RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    @Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<String>> healthCheck(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Health is good"));
    }

    @ExceptionHandler(CustomException.class)
    @Override
    @RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    @Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<BalancesResponse>> balancesById(String accountId, ServerWebExchange exchange) {
        return balanceService.getBalanceById(accountId);
    }


    @ExceptionHandler(CustomException.class)
    @Override
    @RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    @Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")

    public Mono<ResponseEntity<BalancesResponse>> balances(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept, ServerWebExchange exchange) {
        return balanceService.getBalances();
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
