package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties(); //создаем новый файл проперти

        for (Map.Entry<String, String> pair : runtimeStorage.entrySet()) {
            prop.setProperty(pair.getKey(), pair.getValue()); //доавляем в него значения
        }

        prop.store(outputStream,""); //записываем в файл
    }

    public static void load(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);

        for (Map.Entry<Object, Object> p: prop.entrySet()) {
            runtimeStorage.put((String) p.getKey(),(String) p.getValue());
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream fos = new FileInputStream(reader.readLine());
            load(fos);
           /* FileOutputStream out = new FileOutputStream(reader.readLine());
            save(out);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);
    }
}
