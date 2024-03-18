package com.ob.tsb.balances.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.model.consent.ConsentDto;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> toObjectFromFileBalanceList(Class<T> type){
        try {
            if(type == OBReadBalance.Balance.class){
                URL url = OBReadBalance.Balance.class.getClassLoader().getResource("balancelist.json");
                String encodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
                return objectMapper.readValue(new File(encodedPath),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, OBReadBalance.Balance.class));

            }
            return Collections.emptyList();

        } catch (IOException e){
           log.error("Mock balance list json file is not found{}", e.getMessage());
        }
        return Collections.emptyList();
    }

    public static <T> List<T> toObjectFromFileBalance(Class<T> type){
        try {
            if(type == OBReadBalance.Balance.class){
                URL url = OBReadBalance.Balance.class.getClassLoader().getResource("balance.json");
                String encodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
                return objectMapper.readValue(new File(encodedPath),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, OBReadBalance.Balance.class));

            }
            return Collections.emptyList();

        } catch (IOException e){
            log.error("Mock balance json file is not found{}", e.getMessage());
        }
        return Collections.emptyList();
    }

    public static JsonNode toJsonNode() throws IOException {
        Resource resource = new ClassPathResource("bianmock/ConsentMock.json");
        InputStream inputStream = resource.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
        return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonNode.class);
    }

    public static List<ConsentDto>  toJsonNodeData() throws IOException {
        JsonNode jsonNode = toJsonNode();
        JsonNode data = jsonNode.get("Data");
        return objectMapper.readValue(data.toString(), new TypeReference<>(){});
    }
}
