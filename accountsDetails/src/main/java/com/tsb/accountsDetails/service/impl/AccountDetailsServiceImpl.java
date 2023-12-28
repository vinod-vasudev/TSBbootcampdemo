package com.tsb.accountsDetails.service.impl;


import com.tsb.accountsDetails.exception.CustomException;
import com.tsb.accountsDetails.model.response.accountResponse.AccountDetailsResponse;
import com.tsb.accountsDetails.service.AccountDetailsService;
import com.tsb.accountsDetails.service.AuthService;
import com.tsb.accountsDetails.util.HeadersUtil;
import com.tsb.accountsDetails.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;



@Service
@Slf4j
public class AccountDetailsServiceImpl implements AccountDetailsService {



    @Value("${api.accountDetails.uri}")
    private String accountDetails;
    @Value("${api.max-attempt}")
    private Integer maxAttempt;
    @Value("${api.delay-millis}")
    private Integer delayMillis;

    private final WebClient webClient;
    private final AuthService authService;



    public AccountDetailsServiceImpl(WebClient webClient, AuthService authService) {
        this.webClient = webClient;
        this.authService = authService;
    }



    @Override
    public Mono<AccountDetailsResponse> getAccountDetails(String fapiAuthDate, String fapiCustomerIpAddress, String fapiInteractionId, String accept, String auth, String accountId) {
        return authService.getToken(auth).flatMap(tokenResponse -> {
            HttpHeaders httpHeaders = HeadersUtil.consentHeader(tokenResponse);
            httpHeaders.set("x-fapi-auth-date", fapiAuthDate);
            httpHeaders.set("x-fapi-customer-ip-address", fapiCustomerIpAddress);
            httpHeaders.set("x-fapi-interaction-id", fapiInteractionId);
            httpHeaders.set("Accept", accept);

            UriComponents customerUrl = UriComponentsBuilder.fromUriString(accountDetails)
                    .path(accountId)
                    .build();

            log.info("Account Detials Request : {} headers {}", customerUrl.toUriString(), JsonUtil.toJson(httpHeaders));
            return webClient.get().uri(customerUrl.toUriString()).headers(
                    head -> head.addAll(httpHeaders)).retrieve().onStatus( HttpStatusCode::isError,
                    response -> response.bodyToMono(AccountDetailsResponse.class).flatMap(errorBody -> {
                        log.error("Error Account Detials  status code {}, response body: {}", response.statusCode(), errorBody);
                        CustomException customException = new CustomException("451", "Failed to fetch Account Detials ");
                        return Mono.error(customException);
                    })).bodyToMono(String.class).map(response -> {
                log.info("Line number 60 Account Details Response : {}", response);
                return JsonUtil.toObject(response, AccountDetailsResponse.class);
                //return ObjectDtoMapper.dtoMapper(res, AccountResponseDto.class);
            }).retryWhen(Retry.fixedDelay(maxAttempt, Duration.ofMillis(delayMillis))).onErrorResume(error -> {
                log.error("Error while fetching Account Details max attempt done : {}", error.getMessage());
                throw new CustomException("451", "Failed to fetch Account Details ");
            });


        });
    }
}
