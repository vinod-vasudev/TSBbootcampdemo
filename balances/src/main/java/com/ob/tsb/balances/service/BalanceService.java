package com.ob.tsb.balances.service;

import com.ob.tsb.balances.model.response.BalancesResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BalanceService {
    Mono<ResponseEntity<BalancesResponse>> getBalances();

    Mono<ResponseEntity<BalancesResponse>> getBalanceById(String accountId);
}
