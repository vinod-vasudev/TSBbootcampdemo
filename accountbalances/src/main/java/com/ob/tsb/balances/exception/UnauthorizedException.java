package com.ob.tsb.balances.exception;

import org.springframework.http.HttpStatusCode;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
