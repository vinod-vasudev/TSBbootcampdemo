package com.tsb.banking.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsb.banking.exception.CustomException;
import com.tsb.banking.mapper.CustomMapper;
import com.tsb.banking.model.BalanceByIdResponse;
import com.tsb.banking.model.BalancesResponse;
import com.tsb.banking.model.BalancesResponseDataBalanceInner;
import com.tsb.banking.service.BalancesService;
import com.tsb.banking.util.HeadersUtil;
import com.tsb.banking.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BalancesServiceImpl implements BalancesService {

    private final WebClient webClient;


    @Value("${api.max-attempt}")
    private Integer maxAttempt;
    @Value("${api.delay-millis}")
    private Integer delayMillis;

    public BalancesServiceImpl(WebClient webClient){
        this.webClient =webClient;
    }

    @Override
    public Mono<ResponseEntity<BalancesResponse>> getBalanceInfo()  {
        HttpHeaders httpHeaders = HeadersUtil.defaultHeader();
        httpHeaders.add("x-fapi-auth-date", "Sun, 10 Sep 2017 19:43:31 GMT");
        httpHeaders.add("x-fapi-customer-ip-address", "104.25.212.99");
        httpHeaders.add("x-fapi-interaction-id", "93bac548-d2de-4546-b106-880a5018460d");
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);


        return webClient.get()
                .uri("http://localhost:3030/v1/balances")
                .headers(head -> head.addAll(httpHeaders)).headers(h -> h.setBearerAuth("Bearer Az90SAOJklae"))
                .retrieve()
                .toEntity(BalancesResponse.class)
                .map(responseEntity -> ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<BalanceByIdResponse>> getBalanceByAccountId(Long accountId) {
        HttpHeaders httpHeaders = HeadersUtil.defaultHeader();
        httpHeaders.add("x-fapi-auth-date", "Sun, 10 Sep 2017 19:43:31 GMT");
        httpHeaders.add("x-fapi-customer-ip-address", "104.25.212.99");
        httpHeaders.add("x-fapi-interaction-id", "93bac548-d2de-4546-b106-880a5018460d");
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);


        return webClient.get()
                .uri("http://localhost:3030/v1/accounts/{accountId}/balances",accountId)
                .headers(head -> head.addAll(httpHeaders))
                .headers(h -> h.setBearerAuth("Bearer Az90SAOJklae"))
                .retrieve()
                .toEntity(BalanceByIdResponse.class)
                .map(responseEntity -> ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
