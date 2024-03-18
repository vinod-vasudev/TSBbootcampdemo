package com.ob.tsb.balances.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String AUTH_CLIENT_ERROR = "Auth client has client side error";
    public static final String AUTH_SERVER_ERROR = "Auth client has server side error";
    public static final String CONSENT_CLIENT_ERROR = "Consent client has client side error";
    public static final String CONSENT_SERVER_ERROR = "Consent client has server side error";
}
