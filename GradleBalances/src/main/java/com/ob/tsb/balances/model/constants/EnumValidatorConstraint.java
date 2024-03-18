package com.ob.tsb.balances.model.constants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        this.enumClass = (Class<? extends Enum<?>>) constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(value)){
            return true;
        }
        try {
            if(enumClass.getDeclaredFields()[0].getType().equals(CreditDebitIndicator.class)){
                 for(Enum<?> enumConstant : enumClass.getEnumConstants()){
                     if (validateCreditDebitIndicatorEnum(enumConstant)) return true;
                 }
            } else if(enumClass.getDeclaredFields()[0].getType().equals(Currency.class)){
                for(Enum<?> enumConstant : enumClass.getEnumConstants()){
                    if (validateCurrencyEnum(enumConstant)) return true;
                }
            }
        } catch (Exception e) {
            log.error(" Error while validation enums");
        }

        return false;
    }

    private static boolean validateCreditDebitIndicatorEnum(Enum<?> enumConstant) {
        try {
            Enum.valueOf(CreditDebitIndicator.class, enumConstant.name());
            return true;
        } catch (Exception e){
            log.error(" Error while validate CreditDebitIndicator enums");
        }
        return false;
    }

    private static boolean validateCurrencyEnum(Enum<?> enumConstant) {
        try {
            Enum.valueOf(Currency.class, enumConstant.name());
            return true;
        } catch (Exception e){
            log.error(" Error while validate Currency enums");
        }
        return false;
    }
}
