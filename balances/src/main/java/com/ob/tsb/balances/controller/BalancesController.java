package com.ob.tsb.balances.controller;

import com.ob.tsb.balances.api.BalancesApi;
import com.ob.tsb.balances.model.response.BalanceByIdResponse;
import com.ob.tsb.balances.model.response.BalancesResponse;
import com.ob.tsb.balances.service.BalancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class BalancesController implements BalancesApi {


    private final BalancesService balancesService;

    public BalancesController(BalancesService balancesService) {
        this.balancesService = balancesService;
    }

    @Override
    public Mono<ResponseEntity<String>> healthCheck(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Health is good"));
    }

    @Override
    public Mono<ResponseEntity<BalancesResponse>> balances(ServerWebExchange exchange) {
        //return BalancesApi.super.balances(exchange);
       return balancesService.getBalanceInfo();
    }

    @Override
    public Mono<ResponseEntity<BalanceByIdResponse>> balancesById(Long accountId, ServerWebExchange exchange) {
        return balancesService.getBalanceByAccountId(accountId);
    }
}
