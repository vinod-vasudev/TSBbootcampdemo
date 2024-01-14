package com.ob.tsb.transactions.exception;

import org.springframework.http.HttpStatusCode;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(HttpStatusCode httpStatusCode, String message) {
        super(message);
    }
}
