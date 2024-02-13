package com.ob.tsb.balances.controller;

import com.ob.tsb.balances.api.BalancesApi;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.exception.ObCustomException;
import com.ob.tsb.balances.model.errors.ErrorCode;
import com.ob.tsb.balances.model.errors.ObError;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.service.ConsentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ob.tsb.balances.util.ApplicationConstants.BULK_HEAD_FALLBACK_MSG;
import static com.ob.tsb.balances.util.ApplicationConstants.RATE_LIMIT_FALLBACK_MSG;

@RestController
@SecurityRequirement(name="jwt")
@Slf4j
public class BalancesController implements BalancesApi {

    private final BalanceService balanceService;
    private final ConsentService consentService;

    public BalancesController(BalanceService balanceService, ConsentService consentService){
        this.balanceService = balanceService;
        this.consentService = consentService;
    }


    @Override
    @RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    @Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<String>> healthCheck(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Health is good"));
    }



   @Override
    //@RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
   // @Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<?>> balancesById(String accountId, ServerWebExchange exchange) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        String authorization = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        String consentId = consentService.getConsentId(authorization);
       return balanceService.getBalanceById(consentId, accountId).map(res -> ResponseEntity.ok(res));
    }


    @Override
    //@RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    ///@Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<OBReadBalance>> balances(ServerWebExchange exchange) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        String authorization = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        String consentId = consentService.getConsentId(authorization);
        return balanceService.getBalances(consentId).map(res -> ResponseEntity.ok(res));
    }



    public Mono<ResponseEntity<?>> rateLimitFallbackMethod(Throwable t) {
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(RATE_LIMIT_FALLBACK_MSG));
    }

    public Mono<ResponseEntity<?>> bulkheadFallback(Throwable t) {
        log.info("Bulkhead applied no further calls are accepted");
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(BULK_HEAD_FALLBACK_MSG));
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
