package com.ob.tsb.balances.mock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.exception.ResourceNotFoundException;
import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@ControllerAdvice
@Slf4j
@RequestMapping("/api/v1/mock")
public class MockController {

    private final ObjectMapper objectMapper;

    private final AuthService authService;

    private static final String MOCK_NOT_FOUND_MSG = "Mock response not found";

    public MockController(ObjectMapper objectMapper, AuthService authService) {
        this.objectMapper = objectMapper;
        this.authService = authService;
    }

    @GetMapping("/consent")
    public JsonNode getConsent() {
        try {
            Resource resource = new ClassPathResource("bianmock/ConsentMock.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e){
            log.error(" Error while reading Consent api mock response file");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, MOCK_NOT_FOUND_MSG);
        }
    }
    @GetMapping("/currentAccBalances")
    public JsonNode getBalances() {
        try {
            Resource resource = new ClassPathResource("bianmock/CurrentAccount.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
       } catch (Exception e){
           log.error(" Error while reading balances api mock response file for Current account");
           throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, MOCK_NOT_FOUND_MSG);
        }
    }


    @GetMapping("/corporateAccBalances")
    public JsonNode getCorporateAccountBalances() {
        try {
            Resource resource = new ClassPathResource("bianmock/CorporateCurrentAccount.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e){
            log.error(" Error while reading balances api mock response file  for Corporate account");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, MOCK_NOT_FOUND_MSG);
        }
    }

    @GetMapping("/creditCardAccBalances")
    public JsonNode creditCardAccBalances() {
        try {
            Resource resource = new ClassPathResource("bianmock/CreditCardAccount.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e){
            log.error(" Error while reading balances api mock response file  for Credit Card account");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, MOCK_NOT_FOUND_MSG);
        }
    }

    @GetMapping("auth/consent/token/{consentId}")
    public String generateToken(@PathVariable String consentId) {
        return  authService.generateJwtToken(consentId);
    }

    @GetMapping("/consent/{consentId}")
    public ConsentDto getConsent(@PathVariable String consentId) {
        try {
            Resource resource = new ClassPathResource("bianmock/ConsentMock.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            JsonNode jsonNode = objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
            JsonNode data = jsonNode.get("Data");
            List<ConsentDto> consentDtoList = objectMapper.readValue(data.toString(), new TypeReference<>() {
            });
            inputStream.close();
            Optional<ConsentDto> consentDtoOpt = consentDtoList.stream().filter(consentDto -> consentDto.consentId().equals(consentId)).findFirst();
            if(consentDtoOpt.isPresent()){
                return consentDtoOpt.get();
            }
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, MOCK_NOT_FOUND_MSG);
        } catch (Exception e){
            log.error(" Error while reading balances api mock response file");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, MOCK_NOT_FOUND_MSG);
        }
    }


}
