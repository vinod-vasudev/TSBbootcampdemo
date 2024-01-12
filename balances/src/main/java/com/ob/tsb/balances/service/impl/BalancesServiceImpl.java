package com.ob.tsb.balances.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.model.response.BalanceByIdResponse;
import com.ob.tsb.balances.model.response.BalancesResponse;
import com.ob.tsb.balances.service.BalancesService;
import com.ob.tsb.balances.util.HeadersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;

@Service
@Slf4j
public class BalancesServiceImpl implements BalancesService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    public BalancesServiceImpl(WebClient webClient, ObjectMapper objectMapper){

        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }



    /*@Value("${api.max-attempt}")
    private Integer maxAttempt;
    @Value("${api.delay-millis}")
    private Integer delayMillis;*/



    @Override
    public Mono<ResponseEntity<BalancesResponse>> getBalanceInfo()  {
       /* HttpHeaders httpHeaders = HeadersUtil.defaultHeader();
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
                .defaultIfEmpty(ResponseEntity.notFound().build());*/
        try{
            BalancesResponse balancesResponse = objectMapper.readValue(new File("src/main/resources/openApi/fakeResponse.json"), BalancesResponse.class);
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(balancesResponse));
        } catch (Exception e){
            /// log.error(" Error ", e);
        }

        return null;
    }

    @Override
    public Mono<ResponseEntity<BalanceByIdResponse>> getBalanceByAccountId(Long accountId) {
        /*HttpHeaders httpHeaders = HeadersUtil.defaultHeader();
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
                .defaultIfEmpty(ResponseEntity.notFound().build());*/

        try{
            BalanceByIdResponse balanceByIdResponse = objectMapper.readValue(new File("src/main/resources/openApi/fakeResponseById.json"), BalanceByIdResponse.class);
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(balanceByIdResponse));
        } catch (Exception e){
            /// log.error(" Error ", e);
        }

        return null;
    }
}
