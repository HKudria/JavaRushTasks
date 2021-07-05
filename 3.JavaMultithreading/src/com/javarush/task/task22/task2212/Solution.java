package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

import java.util.regex.Pattern;

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        if (telNumber.length() <11) return false;

        return  Pattern.matches("^\\+?\\d*(\\(\\d{3}\\))?\\d?-?\\d*-?\\d+$", telNumber);
        /** ^\+ - начало строки символ +
         * ?\d* - или любая цифла в любом количестве
         * (\(\d{3}\)) - открываем группу на скобки в которой есть 3 символа
         * ?\d - или цыфра -?\d*-?\d или знак тире или цыфра
         * *= 0 or more of the preceding pattern
         * += 1 or more of the preceding pattern
         */

    }

    public static void main(String[] args) {
        String[] test = {"+380501234567", "+38(050)1234567", "+38050123-45-67", "050123-4567", "+38)050(1234567", "+38(050)1-23-45-6-7", "050ххх4567", "050123456", "(0)501234567}",null};
        for (String s : test) {
            System.out.println(s + " - " + checkTelNumber(s));
        }
    }
}
