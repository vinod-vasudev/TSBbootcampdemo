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

public class    Links {
    @JsonProperty("Self")
    private String self;
    @JsonProperty("Last")
    private String last;
    @JsonProperty("First")
    private String first;
    @JsonProperty("Next")
    private String next;
    @JsonProperty("Prev")
    private String prev;
}
