package com.ob.tsb.transactions.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Auth {
    private String token;
    private boolean isValid = false;
}
