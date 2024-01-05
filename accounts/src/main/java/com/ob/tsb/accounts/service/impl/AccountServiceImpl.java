package com.ob.tsb.accounts.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.accounts.exception.ResourceNotFoundException;
import com.ob.tsb.accounts.exception.UnauthorizedException;
import com.ob.tsb.accounts.response.AccountsResponse;
import com.ob.tsb.accounts.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;

import static org.springframework.http.HttpStatus.*;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    public AccountServiceImpl(WebClient webClient, ObjectMapper objectMapper){

        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<ResponseEntity<AccountsResponse>> getAccounts() {

        //have to call TSB API to get actual accounts response
        /*return webClient.get().uri("tsb/accounts").retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {handle4xxClientError(clientResponse)})
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {handle5xxClientError(clientResponse)})
                .bodyToMono(AccountsResponse.class);*/


        try{
            AccountsResponse accountsResponse = objectMapper.readValue(new File("src/main/resources/openApi/fakeResponse.json"), AccountsResponse.class);
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(accountsResponse));
        } catch (Exception e){
           // log.error(" Error ", e);
        }

        return null;
    }

    @Override
    public Mono<ResponseEntity<AccountsResponse>> getAccountById(String accountId) {
        try{
            AccountsResponse accountsResponse = objectMapper.readValue(new File("src/main/resources/openApi/fakeResponseAccId.json"), AccountsResponse.class);
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(accountsResponse));
        } catch (Exception e){
           /// log.error(" Error ", e);
        }

        return null;
    }

    private Mono<? extends  Throwable> handle5xxClientError(ClientResponse clientResponse) {
        HttpStatusCode httpStatusCode = clientResponse.statusCode();
        switch (httpStatusCode){
            case INTERNAL_SERVER_ERROR -> {
                return Mono.error(new ResourceNotFoundException(httpStatusCode, "Client API have Error" ));
            }
            default -> throw new IllegalStateException("Unexpected value: " + httpStatusCode);
        }
    }

    private Mono<? extends  Throwable>  handle4xxClientError(ClientResponse clientResponse) {
        HttpStatusCode httpStatusCode = clientResponse.statusCode();
        switch (httpStatusCode){
            case NOT_FOUND -> {
                return Mono.error(new ResourceNotFoundException(httpStatusCode, "Client API Not Found" ));
            }
            case UNAUTHORIZED -> {
                return Mono.error( new UnauthorizedException(httpStatusCode, "Authorization Failed"));
            }
            default -> throw new IllegalStateException("Unexpected value: " + httpStatusCode);
        }
    }


}
