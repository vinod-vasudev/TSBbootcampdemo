package com.tsb.accountsDetails.controller.impl;

import com.tsb.accountsDetails.controller.AccountDetailsApiVersion;
import com.tsb.accountsDetails.model.response.accountResponse.AccountDetailsResponse;
import com.tsb.accountsDetails.service.AccountDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class AccountDetailsController implements AccountDetailsApiVersion {


    private final AccountDetailsService accountDetailsService;


    public AccountDetailsController(AccountDetailsService accountDetailsService) {
        this.accountDetailsService = accountDetailsService;
    }



    @GetMapping("/accounts/{accountId}")
    public Mono<AccountDetailsResponse> getAccountDetails(@RequestHeader(name = "x-fapi-auth-date") String fapiAuthDate,
                                                   @RequestHeader(name = "x-fapi-customer-ip-address") String fapiCustomerIpAddress,
                                                   @RequestHeader(name = "x-fapi-interaction-id") String fapiInteractionId,
                                                   @RequestHeader(name = "Accept") String accept,
                                                   @RequestHeader("Authorization") String auth,
                                                          @PathVariable String accountId
    ) {
        System.out.println("Controller Account  ");
        return accountDetailsService.getAccountDetails(fapiAuthDate, fapiCustomerIpAddress, fapiInteractionId, accept, auth,accountId);
    }
}
