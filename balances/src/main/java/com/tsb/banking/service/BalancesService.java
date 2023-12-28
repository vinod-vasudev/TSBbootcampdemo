package com.tsb.banking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tsb.banking.model.BalanceByIdResponse;
import com.tsb.banking.model.BalancesResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BalancesService {

    Mono<ResponseEntity<BalancesResponse>> getBalanceInfo(

    );

    Mono<ResponseEntity<BalanceByIdResponse>> getBalanceByAccountId(Long accountId);

}
