package com.ob.tsb.transactions.service;

import com.ob.tsb.transactions.model.response.TransactionsResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<ResponseEntity<TransactionsResponse>> getTransactionById(String accountId);
}
