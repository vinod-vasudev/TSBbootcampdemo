package com.ob.tsb.balances.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Consent {
    private List<String> consentList;
    private String token;
    private boolean isValid;
}
