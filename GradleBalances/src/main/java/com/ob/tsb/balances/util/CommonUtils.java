package com.ob.tsb.balances.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {
    public static <K,V> K getKeyByValue(Map<K, V> map, V value){
        for(Map.Entry<K, V> entry : map.entrySet()){
            if(value.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }

}
