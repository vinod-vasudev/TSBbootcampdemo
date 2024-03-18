package com.ob.tsb.balances.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.bian.BianReadBalance;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.ConsentService;
import com.ob.tsb.balances.service.ValidationService;
import com.ob.tsb.balances.util.BianToObMapper;
import com.ob.tsb.balances.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
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

    /**
     * Constructs a new {@code BalancesClient} with the specified {@link WebClient} and {@link ObjectMapper}.
     *
     * @param webClient The client for interacting with balances-related functionalities.
     * @param objectMapper The mapper for interacting with object-mapper-related functionalities.
     *
     */
    public BalancesClient(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves balances of List asynchronously based on the provided urls and consent map object and bianUrlMap object.
     *
     * @param urls The urls object representing balances-related information.
     * @param consentMap The consentMap for retrieving consent details.
     * @param bianUrlMap The bianUrlMap for retrieving the bian url details.
     * @return A {@link Mono} emitting an {@link OBReadBalance} containing the details of the balances.
     */
    public Mono<List<OBReadBalance.Balance>> processBalanceApiRequest(List<String> urls,  Map<String, String> consentMap,  Map<String, String> bianUrlMap) {
        log.info("Authorized BIAN urls for balances API : " + urls);
        try {
           return Flux.fromIterable(urls).parallel(Runtime.getRuntime().availableProcessors()).runOn(Schedulers.parallel())
                    .flatMap(url -> webClient.get().uri(url)
                            .retrieve()
                            .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> handle4xxClientError(AUTH_CLIENT_ERROR, clientResponse))
                            .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> handle5xxClientError(AUTH_SERVER_ERROR, clientResponse))
                            .bodyToMono(JsonNode.class).map(jsonNode -> mapToBalancesResponse(jsonNode, getAccountId(url, consentMap, bianUrlMap)))).sequential().collectList();
       } catch (Exception e) {
            throw new CustomException("Something went wrong");
        }
    }

    /**
     * Retrieves a String acoountId asynchronously based on the provided url and consent map object and bianUrlMap object.
     *
     * @param url The urls object representing balances-related information.
     * @param consentMap The consentMap for retrieving consent details.
     * @param bianUrlMap The bianUrlMap for retrieving the bian url details.
     * @return A accountId emitting an {@link String} containing the details of the accountId.
     */
    private String getAccountId(String url, Map<String, String> consentMap, Map<String, String> bianUrlMap) {
        String consentValue = CommonUtils.getKeyByValue(bianUrlMap,url);
        return CommonUtils.getKeyByValue(consentMap,consentValue);
    }


    /**
     * Retrieves balances asynchronously based on the provided jsonNode object and accountId.
     * @param jsonNode The jsonNode for retrieving object of JSON Node details.
     * @param accountId The accountId for retrieving balance details.
     * @return balances emitting an {@link OBReadBalance.Balance} containing the details of the Balances Response.
     */
    private OBReadBalance.Balance mapToBalancesResponse(JsonNode jsonNode, String accountId) {
        BianReadBalance bianReadBalance = createBianAccountBalance(jsonNode, accountId);
        return BianToObMapper.INSTANCE.accountBalance(bianReadBalance);
    }

    /**
     * Retrieves Bian Balances response asynchronously based on the provided jsonNode object and accountId.
     * @param jsonNode The jsonNode for retrieving object of JSON Node details.
     * @param accountId The accountId for retrieving balance details.
     * @return balances emitting an {@link BianReadBalance} containing the details of the BIAN Balances Response.
     */
    private BianReadBalance createBianAccountBalance(JsonNode jsonNode, String accountId) {
        try {
            BianReadBalance.AccountBalance accountBalance = objectMapper.readValue(jsonNode.get("Accountbalance").toString(), BianReadBalance.AccountBalance.class);
            List<BianReadBalance.CreditLine> creditLine = objectMapper.readValue(jsonNode.get("CreditLine").toString(), new TypeReference<>() { });

            return new BianReadBalance(accountBalance, creditLine, accountId);
            } catch (JsonProcessingException e) {
               log.error("Json parse error {}" , e.getMessage());
            }
        return null;

    }
}
