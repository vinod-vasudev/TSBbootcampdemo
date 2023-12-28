package com.tsb.accountsDetails.service;


import com.tsb.accountsDetails.model.response.accountResponse.AccountDetailsResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface AccountDetailsService {


    Mono<AccountDetailsResponse> getAccountDetails(
            String fapiAuthDate,
            String fapiCustomerIpAddress,
            String fapiInteractionId,
            String accept,
            String auth,
            String accountId
    );
}
