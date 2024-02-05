package com.ob.tsb.balances.service.impl;


import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.config.BaseResponse;
import com.ob.tsb.balances.exception.UnauthorizedException;
import com.ob.tsb.balances.model.constants.ValidationConstraints;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.service.ConsentService;
import com.ob.tsb.balances.service.ValidationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;

@Service
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    @Value("#{${bianurls}}")
    private Map<String, String> bianUrlMap;
    private final BalancesClient balancesClient;
    private final ConsentService consentService;
    private final ValidationService validationService;


    public BalanceServiceImpl(BalancesClient balancesClient, ConsentService consentService, ValidationService validationService) {
        this.balancesClient = balancesClient;
        this.consentService = consentService;
        this.validationService = validationService;
    }


    @Override
    @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<ResponseEntity<Object>> getBalances(String consentId) {
        return getBalanceDetails(consentService.getBalancesConsents(consentId));
    }

    @Override
    @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<ResponseEntity<Object>> getBalanceById(String consentId, String accountId) {
        return getBalanceDetails(consentService.getBalanceByIdConsents(consentId, accountId));
    }


    private Mono<ResponseEntity<Object>> getBalanceDetails(Map<String, String> authorizedAccounts) {
        if(Objects.nonNull(authorizedAccounts) && !authorizedAccounts.isEmpty()){
            log.info("------1------" + authorizedAccounts);
            List<String> bianUrls = authorizedAccounts.values().stream().map(bianUrlMap::get).filter(Objects::nonNull).collect(Collectors.toList());
            log.info("------bianUrls-----" + bianUrls);
            Mono<List<OBReadBalance.Balance>> obBalance = balancesClient.processBalanceApiRequest(bianUrls, HttpMethod.GET, new HttpHeaders(), authorizedAccounts, bianUrlMap); //request body
         log.info(obBalance.toString());
          return obBalance.map(ob -> {
              Errors errors =  validationService.validateObBalance(ob);
              if(errors.hasErrors()){
                  //have to notify BIAN about validation result
                   return ResponseEntity.ok().body(ValidationConstraints.ERROR_OB_BALANCE_VALIDATION);
              } else {
                  return ResponseEntity.ok().body(new OBReadBalance(new OBReadBalance.Data(ob), new OBReadBalance.Links(""), new OBReadBalance.Meta(1)));
              }
           });
        } else {
            return Mono.just(BaseResponse.error("No Consent access")).map(ResponseEntity::ok);
        }
    }



    public Mono<ResponseEntity<?>> balanceServiceCbFallback(Throwable t) {
        log.info("Balance Service Fallback method called.");
         return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG));
    }



}
