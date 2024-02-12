package com.ob.tsb.balances.util;

public class ApplicationConstants {

    public final static String ACCESS_FORBIDDEN = "Invalid access token";
    public final static String RATE_LIMIT_FALLBACK_MSG = "Too Many Requests - Retry After 1 Minute";
    public final static String BULK_HEAD_FALLBACK_MSG = "Too many request - No further calls are accepted";
    public final static String CIRCUIT_BREAKER_FALLBACK_MSG = "Service not available, try again after 5mins";

    public final static String OB_SPECIFICATION_URL = "https://api.alphabank.com/open-banking/v3.1/aisp/balances/";

}
