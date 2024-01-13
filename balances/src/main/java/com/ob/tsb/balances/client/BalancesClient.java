package com.ob.tsb.balances.client;

import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.response.BalancesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.ob.tsb.balances.exception.ErrorConstants.AUTH_CLIENT_ERROR;
import static com.ob.tsb.balances.exception.ErrorConstants.AUTH_SERVER_ERROR;
import static com.ob.tsb.balances.exception.ErrorDesc.handle4xxClientError;
import static com.ob.tsb.balances.exception.ErrorDesc.handle5xxClientError;

@Component
@Slf4j
public class BalancesClient {
    private final WebClient webClient;


    public BalancesClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ResponseEntity<BalancesResponse>> processBalanceApiRequest(String url, HttpMethod httpMethod, HttpHeaders headers, LinkedMultiValueMap payload){
      log.info("url : "+ url);
      try{
          Mono<BalancesResponse> balancesResponseMono =  webClient.method(httpMethod).uri(url)
                  .headers(httpHeaders -> httpHeaders.addAll(headers))
                  .bodyValue(payload)
                  .retrieve()
                  .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> handle4xxClientError(AUTH_CLIENT_ERROR, clientResponse))
                  .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> handle5xxClientError(AUTH_SERVER_ERROR, clientResponse))
                  .bodyToMono(BalancesResponse.class);
          return balancesResponseMono.map(balancesResponse ->
                  ResponseEntity.ok(balancesResponse));
      }catch (Exception e){
          throw new CustomException(HttpStatusCode.valueOf(500), "Something went wrong");
      }

    }
}
