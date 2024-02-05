package com.ob.tsb.balances.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.client.ConsentClient;
import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.service.AuthService;
import com.ob.tsb.balances.service.ConsentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;

@Service
@Slf4j
public class ConsentServiceImpl implements ConsentService {

     private final ConsentClient consentClient;
    private final ObjectMapper objectMapper;
    private final AuthService authService;

    public ConsentServiceImpl(ConsentClient consentClient, ObjectMapper objectMapper, AuthService authService) {
        this.consentClient = consentClient;
        this.objectMapper = objectMapper;
        this.authService = authService;
    }

    @Override
    @CircuitBreaker(name = "consentService", fallbackMethod = "consentServiceCbFallback")
    public boolean validateConsents(List<String> consents, String token) {
        return consentClient.validateConsent(consents, token);
    }

    @Override
    public Map<String, String> getBalancesConsents(String consentId) {
        try {

          /*JsonNode jsonNode = objectMapper.readValue(new File("src/main/resources/bianmock/ConsentMock.json"), JsonNode.class);
          JsonNode data = jsonNode.get("Data");
            List<ConsentDto> consentDtoList = objectMapper.readValue(data.toString(), new TypeReference<List<ConsentDto>>(){});*/

            Resource resource = new ClassPathResource("bianmock/ConsentMock.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            JsonNode jsonNode = objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
            JsonNode data = jsonNode.get("Data");
            List<ConsentDto> consentDtoList = objectMapper.readValue(data.toString(), new TypeReference<List<ConsentDto>>(){});
            inputStream.close();
            ConsentDto consentObj = consentDtoList.stream().filter(consentDto -> consentDto.consentId().equals(consentId)).findAny().orElse(null);

            return consentObj.accounts().stream().collect(Collectors.toMap(ConsentDto.Accounts::accountId, ConsentDto.Accounts::accountSubType));
       } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> getBalanceByIdConsents(String consentId, String accountId) {
        try {

            /*JsonNode jsonNode = objectMapper.readValue(new File("src/main/resources/bianmock/ConsentMock.json"), JsonNode.class);
            JsonNode data = jsonNode.get("Data");
            List<ConsentDto> consentDtoList = objectMapper.readValue(data.toString(), new TypeReference<List<ConsentDto>>(){});*/

            Resource resource = new ClassPathResource("bianmock/ConsentMock.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            JsonNode jsonNode = objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
            JsonNode data = jsonNode.get("Data");
            List<ConsentDto> consentDtoList = objectMapper.readValue(data.toString(), new TypeReference<List<ConsentDto>>(){});
            inputStream.close();
            ConsentDto consentObj = consentDtoList.stream().filter(consentDto -> consentDto.consentId().equals(consentId)).findAny().orElse(null);
            return  consentObj.accounts().stream().filter(acc -> acc.accountId().equals(accountId)).collect(Collectors.toMap(ConsentDto.Accounts::accountId, ConsentDto.Accounts::accountSubType));
      } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getConsentId(String token) {
         return authService.getClaimsFromJWt(token).get("consentId").toString();
    }

    public ResponseEntity consentServiceCbFallback(String token, RequestNotPermitted requestNotPermitted) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG);
    }
}
