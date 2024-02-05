package com.ob.tsb.balances.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.model.Consent;
import com.ob.tsb.balances.model.consent.ConsentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ob.tsb.balances.exception.ErrorConstants.*;
import static com.ob.tsb.balances.exception.ErrorDesc.handle4xxClientError;
import static com.ob.tsb.balances.exception.ErrorDesc.handle5xxClientError;

@Component
@Slf4j
public class ConsentClient {
    private final WebClient webClient;

    @Value("${consent.url}")
    private String consentUrl;

    private final ObjectMapper objectMapper;


    public ConsentClient(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }


    public JsonNode getConsentDetails(){

        System.out.println("-------------consentUrl ----  " + consentUrl);

         Mono<JsonNode> jsonNode = webClient.get().uri(consentUrl)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> handle4xxClientError(AUTH_CLIENT_ERROR, clientResponse))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> handle5xxClientError(AUTH_SERVER_ERROR, clientResponse))
                .bodyToMono(JsonNode.class);


        System.out.println("-------------jsonNode ----  " + jsonNode.block());

        return jsonNode.block();
    }

    private Object mapToConsentResponse(JsonNode jsonNode) {
        System.out.println("-------------jsonNode ----  " + jsonNode);
        return null;
    }

    public boolean validateConsent(List<String> consents, String token){
        Consent consent = webClient.post().uri(consentUrl+token).retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> { return handle4xxClientError(CONSENT_CLIENT_ERROR, clientResponse);})
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> { return handle5xxClientError(CONSENT_SERVER_ERROR, clientResponse);})
                .bodyToMono(Consent.class).block();
        return consent.isValid();
    }
}
