package com.ob.tsb.balances.exception;

import com.ob.tsb.balances.model.errors.ObError;
import org.springframework.http.HttpStatusCode;

public class ObCustomException extends RuntimeException {

    public ObCustomException(ObError obError, String message) {
        super(message);
    }
}
