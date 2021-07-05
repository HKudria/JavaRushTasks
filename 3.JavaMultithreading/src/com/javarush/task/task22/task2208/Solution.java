package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("name","Ivanov");
        map.put("country","Ukraine");
        map.put(null,"Kiev");
        map.put("city",null);
        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> a: params.entrySet()) {

                if (a.getKey()!=null && a.getValue()!=null){
                    if(sb.length() != 0) sb.append(" and ");
                    sb.append(a.getKey());
                    sb.append(" = '"+a.getValue()+"'");
                }

        }
        return sb.toString();
    }
}
