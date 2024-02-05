package com.ob.tsb.balances.model.bian;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.List;

public record BianReadBalance(AccountBalance accountBalance, List<CreditLine> creditLine, String accountId){

public record AccountBalance (
        @JsonProperty("BalanceAmount") BalanceAmount balanceAmount,
        @JsonProperty("BalanceType") String balanceType,
        @JsonProperty("BalanceDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        OffsetDateTime balanceDate,
        @JsonProperty("BalanceIndicator") String balanceIndicator,
        @JsonProperty("Accountbalance") String accountbalance
){}

public record BalanceAmount (
        @JsonProperty("AmountValue") AmountValue amountValue,
        @JsonProperty("AmountCurrency") AmountCurrency amountCurrency,
        @JsonProperty("DecimalPointPosition") String decimalPointPosition,
        @JsonProperty("AmountType") String amountType
){}

public record Amount(
        @JsonProperty("AmountValue") AmountValue AmountValue,
        @JsonProperty("AmountCurrency") AmountCurrency AmountCurrency,
        @JsonProperty("DecimalPointPosition") String DecimalPointPosition,
        @JsonProperty("AmountType") String AmountType
){}

public record AmountValue(
        @JsonProperty("Value") String value
){}

public record AmountCurrency(
        @JsonProperty("Currencycode") String currencycode
){}

public record BalanceDate(
        @JsonProperty("DateTimeContent") String dateTimeContent,
        @JsonProperty("TimeZoneCode") String timeZoneCode,
        @JsonProperty("DaylightSavingIndicator") String daylightSavingIndicator
){}

public record CreditLine(
        @JsonProperty("Included") Boolean Included,
        @JsonProperty("Type") String Type,
        @JsonProperty("Amount") Amount Amount

){}
}
