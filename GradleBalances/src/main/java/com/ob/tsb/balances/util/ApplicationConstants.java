package com.ob.tsb.balances.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstants {

    public static final String STATUS_AUTHORISED = "Authorised";
    public static final String ACCESS_UNAUTHORISED = "Invalid access token";
    public static final String RATE_LIMIT_FALLBACK_MSG = "Too Many Requests - Retry After 1 Minute";
    public static final String BULK_HEAD_FALLBACK_MSG = "Too many request - No further calls are accepted";
    public static final String CIRCUIT_BREAKER_FALLBACK_MSG = "Service not available, try again after 5mins";
    public static final String OB_SPECIFICATION_URL = "https://api.alphabank.com/open-banking/v3.1/aisp/balances/";
    public static final String ERROR_DEFAULT_MSG = "As per TSB";
    public static final String SWAGGER_UI_PATH = "swagger-ui";
    public static final String SWAGGER_RESOURCES_PATH = "webjars";
    public static final String SWAGGER_DOC="api-docs";
    public static final String NO_CONSENT_FOUND = "No consent found";

    public static final String API_BASE_PATH = "/api/v1/balance";

}
