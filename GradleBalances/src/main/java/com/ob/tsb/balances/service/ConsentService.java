package com.ob.tsb.balances.service;

import com.ob.tsb.balances.model.consent.ConsentDto;

import java.util.List;
/**
 * Interface for consent-related operations.
 */
public interface ConsentService {

    /**
     * Validate the provided consents and token .
     * @param consents The consents to validate.
     * @param token The token to validate.
     * @return True if the token and consents is valid, otherwise false.
     */
    boolean validateConsents(List<String> consents, String token);

    /**
     * Retrieves the consent DTO from the provided consentId .
     *
     * @param consentId The consent ID containing Consent Dto information.
     * @return The consent DTO extracted from the consentId .
     */
    ConsentDto getBalancesConsent(String consentId);

    /**
     * Retrieves the consent ID from the provided authorization token.
     *
     * @param authorization The authorization token containing consent ID information.
     * @return The consent ID extracted from the authorization token.
     */
    String getConsentId(String authorization);
}
