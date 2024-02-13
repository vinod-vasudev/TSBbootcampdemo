package com.ob.tsb.balances.service;

import com.ob.tsb.balances.model.ob.OBReadBalance;
import org.springframework.validation.Errors;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ValidationService {
    Errors validateObBalance(List<OBReadBalance.Balance> obBalance);
}
