package com.ob.tsb.accounts.controller;

import com.ob.tsb.accounts.api.AccountsApi;
import com.ob.tsb.accounts.response.AccountsResponse;
import com.ob.tsb.accounts.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class AccountsController implements AccountsApi {


    private final AccountService accountService;
    public AccountsController(AccountService accountService){
        this.accountService = accountService;
    }


    @Override
    public Mono<ResponseEntity<String>> healthCheck(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept, ServerWebExchange exchange) {
        //return AccountsApi.super.healthCheck(authorization, xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, accept, exchange);
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Health is good"));
    }

    @Override
    public Mono<ResponseEntity<AccountsResponse>> accounts(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept, ServerWebExchange exchange) {
        //return AccountsApi.super.accounts(authorization, xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, accept, exchange);
        return accountService.getAccounts();
    }

    @Override
    public Mono<ResponseEntity<AccountsResponse>> accountsById(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept, String accountId, ServerWebExchange exchange) {
        //return AccountsApi.super.accountsById(authorization, xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, accept, accountId, exchange);
        return accountService.getAccountById(accountId);
    }

}
