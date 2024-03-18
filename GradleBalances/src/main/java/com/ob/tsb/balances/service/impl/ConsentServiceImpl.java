package com.ob.tsb.balances.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.client.ConsentClient;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.exception.ObCustomException;
import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.model.errors.ErrorCodes;
import com.ob.tsb.balances.model.errors.ObError;
import com.ob.tsb.balances.service.AuthService;
import com.ob.tsb.balances.service.ConsentService;
import com.ob.tsb.balances.service.ValidationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ob.tsb.balances.util.ApplicationConstants.*;
/**
 * Service class for handling consent-related operations.
 */
@Service
@Slf4j
public class ConsentServiceImpl implements ConsentService {

    private final ConsentClient consentClient;
    private final ObjectMapper objectMapper;
    private final AuthService authService;

    /**
     * Constructs a new {@code ConsentServiceImpl} with the specified {@link ConsentClient} and {@link ObjectMapper} and {@link AuthService}.
     *
     * @param consentClient The client for interacting with client-related functionalities.
     * @param objectMapper The object for interacting with  mapper-related functionalities.
     * @param authService The service for interacting with auth-related related functionalities.
     */
    public ConsentServiceImpl(ConsentClient consentClient, ObjectMapper objectMapper, AuthService authService) {
        this.consentClient = consentClient;
        this.objectMapper = objectMapper;
        this.authService = authService;
    }

    /**
     * Validate the provided consents and token .
     * @param consents The consents to validate.
     * @param token The token to validate.
     * @return True if the token and consents is valid, otherwise false.
     */
    @Override
    @CircuitBreaker(name = "consentService", fallbackMethod = "consentServiceCbFallback")
    public boolean validateConsents(List<String> consents, String token) {
        return consentClient.validateConsent(token);
    }

    /**
     * Retrieves the consent DTO asynchronously based on the provided consentId.
     *
     * @param consentId The consent ID retrieving Consent Dto information.
     * @return A {@link ConsentDto} containing the details of the ConsentDto object.
     */
    @Override
    public ConsentDto getBalancesConsent(String consentId) {
        try {
            Resource resource = new ClassPathResource("bianmock/ConsentMock.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            JsonNode jsonNode = objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
            JsonNode data = jsonNode.get("Data");
            List<ConsentDto> consentDtoList = objectMapper.readValue(data.toString(), new TypeReference<>() {});
            inputStream.close();
            Optional<ConsentDto> consentDtoOpt = consentDtoList.stream().filter(consentDto -> consentDto.consentId().equals(consentId)).findFirst();
            if (consentDtoOpt.isEmpty()) {
                throw new ObCustomException(new ObError(ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getHttpStatus(),ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), UUID.randomUUID().toString(), NO_CONSENT_FOUND, List.of(new ObError.Description(ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), ERROR_DEFAULT_MSG, "/api/v1/balances", "https://api.example.com/docs/errors/"))), NO_CONSENT_FOUND);
            }
            return consentDtoOpt.get();
        } catch (IOException e) {
            throw new CustomException("No consent found");
        }
    }

    /**
     * Retrieves the consent ID from the provided JWT token.
     * It delegates the extraction of claims to the AuthService.
     *
     * @param token The JWT token from which consent ID needs to be extracted.
     * @return The consent ID extracted from the token.
     */
    @Override
    public String getConsentId(String token) {
        try {
            return authService.getClaimsFromJWt(token).get("consentId").toString();
        } catch (Exception e){
            throw new ObCustomException(new ObError(ErrorCodes.UNAUTHORIZED_HTTP_401.getHttpStatus(),ErrorCodes.UNAUTHORIZED_HTTP_401.getErrorCode(), UUID.randomUUID().toString(), "Invalid access token", List.of(new ObError.Description(ErrorCodes.UNAUTHORIZED_HTTP_401.getErrorCode(), ERROR_DEFAULT_MSG, "/api/v1/balances", "https://api.example.com/docs/errors/"))),"Invalid access token");
        }
    }

    /**
     * Fallback method for throwable in case of service failure.
     * Logs the failure and returns a service unavailable response.
     * @return ResponseEntity with service unavailable status.
     */
    public ResponseEntity<String> consentServiceCbFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(CIRCUIT_BREAKER_FALLBACK_MSG);
    }
}
