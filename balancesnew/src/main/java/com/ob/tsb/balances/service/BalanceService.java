package com.ob.tsb.balances.service;

import com.ob.tsb.balances.model.ob.OBReadBalance;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BalanceService {
    Mono<OBReadBalance> getBalances(String consentId);
    Mono<OBReadBalance> getBalanceById(String consentId, String accountId);
}
