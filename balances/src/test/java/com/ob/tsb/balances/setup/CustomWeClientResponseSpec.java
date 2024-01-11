package com.tsb.banking.balances.setup;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract  class CustomWeClientResponseSpec implements WebClient.ResponseSpec {

    public  abstract  HttpStatus getStatus();
    @Override
    public WebClient.ResponseSpec onStatus(Predicate<HttpStatusCode> statusPredicate, Function<ClientResponse, Mono<? extends Throwable>> exceptionFunction) {
        if (statusPredicate.test(this.getStatus())) exceptionFunction.apply(ClientResponse.create(HttpStatus.BAD_REQUEST).build()).block();
        return this;
    }

}
