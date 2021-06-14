package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
       // System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
       // System.out.println(getPartOfString("Амиго и Диего лучшиедрузья!"));
    }

    public static String getPartOfString(String string) {
        try {
            ArrayList<Integer> ar = new ArrayList<>();
            for (int i = 0; i < string.length(); i++) {
                int n = string.indexOf(" ",i);
                if (n != -1) {
                    ar.add(n);
                    i = n;
                }
            }
           // System.out.println(ar.size());

            if (ar.size() > 4) return string.substring(ar.get(0)+1, ar.get(4));
            else if (ar.size() == 4) return string.substring(ar.get(0)+1);
            else throw new Exception();
        } catch (Exception e){
            throw new TooShortStringException(e);
        }

    }

    public static class TooShortStringException extends RuntimeException {
        public TooShortStringException(Exception e) {
        }
    }
}
