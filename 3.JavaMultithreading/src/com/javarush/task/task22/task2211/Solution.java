package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //почему то не получается это сделать через BufferedReader
        //args = new String[] {"test","test2"};
        try {
            FileInputStream file = new FileInputStream(args[0]);
            FileOutputStream fis = new FileOutputStream(args[1]);
            while (file.available()>0) {
                //byte[] ch1251 = file.readAllBytes();
                byte[] ch1251 = new byte[file.available()];
                file.read(ch1251);
                String newLine = new String(ch1251,"Windows-1251");
               // System.out.println(newLine);
                ch1251 = newLine.getBytes(StandardCharsets.UTF_8);
                fis.write(ch1251);
            }
            file.close();
            fis.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }


    }
}
