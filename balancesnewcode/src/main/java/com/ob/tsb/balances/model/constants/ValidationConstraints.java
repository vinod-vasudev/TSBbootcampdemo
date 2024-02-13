package com.ob.tsb.balances.model.constants;

public class ValidationConstraints {

    public static final int ACCOUNT_ID_MIN_LENGTH = 1;
    public static final int ACCOUNT_ID_MAX_LENGTH = 40;
    public static final String ERROR_ACCOUNT_ID_LENGTH = "Account id should be Min " + ACCOUNT_ID_MIN_LENGTH + " and max "+ACCOUNT_ID_MAX_LENGTH;
    public static final String ERROR_ACCOUNT_ID_BLANK = "Account id should not blank or null";
    public static final String ERROR_AMOUNT_BLANK = "Amount should not blank or null";
    public static final String ERROR_CURRENCY_BLANK = "Currency should not blank or null";
    public static final String ERROR_OB_BALANCE_VALIDATION = "Balance response validation is failed";
    public static final String ERROR_AMOUNT_FORMAT = "Amount not in specified format";
    public static final String ERROR_CURRENCY_FORMAT = "Currency not in specified format";
    public static final String ERROR_CREDIT_DEBIT_INDICATOR = "Invalid Credit/Debit Indicator";
    public static final String ERROR_CURRENCY = "Invalid Currency";
}
