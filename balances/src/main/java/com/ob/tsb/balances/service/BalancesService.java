package com.ob.tsb.balances.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ob.tsb.balances.model.response.BalanceByIdResponse;
import com.ob.tsb.balances.model.response.BalancesResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BalancesService {

    Mono<ResponseEntity<BalancesResponse>> getBalanceInfo(

    );

    Mono<ResponseEntity<BalanceByIdResponse>> getBalanceByAccountId(Long accountId);

}
