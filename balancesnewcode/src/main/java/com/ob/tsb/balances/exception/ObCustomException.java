package com.ob.tsb.balances.exception;

import com.ob.tsb.balances.model.errors.ObError;
import lombok.*;
import org.springframework.http.HttpStatusCode;

@Setter
@Getter
@NoArgsConstructor
@Data
@Builder
public class ObCustomException extends RuntimeException {

    private ObError obError;
    private String message;

    public ObCustomException(ObError obError, String message) {
        super(message);
        this.obError = obError;
    }
}
