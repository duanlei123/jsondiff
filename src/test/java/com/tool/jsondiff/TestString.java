package com.tool.jsondiff;

import java.util.HashMap;
import java.util.Map;

/**
 * @ ClassName TestString
 * @ Description
 * @ Auther wb-dl321273
 * @ Version 1.0
 **/
public class TestString {


    public static Map<String,String> stringUtils(String heards){

        Map<String,String> stringMap = new HashMap<>();

        String[] strings = heards.split(",");
        for (int i=0;i<strings.length;i++){
            String[] split = strings[i].split("=");
            stringMap.put(split[0],split[1]);
        }
        return stringMap;
    }

    public static void main(String[] args) {
        String a = "key1=value1,key2=value2,key3=value3";
        Map<String, String> map = stringUtils(a);
        System.out.println(map);
    }
}
