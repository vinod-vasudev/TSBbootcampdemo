package com.ob.tsb.balances.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CreditDebitIndicator {
    CREDIT("Credit"),
    DEBIT("Debit");
    private final String indicatorCode;
}
