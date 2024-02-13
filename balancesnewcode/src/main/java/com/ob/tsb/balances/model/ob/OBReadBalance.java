package com.ob.tsb.balances.model.ob;

import com.ob.tsb.balances.model.constants.EnumValidator;
import com.ob.tsb.balances.model.constants.ValidationConstraints;
import com.ob.tsb.balances.model.constants.Currency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.util.List;


@Valid
public record OBReadBalance(Data Data, Links Links, Meta Meta){
public record Data(List<OBReadBalance.Balance> Balance){}

        @Valid
public record Balance(
        @NotBlank(message = ValidationConstraints.ERROR_ACCOUNT_ID_BLANK)
        @NotNull(message = ValidationConstraints.ERROR_ACCOUNT_ID_BLANK)
        @Size(min = ValidationConstraints.ACCOUNT_ID_MIN_LENGTH, max = ValidationConstraints.ACCOUNT_ID_MAX_LENGTH, message = ValidationConstraints.ERROR_ACCOUNT_ID_LENGTH)
        String AccountId,
        @Valid
        Amount Amount,
        @EnumValidator(enumClass = com.ob.tsb.balances.model.constants.CreditDebitIndicator.class, message = ValidationConstraints.ERROR_CREDIT_DEBIT_INDICATOR)
        String CreditDebitIndicator,
        String Type,  String DateTime, List<CreditLine> CreditLine){

}

public record CreditLine(Boolean Included, Amount Amount, String Type) {}
public record Amount(
        @NotBlank(message = ValidationConstraints.ERROR_AMOUNT_BLANK)
        @NotNull(message = ValidationConstraints.ERROR_AMOUNT_BLANK)
        @Pattern(regexp = "^\\{1,1}$|^\\d{1,13}\\.\\d{1,5}$", message = ValidationConstraints.ERROR_AMOUNT_FORMAT)
        String Amount,
        @NotBlank(message = ValidationConstraints.ERROR_CURRENCY_BLANK)
        @NotNull(message = ValidationConstraints.ERROR_CURRENCY_BLANK)
        @Pattern(regexp = "^[A-Z]{3,3}]*$" , message = ValidationConstraints.ERROR_CURRENCY_FORMAT)
        @EnumValidator(enumClass = com.ob.tsb.balances.model.constants.Currency.class, message = ValidationConstraints.ERROR_CURRENCY)
        String Currency) {}
public record Links(String Self){}
public record Meta(int TotalPages){}


}
