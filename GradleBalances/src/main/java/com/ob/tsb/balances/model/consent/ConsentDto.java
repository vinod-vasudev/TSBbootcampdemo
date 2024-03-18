package com.ob.tsb.balances.model.consent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ConsentDto(@JsonProperty("ConsentId")String consentId, @JsonProperty("ConsentStatus")String consentStatus,  @JsonProperty("Permissions") List<String> permissions, @JsonProperty("Accounts")List<Accounts> accounts) {

    public record Accounts(
            @JsonProperty("AccountId") String accountId,
            @JsonProperty("Status") String status,
            @JsonProperty("StatusUpdateDateTime") String statusUpdateDateTime,
            @JsonProperty("Currency") String currency,
            @JsonProperty("AccountType") String accountType,
            @JsonProperty("Nickname") String nickName,
            @JsonProperty("AccountSubType") String accountSubType,
            @JsonProperty("OpeningDate") String openingDate,
            @JsonProperty("Account") List<Account> account
    ){}
    public record Account(
            @JsonProperty("SchemeName") String schemeName,
            @JsonProperty("Identification") String identification,
            @JsonProperty("Name") String name,
            @JsonProperty("SecondaryIdentification") String secondaryIdentification
    ){}



}