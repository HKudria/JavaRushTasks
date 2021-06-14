package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();

        int n = string.indexOf('\t')+1;
        int nn = string.indexOf('\t',string.indexOf('\t')+1);
        if (nn == -1) throw new TooShortStringException();
        return string.substring(n,nn);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {

        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
