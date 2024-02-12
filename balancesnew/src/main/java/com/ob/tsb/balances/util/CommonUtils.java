package com.ob.tsb.balances.util;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
