package com.ob.tsb.balances.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.model.Consent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

import static com.ob.tsb.balances.exception.ErrorConstants.CONSENT_CLIENT_ERROR;
import static com.ob.tsb.balances.exception.ErrorConstants.CONSENT_SERVER_ERROR;
import static com.ob.tsb.balances.exception.ErrorDesc.handle4xxClientError;
import static com.ob.tsb.balances.exception.ErrorDesc.handle5xxClientError;

@Component
@Slf4j
public class ConsentClient {
    private final WebClient webClient;

    @Value("${consent.url}")
    private String consentUrl;

    /**
     * Constructs a new {@code ConsentClient} with the specified {@link WebClient}.
     *
     * @param webClient The client for interacting with consent-related functionalities.
      */
    public ConsentClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Validates the consent for provided token.
     *
     * @param token The token to validate.
     * @return True if the consent is valid, otherwise false.
     */
    public boolean validateConsent(String token){
        Consent consent = webClient.post().uri(consentUrl+token).retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->  handle4xxClientError(CONSENT_CLIENT_ERROR, clientResponse))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->  handle5xxClientError(CONSENT_SERVER_ERROR, clientResponse))
                .bodyToMono(Consent.class).block();
        if(Objects.isNull(consent)){
            return false;
        }
        return consent.isValid();
    }
}
