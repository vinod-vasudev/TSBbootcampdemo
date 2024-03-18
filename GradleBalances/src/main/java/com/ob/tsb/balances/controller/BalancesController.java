package com.ob.tsb.balances.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.api.BalancesApi;
import com.ob.tsb.balances.client.ConsentClient;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.AuthService;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.service.ConsentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.ob.tsb.balances.util.ApplicationConstants.BULK_HEAD_FALLBACK_MSG;
import static com.ob.tsb.balances.util.ApplicationConstants.RATE_LIMIT_FALLBACK_MSG;

/**
 * Controller class for handling balances-related endpoints.
 * Implements the BalancesApi interface.
 */
@RestController
@SecurityRequirement(name="jwt")
@Slf4j
public class BalancesController implements BalancesApi {

    private final BalanceService balanceService;
    private final ConsentService consentService;

    /**
     * Constructs a new {@code BalancesController} with the specified {@link BalanceService} and {@link ConsentService} .
     *
     * @param balanceService The service for interacting with balance-related functionalities.
     * @param consentService The service for interacting with consent-related functionalities.
      */
    public BalancesController(BalanceService balanceService, ConsentService consentService){
        this.balanceService = balanceService;
        this.consentService = consentService;
    }


    /**
     * Handles health check requests.
     *
     * @param exchange The ServerWebExchange object containing the request and response information.
     * @return A Mono emitting ResponseEntity with health status.
     */
    @Override
    @RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    @Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<String>> healthCheck(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Health is good"));
    }


    /**
     * Handles requests to retrieve balances information by ID.
     *
     * @param accountId       The ID of the balances to retrieve.
     * @return A Mono emitting ResponseEntity with balances response.
     */
   @Override
    //@RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
   // @Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<OBReadBalance>> balancesById(String accountId, ServerWebExchange exchange) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        String authorization = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        String consentId = consentService.getConsentId(authorization);
       return balanceService.getBalanceById(consentId, accountId).map(ResponseEntity::ok);
    }

    /**
     * Handles requests to retrieve balances information.
     *
     * @param exchange         The ServerWebExchange object containing the request and response information.
     * @return A Mono emitting ResponseEntity with balances response.
     */
    @Override
    //@RateLimiter(name = "balanceRateLimit", fallbackMethod = "rateLimitFallbackMethod")
    ///@Bulkhead(name = "balanceBulkheadInstance", fallbackMethod = "bulkheadFallback")
    public Mono<ResponseEntity<OBReadBalance>> balances(ServerWebExchange exchange) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        String authorization = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        String consentId = consentService.getConsentId(authorization);
        return balanceService.getBalances(consentId).map(ResponseEntity::ok);
    }


    /**
     * Fallback method for throwable in case of many request failure.
     * Logs the failure and returns too many request  response.
     * @param t The throwable indicating request failure.
     * @return ResponseEntity with many request status.
     */
    public Mono<ResponseEntity<String>> rateLimitFallbackMethod(Throwable t) {
        log.info(" Rate limit fall back " + t.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(RATE_LIMIT_FALLBACK_MSG));
    }

    /**
     * Fallback method for throwable in case of many request failure.
     * Logs the failure and returns Too many request - No further calls are accepted.
     * @param t The throwable indicating request failure.
     * @return ResponseEntity with many request status.
     */
    public Mono<ResponseEntity<String>> bulkheadFallback(Throwable t) {
        log.info("Bulkhead applied no further calls are accepted" + t.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(BULK_HEAD_FALLBACK_MSG));
    }

}
