package com.tsb.accountsDetails.model.response.accountResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetailsResponse {

    @JsonProperty("Data")
   protected Data data;


    @JsonProperty("Links")
    protected Links links;

    @JsonProperty("Meta")
    protected Meta meta;



}
