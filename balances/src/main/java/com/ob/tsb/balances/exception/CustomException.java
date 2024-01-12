package com.ob.tsb.balances.exception;

import lombok.*;

@Setter
@Getter
public class CustomException extends RuntimeException {


    private String status;
    private String description;

    public CustomException(String number, String somethingWentWrong) {

    }
}
