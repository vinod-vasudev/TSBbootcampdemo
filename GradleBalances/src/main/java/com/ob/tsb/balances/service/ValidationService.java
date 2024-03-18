package com.ob.tsb.balances.service;

import com.ob.tsb.balances.model.ob.OBReadBalance;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Interface for Validation-related operations.
 */
public interface ValidationService {

    /**
     * Validate the obReadBalance.Balance response object and collect the errors.
     * Retrieves errors information based on the provided obBalances details.
     * @param obBalance The obBalance POJO associated with the request.
     * @return Retrieves errors information based on the provided obBalances details.
     */
    Errors validateObBalance(List<OBReadBalance.Balance> obBalance);
}
