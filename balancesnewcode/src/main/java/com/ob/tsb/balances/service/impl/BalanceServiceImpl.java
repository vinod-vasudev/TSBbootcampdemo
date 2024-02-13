package com.ob.tsb.balances.service.impl;


import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.config.BaseResponse;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.exception.ObCustomException;
import com.ob.tsb.balances.model.constants.ValidationConstraints;
import com.ob.tsb.balances.model.errors.ErrorCode;
import com.ob.tsb.balances.model.errors.ObError;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.service.ConsentService;
import com.ob.tsb.balances.service.ValidationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.ob.tsb.balances.util.ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG;
import static com.ob.tsb.balances.util.ApplicationConstants.OB_SPECIFICATION_URL;

@Service
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    @Value("#{${bianurls}}")
    private Map<String, String> bianUrlMap;

    @Value("${auth.url}")
    private String authurl;
    private final BalancesClient balancesClient;
    private final ConsentService consentService;
    private final ValidationService validationService;



    public BalanceServiceImpl(BalancesClient balancesClient, ConsentService consentService, ValidationService validationService) {
        this.balancesClient = balancesClient;
        this.consentService = consentService;
        this.validationService = validationService;
    }


    @Override
   // @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<OBReadBalance> getBalances(String consentId) {
        return getBalanceDetails(consentService.getBalancesConsents(consentId));
    }

    @Override
   // @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<OBReadBalance> getBalanceById(String consentId, String accountId) {
        return getBalanceDetails(consentService.getBalanceByIdConsents(consentId, accountId));
    }


   public Mono<OBReadBalance> getBalanceDetails(Map<String, String> authorizedAccounts) {
            if(Objects.nonNull(authorizedAccounts) && !authorizedAccounts.isEmpty()){
            List<String> bianUrls = authorizedAccounts.values().stream().map(bianUrlMap::get).filter(Objects::nonNull).collect(Collectors.toList());
            Mono<List<OBReadBalance.Balance>> obBalance = balancesClient.processBalanceApiRequest(bianUrls, HttpMethod.GET, new HttpHeaders(), authorizedAccounts, bianUrlMap); //request body
            if(Objects.isNull(obBalance)){
                throw new ObCustomException(new ObError(ErrorCode.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), UUID.randomUUID().toString(), "No account details found", List.of(new ObError.Description(ErrorCode.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), "As per TSB", "/api/v1/balances", "https://api.example.com/docs/errors/"))),"No account details found");
            }
             return  obBalance.map(ob -> {
              Errors errors =  validationService.validateObBalance(ob);
              if(errors.hasErrors()){
                  //have to notify BIAN about validation result
                  List<ObError.Description> errorsList = errors.getAllErrors().stream().map(this::getErrorMessage).collect(Collectors.toList());
                  throw new ObCustomException(new ObError(ErrorCode.UK_OBIE_FIELD_INVALID.getErrorCode(), UUID.randomUUID().toString(), ValidationConstraints.ERROR_OB_BALANCE_VALIDATION, errorsList),ValidationConstraints.ERROR_OB_BALANCE_VALIDATION);
             } else {
                  return new OBReadBalance(new OBReadBalance.Data(ob), new OBReadBalance.Links(OB_SPECIFICATION_URL), new OBReadBalance.Meta(1));
              }
           });
        } else {
                throw new ObCustomException(new ObError("UK.OBIE.Resource.InvalidConsentStatus", "2b5f0fb2-730b-11e8-adc0-fa7ae01bbebc", "No authorised consent found", List.of(new ObError.Description(ErrorCode.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), "As per TSB", "/api/v1/balances", "https://api.example.com/docs/errors/"))),"No authorised consent found");
            }
    }

    private ObError.Description getErrorMessage(ObjectError error) {
        if( error instanceof FieldError){
            return new ObError.Description(ErrorCode.UK_OBIE_FIELD_INVALID.getErrorCode(), error.getDefaultMessage(), "/api/v1/balances", "https://api.example.com/docs/errors/");
        } else {
            return new ObError.Description("", error.getDefaultMessage(), "/api/v1/balances", "https://api.example.com/docs/errors/");
        }
    }



    public Mono<ResponseEntity<?>> balanceServiceCbFallback(Throwable t) {
        log.info("Balance Service Fallback method called.");
         return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG));
    }



}
