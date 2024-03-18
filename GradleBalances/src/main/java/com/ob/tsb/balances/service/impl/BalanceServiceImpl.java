package com.ob.tsb.balances.service.impl;


import com.ob.tsb.balances.client.BalancesClient;
import com.ob.tsb.balances.exception.ObCustomException;
import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.model.constants.ValidationConstraints;
import com.ob.tsb.balances.model.errors.ErrorCodes;
import com.ob.tsb.balances.model.errors.ObError;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.BalanceService;
import com.ob.tsb.balances.service.ConsentService;
import com.ob.tsb.balances.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

import static com.ob.tsb.balances.model.constants.ValidationConstraints.PERMISSION_READ_BALANCE;
import static com.ob.tsb.balances.util.ApplicationConstants.*;

/**
 * Service class for Balances operations.
 */
@Service
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    @Value("#{${bianurls}}")
    private Map<String, String> bianUrlMap;
    private final BalancesClient balancesClient;
    private final ConsentService consentService;
    private final ValidationService validationService;

    private static final String BALANCE_API_PATH = "/api/v1/balances";
    private static final String ERROR_DOCUMENT_PATH = "https://api.example.com/docs/errors/";


    /**
     * Constructs a new {@code BalanceServiceImpl} with the specified {@link BalancesClient} and {@link ConsentService} and {@link ValidationService}.
     *
     * @param balancesClient The client for interacting with balances-related functionalities.
     * @param consentService The client for interacting with consent-related functionalities.
     * @param validationService The validate for interacting with balances-related related functionalities.
     */
    public BalanceServiceImpl(BalancesClient balancesClient, ConsentService consentService, ValidationService validationService) {
        this.balancesClient = balancesClient;
        this.consentService = consentService;
        this.validationService = validationService;
    }

    /**
     * Retrieves balances information asynchronously
     * @param consentId The consent ID associated with the request.
     * @return A {@link Mono} emitting a {@link ResponseEntity} containing an {@link OBReadBalance}
     */
    @Override
    // @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<OBReadBalance> getBalances(String consentId) {
        return getBalanceDetails(consentService.getBalancesConsent(consentId), null);
    }

    /**
     * Retrieves balances information by account ID asynchronously.
     * @param consentId The consent ID associated with the request.
     * @param accountId The identifier of the balances to retrieve.
     * @return A {@link Mono} emitting a {@link ResponseEntity} containing an {@link OBReadBalance}
     */
    @Override
    // @CircuitBreaker(name = "balanceService", fallbackMethod = "balanceServiceCbFallback")
    public Mono<OBReadBalance> getBalanceById(String consentId, String accountId) {
        return getBalanceDetails(consentService.getBalancesConsent(consentId), accountId);
    }

    /**
     * Retrieves balances details asynchronously based on the provided consent object and accountId.
     *
     * @param consentObj The data object representing consent-related information.
     * @param accountId The accountId for retrieving balances details.
     * @return A {@link Mono} emitting an {@link OBReadBalance} containing the details of the balances.
     */
    private Mono<OBReadBalance> getBalanceDetails(ConsentDto consentObj, String accountId) {
        if (!consentObj.consentStatus().equals(STATUS_AUTHORISED)) {
            throw new ObCustomException(new ObError(ErrorCodes.UK_OBIE_RESOURCE_INVALID_CONSENT_STATUS.getHttpStatus(),ErrorCodes.UK_OBIE_RESOURCE_INVALID_CONSENT_STATUS.getErrorCode() , UUID.randomUUID().toString(), "Consent is not Authorised", List.of(new ObError.Description(ErrorCodes.UK_OBIE_RESOURCE_INVALID_CONSENT_STATUS.getErrorCode(), ERROR_DEFAULT_MSG, BALANCE_API_PATH, ERROR_DOCUMENT_PATH))), "Consent is not Authorised");
        }
        if (!validateAccountPermission(consentObj)) {
            throw new ObCustomException(new ObError(ErrorCodes.UNAUTHORIZED_HTTP_401.getHttpStatus(), ErrorCodes.UNAUTHORIZED_HTTP_401.getErrorCode(), UUID.randomUUID().toString(), "Consent permission is not Authorised", List.of(new ObError.Description(ErrorCodes.UNAUTHORIZED_HTTP_401.getErrorCode(), ERROR_DEFAULT_MSG, BALANCE_API_PATH, ERROR_DOCUMENT_PATH))), "Consent permission is not Authorised");
        } else {
            List<ConsentDto.Accounts> accountList = getAccounts(consentObj, accountId);
            Map<String, String> authorizedAccounts = accountList.stream().collect(Collectors.toMap(ConsentDto.Accounts::accountId, account -> account.accountType() + account.accountSubType()));
            if (!authorizedAccounts.isEmpty()) {
                List<String> bianUrls = authorizedAccounts.values().stream().map(bianUrlMap::get).filter(Objects::nonNull).toList();
                Mono<List<OBReadBalance.Balance>> obBalance = balancesClient.processBalanceApiRequest(bianUrls, authorizedAccounts, bianUrlMap);
                if (Objects.isNull(obBalance)) {
                    throw new ObCustomException(new ObError(ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getHttpStatus(),ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), UUID.randomUUID().toString(), "No account details found", List.of(new ObError.Description(ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), ERROR_DEFAULT_MSG, BALANCE_API_PATH, ERROR_DOCUMENT_PATH))), "No account details found");
                }
                return obBalance.map(ob -> {
                    Errors errors = validationService.validateObBalance(ob);
                    if (errors.hasErrors()) {
                        //have to notify BIAN about validation result
                        List<ObError.Description> errorsList = errors.getAllErrors().stream().map(this::getErrorMessage).toList();
                        throw new ObCustomException(new ObError(ErrorCodes.UK_OBIE_FIELD_INVALID.getHttpStatus(),ErrorCodes.UK_OBIE_FIELD_INVALID.getErrorCode(), UUID.randomUUID().toString(), ValidationConstraints.ERROR_OB_BALANCE_VALIDATION, errorsList), ValidationConstraints.ERROR_OB_BALANCE_VALIDATION);
                    } else {
                        return new OBReadBalance(new OBReadBalance.Data(ob), new OBReadBalance.Links(OB_SPECIFICATION_URL), new OBReadBalance.Meta(1));
                    }
                });
            } else {
                throw new ObCustomException(new ObError(ErrorCodes.FORBIDDEN_HTTP_403.getHttpStatus(),ErrorCodes.FORBIDDEN_HTTP_403.getErrorCode(), UUID.randomUUID().toString(), "No authorised account found", List.of(new ObError.Description(ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), ERROR_DEFAULT_MSG, BALANCE_API_PATH, ERROR_DOCUMENT_PATH))), "No authorised account found");
            }
        }
    }

    /**
     * Retrieves accounts details asynchronously based on the provided consent object and accountId.
     *
     * @param consentObj The data object representing consent-related information.
     * @param accountId The accountId for retrieving accounts details.
     * @return A {@link Mono} emitting an {@link ConsentDto.Accounts} containing the details of the accounts.
     */
    private List<ConsentDto.Accounts> getAccounts(ConsentDto consentObj, String accountId) {
        List<ConsentDto.Accounts> accountsList = new ArrayList<>();
        if (Objects.isNull(accountId)) {
            accountsList.addAll(consentObj.accounts());
        } else {
            Optional<ConsentDto.Accounts> accountOpt = consentObj.accounts().stream().filter(accounts -> accounts.accountId().equals(accountId)).findFirst();
            if (accountOpt.isEmpty()) {
                throw new ObCustomException(new ObError(ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getHttpStatus(),ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), UUID.randomUUID().toString(), "Account is not present", List.of(new ObError.Description(ErrorCodes.UK_OBIE_RESOURCE_CONSENT_MISMATCH.getErrorCode(), ERROR_DEFAULT_MSG, BALANCE_API_PATH, ERROR_DOCUMENT_PATH))), "Account is not present");
            } else {
                accountsList.add(accountOpt.get());
            }
        }
        return accountsList;
    }

    /**
     * Validate Account Permission based on consent DTO object.
     * @param consentDto The consent Dto provide of permission for Accounts details.
     * @return True if the permission is valid, otherwise false.
     */
    private boolean validateAccountPermission(ConsentDto consentDto) {
        return consentDto.permissions().contains(PERMISSION_READ_BALANCE);
    }
    /**
     * Retrieves description asynchronously based on the provided error object.
     * @param error The error object associated with the request.
     * @return Retrieves errors description based on the provided error object.
     */
    private ObError.Description getErrorMessage(ObjectError error) {
        if( error instanceof FieldError){
            return new ObError.Description(ErrorCodes.UK_OBIE_FIELD_INVALID.getErrorCode(), error.getDefaultMessage(), BALANCE_API_PATH, ERROR_DOCUMENT_PATH);
        } else {
            return new ObError.Description("", error.getDefaultMessage(), BALANCE_API_PATH, ERROR_DOCUMENT_PATH);
        }
    }


    /**
     * Fallback method for throwable in case of service failure.
     * Logs the failure and returns a service unavailable response.
     * @param t The throwable indicating service failure.
     * @return ResponseEntity with service unavailable status.
     */
    public Mono<ResponseEntity<String>> balanceServiceCbFallback(Throwable t) {
        log.info("Balance Service Fallback method called {}", t.getMessage());
         return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CIRCUIT_BREAKER_FALLBACK_MSG));
    }



}
