package com.tsb.banking.util;


import com.tsb.banking.exception.CustomException;
import org.springframework.stereotype.Component;


@Component
public class ValidationUtil {

    public static <T> T checkIfValueIsPresent(T value,String parameterLocated){
        if ( value != null && !value.equals(""))
            return value;
        else
            throw new CustomException("451",parameterLocated+" cannot be empty or null");
    }
}
