package com.ob.tsb.balances.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T>{
    private boolean status;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(true, "Success", data);
    }

    public static <T> BaseResponse<T> error(String message){
        return new BaseResponse<>(false, message, null);
    }
}
