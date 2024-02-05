package com.ob.tsb.balances.service;

import java.util.List;
import java.util.Map;

public interface ConsentService {

    boolean validateConsents(List<String> consents, String token);

    Map<String, String> getBalancesConsents(String consentId);

    Map<String, String> getBalanceByIdConsents(String consentId, String accountId);

    String getConsentId(String authorization);
}
