package com.ob.tsb.balances.service;

import com.ob.tsb.balances.model.ob.OBReadBalance;
import reactor.core.publisher.Mono;
/**
 * Interface for balances-related operations.
 */
public interface BalanceService {

    /**
     * Retrieves balances information based on the provided consent ID.
     *
     * @param consentId The consent ID associated with the request.
     * @return A Mono emitting ResponseEntity with balances response.
     */
    Mono<OBReadBalance> getBalances(String consentId);

    /**
     * Retrieves balances information by ID based on the provided consent ID, account ID.
     *
     * @param consentId The consent ID associated with the request.
     * @param accountId The ID of the balances to retrieve.
     * @return A Mono emitting ResponseEntity with balances response.
     */
    Mono<OBReadBalance> getBalanceById(String consentId, String accountId);
}
