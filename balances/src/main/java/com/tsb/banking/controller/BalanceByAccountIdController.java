package com.tsb.banking.controller;

import com.tsb.banking.api.AccountsApi;
import com.tsb.banking.model.BalanceByIdResponse;
import com.tsb.banking.model.BalancesResponse;
import com.tsb.banking.service.BalancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class BalanceByAccountIdController implements AccountsApi {

    @Autowired
    private BalancesService balancesServices;

    @Override
    public Mono<ResponseEntity<BalanceByIdResponse>> balancesById(Long accountId, ServerWebExchange exchange) {
        return balancesServices.getBalanceByAccountId(accountId);
    }
}
