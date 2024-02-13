package com.ob.tsb.balances.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.bian.BianReadBalance;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.util.BianToObMapper;
import com.ob.tsb.balances.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;

import static com.ob.tsb.balances.exception.ErrorConstants.AUTH_CLIENT_ERROR;
import static com.ob.tsb.balances.exception.ErrorConstants.AUTH_SERVER_ERROR;
import static com.ob.tsb.balances.exception.ErrorDesc.handle4xxClientError;
import static com.ob.tsb.balances.exception.ErrorDesc.handle5xxClientError;

@Component
@Slf4j
public class BalancesClient {
    private final WebClient webClient;

    private final ObjectMapper objectMapper;

    public BalancesClient(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    public Mono<OBReadBalance.Balance> processBalanceByIdApiRequest(String url, HttpMethod httpMethod, HttpHeaders headers, MultiValueMap queryParams) {
       try {
            Mono<OBReadBalance.Balance> bianResponseMono = webClient.get().uri(url)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> handle4xxClientError(AUTH_CLIENT_ERROR, clientResponse))
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> handle5xxClientError(AUTH_SERVER_ERROR, clientResponse))
                    .bodyToMono(JsonNode.class).map(jsonNode -> mapToBalanceByIdResponse(jsonNode, queryParams.getFirst("accountId").toString()));
            return bianResponseMono;
        } catch (Exception e) {
            throw new CustomException( "Something went wrong");
        }
    }


        public Mono<List<OBReadBalance.Balance>> processBalanceApiRequest(List<String> urls, HttpMethod httpMethod, HttpHeaders headers, Map consentMap, Map bianUrlMap){
        log.info("Authorized BIAN urls for balances API : "+ urls);
        try {
            Mono<List<OBReadBalance.Balance>> bianBalanceMono = Flux.fromIterable(urls).parallel(Runtime.getRuntime().availableProcessors()).runOn(Schedulers.parallel())
                  .flatMap(url -> webClient.get().uri(url)
                          .retrieve()
                          .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> handle4xxClientError(AUTH_CLIENT_ERROR, clientResponse))
                          .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> handle5xxClientError(AUTH_SERVER_ERROR, clientResponse))
                          .bodyToMono(JsonNode.class).map(jsonNode ->  mapToBalancesResponse(jsonNode, getAccountId(url, consentMap, bianUrlMap)))).sequential().collectList();
            return bianBalanceMono;

        } catch (Exception e){
          throw new CustomException(  "Something went wrong");
      }
    }

    private String getAccountId(String url, Map consentMap, Map bianUrlMap) {
        String consentValue = CommonUtils.getKeyByValue(bianUrlMap,url).toString();
        return CommonUtils.getKeyByValue(consentMap,consentValue).toString();
    }




    private OBReadBalance.Balance mapToBalancesResponse(JsonNode jsonNode, String accountId) {
        BianReadBalance bianReadBalance = createBianAccountBalance(jsonNode, accountId);
        OBReadBalance.Balance obBalance =  BianToObMapper.INSTANCE.accountBalance(bianReadBalance);

        return obBalance;
    }

    private OBReadBalance.Balance mapToBalanceByIdResponse(JsonNode jsonNode, String accountId) {
        BianReadBalance bianReadBalance = createBianAccountBalance(jsonNode, accountId);
        OBReadBalance.Balance obBalance =  BianToObMapper.INSTANCE.accountBalance(bianReadBalance);
        return obBalance;
    }

    private BianReadBalance createBianAccountBalance(JsonNode jsonNode, String accountId) {
        try {
            BianReadBalance.AccountBalance accountBalance = objectMapper.readValue(jsonNode.get("Accountbalance").toString(), BianReadBalance.AccountBalance.class);
            List<BianReadBalance.CreditLine> creditLine = objectMapper.readValue(jsonNode.get("CreditLine").toString(), new TypeReference<>() { });

            return new BianReadBalance(accountBalance, creditLine, accountId);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        return null;

    }
}
