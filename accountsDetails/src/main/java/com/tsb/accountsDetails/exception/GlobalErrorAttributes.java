package com.tsb.accountsDetails.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;


import java.util.*;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        var error = getError(request);
        Map<String, Object> map = new LinkedHashMap<>();
        if (error instanceof CustomException customException) {
            map.put("status", customException.getStatus());
            map.put("description", customException.getDescription());
        }else{
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("description", error.getMessage());
        }

        return map;

    }

}

