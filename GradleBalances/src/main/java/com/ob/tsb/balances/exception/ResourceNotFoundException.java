package com.ob.tsb.balances.exception;

import lombok.*;
import org.springframework.http.HttpStatusCode;
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ResourceNotFoundException extends RuntimeException{

    private final HttpStatusCode httpStatusCode;
    public ResourceNotFoundException(HttpStatusCode httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
