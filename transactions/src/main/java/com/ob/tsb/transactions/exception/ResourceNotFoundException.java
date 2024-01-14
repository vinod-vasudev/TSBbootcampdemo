package com.ob.tsb.transactions.exception;

import org.springframework.http.HttpStatusCode;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(HttpStatusCode httpStatusCode, String message) {
        super(message);
    }
}
