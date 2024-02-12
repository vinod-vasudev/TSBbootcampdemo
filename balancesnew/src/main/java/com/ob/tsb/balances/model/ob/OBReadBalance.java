package com.ob.tsb.balances.model.ob;

import com.ob.tsb.balances.model.constants.ValidationConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.List;


@Validated
public record OBReadBalance(Data Data, Links Links, Meta Meta){
public record Data(List<OBReadBalance.Balance> Balance){}

        @Validated
public record Balance(
        @NotBlank(message = ValidationConstraints.ERROR_ACCOUNT_ID_BLANK)
        @NotNull(message = ValidationConstraints.ERROR_ACCOUNT_ID_BLANK)
        @Size(min = ValidationConstraints.ACCOUNT_ID_MIN_LENGTH, max = ValidationConstraints.ACCOUNT_ID_MAX_LENGTH, message = ValidationConstraints.ERROR_ACCOUNT_ID_LENGTH)
        String AccountId,
        Amount Amount, com.ob.tsb.balances.model.constants.CreditDebitIndicator CreditDebitIndicator, String Type,  String DateTime, List<CreditLine> CreditLine){

}

public record CreditLine(Boolean Included, Amount Amount, String Type) {}
public record Amount(

        @NotBlank(message = ValidationConstraints.ERROR_AMOUNT_BLANK)
        @NotNull(message = ValidationConstraints.ERROR_AMOUNT_BLANK)
        @Pattern(regexp = "^\\{1,1}$|^\\d{1,13}\\.\\d{1,5}$", message = "Amount not in specified format")
        String Amount,
        @NotBlank(message = ValidationConstraints.ERROR_CURRENCY_BLANK)
        @NotNull(message = ValidationConstraints.ERROR_CURRENCY_BLANK)
        @Pattern(regexp = "^[A-Z]{3,3}]*$" , message = "Currency not in specified format")
        String Currency) {}
public record Links(String Self){}
public record Meta(int TotalPages){}


}
