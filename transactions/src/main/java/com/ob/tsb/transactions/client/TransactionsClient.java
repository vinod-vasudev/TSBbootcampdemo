package com.ob.tsb.transactions.client;

import com.ob.tsb.transactions.exception.CustomException;
import com.ob.tsb.transactions.model.response.TransactionsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.ob.tsb.transactions.exception.ErrorConstants.AUTH_CLIENT_ERROR;
import static com.ob.tsb.transactions.exception.ErrorConstants.AUTH_SERVER_ERROR;
import static com.ob.tsb.transactions.exception.ErrorDesc.handle4xxClientError;
import static com.ob.tsb.transactions.exception.ErrorDesc.handle5xxClientError;

@Component
@Slf4j
public class TransactionsClient {
    private final WebClient webClient;


    public TransactionsClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ResponseEntity<TransactionsResponse>> processTransactionApiRequest(String url, HttpMethod httpMethod, HttpHeaders headers, LinkedMultiValueMap payload){
      log.info("url : "+ url);
      try{
          Mono<TransactionsResponse> transactionsResponseMono =  webClient.method(httpMethod).uri(url)
                  .headers(httpHeaders -> httpHeaders.addAll(headers))
                  .bodyValue(payload)
                  .retrieve()
                  .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> handle4xxClientError(AUTH_CLIENT_ERROR, clientResponse))
                  .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> handle5xxClientError(AUTH_SERVER_ERROR, clientResponse))
                  .bodyToMono(TransactionsResponse.class);
          return transactionsResponseMono.map(transactionsResponse ->
                  ResponseEntity.ok(transactionsResponse));
      }catch (Exception e){
          throw new CustomException(HttpStatusCode.valueOf(500), "Something went wrong");
      }

    }
}
