package com.ob.tsb.balances.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.BalancesApplication;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.response.BalanceByIdResponse;
import com.ob.tsb.balances.model.response.BalancesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (Throwable throwable){
         //log.info("Error in JsonUtil class in method toJson: {}",throwable.getMessage());
         throw new CustomException("451","Something went wrong");
        }
    }

    public static <T> T toObject(String json, Class<T> type){
        try{
            return objectMapper.readValue(json,type);
        }catch (Throwable throwable){
            //log.info("Error in JsonUtil class in method toObject: {}",throwable.getMessage());
         throw new CustomException("451","Something went wrong");
        }
    }

    public static <T> T toObjectFromFile(Class<T> classType) {

        try {
            String path = "";
            if (classType == BalancesResponse.class){
                path = "mock/json/account/response/BalancesResponse.json";
            } else if (classType == BalanceByIdResponse.class) {
                path = "mock/json/account/response/BalanceByIdResponse.json";

            }
            URL url = BalancesApplication.class.getClassLoader().getResource(path);
            String encodedPath = URLDecoder.decode( url.getPath(), StandardCharsets.UTF_8);
            return objectMapper.readValue(new File(encodedPath), classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
