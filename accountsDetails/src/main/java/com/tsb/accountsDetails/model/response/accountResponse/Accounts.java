package com.tsb.accountsDetails.model.response.accountResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Accounts {

    @JsonProperty("AccountId")
    protected String accountId;

    @JsonProperty("Status")
    protected String status;

    @JsonProperty("StatusUpdateDateTime")
    protected String statusUpdateDateTime;

    @JsonProperty("Currency")
    protected String currency;

    @JsonProperty("AccountType")
    protected String accountType;

    @JsonProperty("AccountSubType")
    protected String accountSubType;

    @JsonProperty("Nickname")
    protected String nickname;

    @JsonProperty("OpeningDate")
    protected String openingDate;

    @JsonProperty("Account")
    protected Account[] account;






}
