package com.ob.tsb.balances.service.impl;


import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.ValidationService;
import jakarta.validation.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

@Service
@Slf4j
public class ValidationServiceImpl implements ValidationService {

    SpringValidatorAdapter validator = new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());

    /**
     * Validate the obReadBalance.Balance response object and collect the errors.
     * Retrieves errors information based on the provided obBalances details.
     * @param obBalance The obBalance POJO associated with the request.
     * @return A {@link Errors} containing the details of the OBReadBalance.Balance object.
     */
    @Override
    public Errors validateObBalance(List<OBReadBalance.Balance> obBalance) {
        Errors errors = new BeanPropertyBindingResult(obBalance, "balances");
        obBalance.forEach(balance -> validator.validate(balance, errors, "balance[" + obBalance.indexOf(balance) + "]"));
        errors.getAllErrors().forEach(this::getErrorMessage);
        return errors;
    }

    /**
     * Retrieves errors Message based on the provided error details.
     * @param error The error object associated with the request.
     *
     */
    private void getErrorMessage(ObjectError error) {
        if( error instanceof FieldError filedError){
            log.info(" Field " +  filedError.getField() + " : " + filedError.getDefaultMessage());
        } else {
            log.info(" Msg "+ error.getDefaultMessage());
        }
    }
}
