package com.ob.tsb.balances.exception;


import lombok.*;
import org.springframework.http.HttpStatusCode;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomException extends RuntimeException{

    private String status;
    private String description;

    public CustomException(HttpStatusCode httpStatusCode, String message) {
        super(message);
    }

}
