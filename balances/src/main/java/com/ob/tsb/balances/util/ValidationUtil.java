package com.ob.tsb.balances.util;


import org.springframework.stereotype.Component;
import com.ob.tsb.balances.exception.CustomException;


@Component
public class ValidationUtil {

    public static <T> T checkIfValueIsPresent(T value,String parameterLocated){
        if ( value != null && !value.equals(""))
            return value;
        else
            throw new CustomException("451",parameterLocated+" cannot be empty or null");
    }
}
