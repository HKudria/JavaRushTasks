package com.javarush.task.task22.task2210;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution {
    public static void main(String[] args) {

       System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));


    }

    public static String[] getTokens(String query, String delimiter) {
        //Первый вариант работает но валидатор его не пропускает
        /*   StringTokenizer tok = new StringTokenizer(query,delimiter);
        String[] result = new String[tok.countTokens()];
        String s = "";
        while (tok.hasMoreTokens()){
           s += tok.nextToken() + " ";
       }

        return s.split(" ");*/

        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        String[] result = new String[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            result[i++] = stringTokenizer.nextToken();
        }
        return result;

    }
}
