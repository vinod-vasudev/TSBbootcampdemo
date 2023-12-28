package com.tsb.accountsDetails.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsb.accountsDetails.AccountsDetailsApplication;
import com.tsb.accountsDetails.enums.ConsentType;
import com.tsb.accountsDetails.exception.CustomException;
import com.tsb.accountsDetails.model.response.accountResponse.AccountDetailsResponse;
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

    /*Added by Sarvesh Kushwaha for Read Object Array From JSON Array String*/

    public static <T> T toArrayObject(String jsonArray, Class<T> type){
        try{
            // objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
            return objectMapper.readValue(jsonArray, type);
        }catch (Throwable throwable){
            log.info("Error in JsonUtil class in method toObject: {}",throwable.getMessage());
            throw new CustomException("451","Something went wrong");
        }


    }


    /*public static <T> T toObjectFromFile(Class<T> classType,ConsentType consentType){

        try {
            String path = "";
            if (classType ==  UserInfoRequest.class) {
                path = "mock/json/userInfo/UserInfoRequestMock.json";
            }
            if (classType ==   UserInfoResponse.class) {
                path = "mock/json/userInfo/UserInfoSuccessResponse.json";
            }
            if (classType ==   UserInfoError.class) {
                path = "mock/json/userInfo/UserInfoErrorResponseMock.json";
            }

            if (classType ==  ConsentResponse.class){

                if (consentType == ConsentType.CONSENT_READ_consents){
                    path = "mock/json/consent/response/READ_consents.json";
                }
                if (consentType == ConsentType.CONSENT_READ_a_consent){
                    path = "mock/json/consent/response/READ_a_consent.json";
                }

//                if (consentType == ConsentType.CONSENT_DELETE_a_consent){
//                    path = "mock/json/consent/DELETE_a_consent.json";
//                }

            }

            if (classType ==  ConsentResponsePostPutPatch.class){
                if (consentType == ConsentType.CONSENT_CREATE_a_consent){
                    path = "mock/json/consent/response/CREATE_a_consent.json";
                }
                if (consentType == ConsentType.CONSENT_REPLACE_a_consent){
                    path = "mock/json/consent/response/REPLACE_a_consent.json";
                }
                if (consentType == ConsentType.CONSENT_UPDATE_a_consent){
                    path = "mock/json/consent/response/UPDATE_a_consent.json";
                }

            }

            if (classType == DefinitionsResponse.class){
                if (consentType == ConsentType.DEFINITIONS_READ_definitions){
                    path = "mock/json/definitions/READ_definitions.json";
                }

                if (consentType == ConsentType.DEFINITIONS_READ_a_definition){
                    path = "mock/json/definitions/READ_a_definition.json";
                }

                if (consentType == ConsentType.DEFINITIONS_READ_localizations){
                    path = "mock/json/definitions/READ_localizations.json";
                }
                if (consentType == ConsentType.DEFINITIONS_READ_a_localization){
                    path = "mock/json/definitions/READ_a_localization.json";
                }

            }

            if (classType == ConsentRequest.class){
                path = "mock/json/consent/request/Consent_Request.json";
            }


            URL url = BoilerApplication.class.getClassLoader().getResource(path);
            return objectMapper.readValue(new File(url.getFile()), classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static <T> T toObjectFromFile(Class<T> classType, ConsentType consentType){

        try {
            String path = "";
            if (classType ==  AccountDetailsResponse.class) {
                path = "mock/json/accountsDetails/AccountDetailsResponse.json";
            }
            URL url =AccountsDetailsApplication.class.getClassLoader().getResource(path);
            System.out.println("print of url: -"+url);
            String encodedPath = URLDecoder.decode( url.getPath(), StandardCharsets.UTF_8);
            return objectMapper.readValue(new File(encodedPath), classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
