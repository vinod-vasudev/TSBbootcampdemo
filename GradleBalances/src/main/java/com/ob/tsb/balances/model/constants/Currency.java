package com.ob.tsb.balances.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
    GBP("GBP"),
    USD("USD");
    private final String currencyCode;
}
