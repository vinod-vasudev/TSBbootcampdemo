package com.ob.tsb.balances.model.errors;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public enum ErrorCodes {
    UK_OBIE_FIELD_EXPECTED(HttpStatus.BAD_REQUEST, "UK.OBIE.Field.Expected", HttpMethod.GET),
    UK_OBIE_FIELD_INVALID(HttpStatus.BAD_REQUEST,"UK.OBIE.Field.Invalid", HttpMethod.GET),
    UK_OBIE_FILED_INVALID_DATE(HttpStatus.BAD_REQUEST,"UK.OBIE.Field.InvalidDate", HttpMethod.GET),
    UK_OBIE_FILED_MISSING(HttpStatus.BAD_REQUEST,"UK.OBIE.Field.Missing", HttpMethod.GET),
    UK_OBIE_FILED_UNEXPECTED(HttpStatus.BAD_REQUEST,"UK.OBIE.Field.Unexpected", HttpMethod.GET),
    UK_OBIE_HEADER_INVALID(HttpStatus.BAD_REQUEST,"UK.OBIE.Header.Invalid", HttpMethod.GET),
    UK_OBIE_HEADER_MISSING(HttpStatus.BAD_REQUEST,"UK.OBIE.Header.Missing", HttpMethod.GET),
    UK_OBIE_RESOURCE_CONSENT_MISMATCH(HttpStatus.BAD_REQUEST,"UK.OBIE.Resource.ConsentMismatch", HttpMethod.GET),
    UK_OBIE_RESOURCE_INVALID_CONSENT_STATUS(HttpStatus.BAD_REQUEST,"UK.OBIE.Resource.InvalidConsentStatus", HttpMethod.GET),
    UK_OBIE_RESOURCE_INVALID_FORMAT(HttpStatus.BAD_REQUEST,"UK.OBIE.Resource.InvalidFormat", HttpMethod.GET),
    UK_OBIE_RESOURCE_NOT_FOUND(HttpStatus.BAD_REQUEST,"UK.OBIE.Resource.NotFound", HttpMethod.GET),
    UK_OBIE_RULES_AFTER_CUTOFF_DATETIME(HttpStatus.BAD_REQUEST,"UK.OBIE.Rules.AfterCutOffDateTime", HttpMethod.GET),
    UK_OBIE_SIGNATURE_INVALID(HttpStatus.BAD_REQUEST,"UK.OBIE.Signature.Invalid", HttpMethod.GET),
    UK_OBIE_SIGNATURE_INVALID_CLAIM(HttpStatus.BAD_REQUEST,"UK.OBIE.Signature.InvalidClaim", HttpMethod.GET),
    UK_OBIE_SIGNATURE_MISSING_CLAIM(HttpStatus.BAD_REQUEST,"UK.OBIE.Signature.MissingClaim", HttpMethod.GET),
    UK_OBIE_SIGNATURE_MALFORMED(HttpStatus.BAD_REQUEST,"UK.OBIE.Signature.Malformed", HttpMethod.GET),
    UK_OBIE_SIGNATURE_MISSING(HttpStatus.BAD_REQUEST,"UK.OBIE.Signature.Missing", HttpMethod.GET),
    UK_OBIE_SIGNATURE_UNEXPECTED(HttpStatus.BAD_REQUEST,"UK.OBIE.Signature.Unexpected", HttpMethod.GET),
    UK_OBIE_UNSUPPORTED_ACCOUNT_IDENTIFIER(HttpStatus.BAD_REQUEST,"UK.OBIE.Unsupported.AccountIdentifier", HttpMethod.GET),
    UK_OBIE_UNSUPPORTED_ACCOUNT_SECONDARY_IDENTIFIER(HttpStatus.BAD_REQUEST,"UK.OBIE.Unsupported.AccountSecondaryIdentifier", HttpMethod.GET),
    UK_OBIE_UNSUPPORTED_CURRENCY(HttpStatus.BAD_REQUEST,"UK.OBIE.Unsupported.Currency", HttpMethod.GET),
    UK_OBIE_UNSUPPORTED_FREQUENCY(HttpStatus.BAD_REQUEST,"UK.OBIE.Unsupported.Frequency", HttpMethod.GET),
    UK_OBIE_UNSUPPORTED_LOCAL_INSTRUMENT(HttpStatus.BAD_REQUEST,"UK.OBIE.Unsupported.LocalInstrument", HttpMethod.GET),
    UK_OBIE_UNSUPPORTED_SCHEME(HttpStatus.BAD_REQUEST,"UK.OBIE.Unsupported.Scheme", HttpMethod.GET),
    UNAUTHORIZED_HTTP_401(HttpStatus.UNAUTHORIZED,"401 Unauthorised", HttpMethod.GET),
    PAYMENT_REQUIRED_HTTP_402(HttpStatus.PAYMENT_REQUIRED,"402 Payment Request", HttpMethod.GET),
    FORBIDDEN_HTTP_403(HttpStatus.FORBIDDEN,"403 Forbidden", HttpMethod.GET),
    NOT_FOUND_HTTP_404(HttpStatus.NOT_FOUND,"404 Not Found", HttpMethod.GET),
    METHOD_NOT_ALLOWED_HTTP_405(HttpStatus.METHOD_NOT_ALLOWED,"405 Method Not Allowed", HttpMethod.GET),
    NOT_ACCEPTABLE_HTTP_406(HttpStatus.NOT_ACCEPTABLE,"406 Not Acceptable", HttpMethod.GET),
    TOO_MANY_REQUESTS_HTTP_429(HttpStatus.TOO_MANY_REQUESTS,"429 Too Many Requests", HttpMethod.GET),
    INTERNAL_SERVER_ERROR_HTTP_500(HttpStatus.INTERNAL_SERVER_ERROR,"500 Internal Server Error", HttpMethod.GET),
    SERVICE_UNAVAILABLE_HTTP_500(HttpStatus.SERVICE_UNAVAILABLE,"503 Service Unavailable", HttpMethod.GET);


    private final HttpStatus httpStatus;
    private final String errorCode;
    private final HttpMethod httpMethod;

    ErrorCodes(HttpStatus httpStatus, String errorCode, HttpMethod httpMethod){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.httpMethod = httpMethod;
    }

    public String getErrorCode(){
        return errorCode;
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
    public HttpMethod getHttpMethod(){
        return httpMethod;
    }

}
