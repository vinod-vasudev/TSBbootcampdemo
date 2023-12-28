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
public class Meta {
    @JsonProperty("TotalPages")
    private Integer totalPages;
    @JsonProperty("FirstAvailableDateTime")
    private String firstAvailableDateTime;
    @JsonProperty("LastAvailableDateTime")
    private String lastAvailableDateTime;
}
