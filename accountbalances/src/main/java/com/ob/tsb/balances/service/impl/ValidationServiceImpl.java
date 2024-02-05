package com.ob.tsb.balances.service.impl;


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
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class ValidationServiceImpl implements ValidationService {

    SpringValidatorAdapter validator = new SpringValidatorAdapter(new Validation().buildDefaultValidatorFactory().getValidator());

    @Override
    public Errors validateObBalance(List<OBReadBalance.Balance> obBalance) {
        Errors errors = new BeanPropertyBindingResult(obBalance, "balances");
        obBalance.stream().forEach(balance -> validator.validate(balance, errors, "balance[" + obBalance.indexOf(balance) + "]"));
        errors.getAllErrors().stream().forEach(this::getErrorMessage);
        return errors;
    }

    private void getErrorMessage(ObjectError error) {
        if( error instanceof FieldError){
            log.info(" Filed " + ((FieldError) error).getField() + " : " + error.getDefaultMessage());
        } else {
            log.info(" Msg "+ error.getDefaultMessage());
        }
    }
}
