package com.gatech.buzzdine.utils;

import com.gatech.buzzdine.entity.UserInfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BuzzUtils {
    public static String mapToString(Map<String, Integer> map){
        return map.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue())
                .collect(Collectors.joining(","));
    }

    public static int getMapMinValue(Map<String, Integer> map){
        return map.isEmpty() ? 0 : Collections.min(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getValue();
    }

    public static int getMapMaxValue(Map<String, Integer> map){
        return map.isEmpty() ? 0 : Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getValue();
    }

    public static Map<String, Integer> stringToMap(String str) {
        return (str == null || str.isEmpty()) ? new HashMap<>() : (HashMap<String, Integer>) Arrays.asList(str.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> e[0], e -> Integer.parseInt(e[1])));
    }
}
