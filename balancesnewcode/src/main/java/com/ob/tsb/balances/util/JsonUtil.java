package com.ob.tsb.balances.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (Throwable throwable){
         log.info("Error in JsonUtil class in method toJson: {}",throwable.getMessage());
         throw new CustomException("451","Something went wrong");
        }
    }

    public static <T> T toObject(String json, Class<T> type){
        try{
            return objectMapper.readValue(json,type);
        }catch (Throwable throwable){
            log.info("Error in JsonUtil class in method toObject: {}",throwable.getMessage());
         throw new CustomException("451","Something went wrong");
        }
    }


    public static <T> List<T> toObjectFromFileBalanceList(Class<T> type){
        try {
            String path = "";
            if(type == OBReadBalance.Balance.class){
                path = "balancelist.json";

                //TypeReference<List<OBReadBalance.Balance>> typeReference  = new TypeReference<List<OBReadBalance.Balance>>(){};
                URL url = OBReadBalance.Balance.class.getClassLoader().getResource(path);
                String encodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
                return objectMapper.readValue(new File(encodedPath),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, OBReadBalance.Balance.class));

            }

            return null;


        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> toObjectFromFileBalance(Class<T> type){
        try {
            String path = "";
            if(type == OBReadBalance.Balance.class){
                path = "balance.json";

                //TypeReference<List<OBReadBalance.Balance>> typeReference  = new TypeReference<List<OBReadBalance.Balance>>(){};
                URL url = OBReadBalance.Balance.class.getClassLoader().getResource(path);
                String encodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
                return objectMapper.readValue(new File(encodedPath),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, OBReadBalance.Balance.class));

            }

            return null;


        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }



}
