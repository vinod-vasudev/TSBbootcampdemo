package com.ob.tsb.balances.mock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.exception.ResourceNotFoundException;
import com.ob.tsb.balances.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@RestController
@ControllerAdvice
@Slf4j
@RequestMapping("/api/v1/mock")
public class MockController {

    private final ObjectMapper objectMapper;

    private final ResourceLoader resourceLoader;

    private final AuthService authService;

    public MockController(ObjectMapper objectMapper, ResourceLoader resourceLoader, AuthService authService) {
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
        this.authService = authService;
    }

    @GetMapping("/consent")
    public JsonNode getConsent() {
        try {
            /* File file = ResourceUtils.getFile("src/main/resources/bianmock/ConsentMock.json");
            return objectMapper.readValue(file, JsonNode.class);*/
               Resource resource = new ClassPathResource("bianmock/ConsentMock.json");
                InputStream inputStream = resource.getInputStream();
                byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
                return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e){
            e.printStackTrace();
            log.error(" Error while reading Consent api mock response file");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, "Mock response not found");
        }
    }

    @GetMapping("/currentAccbalances")
    public JsonNode getBalances() {
        try {
            /* File file = ResourceUtils.getFile("src/main/resources/bianmock/CurrentAccount.json");
            return objectMapper.readValue(file, JsonNode.class);*/
                Resource resource = new ClassPathResource("bianmock/CurrentAccount.json");
                InputStream inputStream = resource.getInputStream();
                byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
                return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
       } catch (Exception e){
           log.error(" Error while reading balances api mock response file");
           throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, "Mock response not found");
        }
    }


    @GetMapping("/corporateAccBalances")
    public JsonNode getCorporateAccountBalances() {
        try {
            /*File file = ResourceUtils.getFile("src/main/resources/bianmock/CorporateCurrentAccount.json");
            return objectMapper.readValue(file, JsonNode.class);*/
            Resource resource = new ClassPathResource("bianmock/CorporateCurrentAccount.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);

            //  return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e){
            log.error(" Error while reading balances api mock response file");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, "Mock response not found");
        }
    }

    @GetMapping("/creditCardAccBalances")
    public JsonNode creditCardAccBalances() {
        try {
           /* File file = ResourceUtils.getFile("src/main/resources/bianmock/CreditCardAccount.json");
            return objectMapper.readValue(file, JsonNode.class);*/
           Resource resource = new ClassPathResource("bianmock/CreditCardAccount.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e){
            log.error(" Error while reading balances api mock response file");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, "Mock response not found");
        }
    }

    @GetMapping("/accounts/{AccountId}/balances")
    public JsonNode getBalanceById() {
        try {
          /*  File file = ResourceUtils.getFile("src/main/resources/bianmock/CreditCardAccount.json");
            return objectMapper.readValue(file, JsonNode.class);*/
            Resource resource = new ClassPathResource("bianmock/CreditCardAccount.json");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e){
            log.error(" Error while reading balances api mock response file");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, "Mock response not found");
        }

    }

    @GetMapping("auth/consent/token/{consentId}")
    public String generateToken(@PathVariable String consentId) {
       return  authService.generateJwtToken(consentId);
    }



}
