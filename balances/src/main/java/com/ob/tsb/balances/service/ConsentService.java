package com.ob.tsb.balances.service;

import java.util.List;

public interface ConsentService {

    boolean validateConsents(List<String> consents, String token);
}
