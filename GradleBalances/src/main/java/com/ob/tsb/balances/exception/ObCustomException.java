package com.ob.tsb.balances.exception;

import com.ob.tsb.balances.model.errors.ObError;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ObCustomException extends RuntimeException {

    private final transient ObError obError;
    public ObCustomException(ObError obError, String message) {
        super(message);
        this.obError = obError;
    }


}
