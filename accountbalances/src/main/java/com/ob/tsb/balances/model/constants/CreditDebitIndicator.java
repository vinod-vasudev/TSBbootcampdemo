package com.ob.tsb.balances.model.constants;

public enum CreditDebitIndicator {
    Credit("Credit"),
    Debit("Debit");

    private final String balanceIndicator;

    CreditDebitIndicator(String balanceIndicator) {
        this.balanceIndicator = balanceIndicator;
    }

   public CreditDebitIndicator getBalanceIndicator(){
        return CreditDebitIndicator.valueOf(balanceIndicator);
    }
}
