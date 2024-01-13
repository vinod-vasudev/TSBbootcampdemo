package com.ob.tsb.balances.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.exception.ResourceNotFoundException;
import com.ob.tsb.balances.model.response.BalancesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@ControllerAdvice
@Slf4j
@RequestMapping("/api/v1/mock")
public class MockController {

    private final ObjectMapper objectMapper;

    private final ResourceLoader resourceLoader;

    public MockController(ObjectMapper objectMapper, ResourceLoader resourceLoader) {
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/balances")
    public BalancesResponse getBalances() {
        try {
            Resource resource = new ClassPathResource("openapi/fakeResponse.json");

            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), BalancesResponse.class);
       } catch (Exception e){
           log.error(" Error while reading balances api mock response file");
           throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, "Mock response not found");
        }
    }

    @GetMapping("/accounts/{AccountId}/balances")
    public BalancesResponse getBalanceById() {
        try {
           Resource resource = new ClassPathResource("openapi/fakeResponseAccId.json");

            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), BalancesResponse.class);
        } catch (Exception e){
            log.error(" Error while reading balances by id api mock response file");
            throw new ResourceNotFoundException(HttpStatus.NO_CONTENT, "Mock response not found");
        }
    }


}
