package com.ob.tsb.balances.service;

import com.ob.tsb.balances.model.ob.OBReadBalance;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BalanceService {
    Mono<ResponseEntity<Object>> getBalances(String consentId);
    Mono<ResponseEntity<Object>> getBalanceById(String consentId, String accountId);
}
