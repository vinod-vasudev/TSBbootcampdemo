package com.tsb.banking.controller;

import com.tsb.banking.api.BalanceApi;
import com.tsb.banking.model.BalancesResponse;
import com.tsb.banking.service.BalancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class BalancesController implements BalanceApi {

    @Autowired
    private BalancesService balancesServices;

    @Override
    public Mono<ResponseEntity<BalancesResponse>> balances(ServerWebExchange exchange) {
        return balancesServices.getBalanceInfo();
    }

    @Override
    public Mono<ResponseEntity<String>> healthCheck(ServerWebExchange exchange) {
        return  Mono.just(ResponseEntity.status(HttpStatus.OK).body("Health of get/balances is Good"));
    }
}
