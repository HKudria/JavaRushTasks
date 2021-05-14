package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] checked = new byte[4];
        for (int i = 0; i < ip.length; i++) {
            int numberIp = ip[i] < 0 ? ip[i]+256:ip[i]; //если число отрицательное переводим его к размуре подсети то есть + 256
            int numberMask = mask[i]< 0 ? mask[i]+256:mask[i];
            int res = numberIp & numberMask; //логическое И истина если два числа одинаковы
            checked[i] = (byte) res;
        }
        return checked;
    }

    public static void print(byte[] bytes) {
        String s = "";
        for (int i = 0; i < bytes.length; i++) {
            int number = bytes[i];
            if (number<0) number = number + 256;
           //перевод числа в двоичный код на 8 битой
            int[] toS = new int[8];
            for (int j = 7; j >= 0; j--) {
                toS[j] = number%2;
                number = number/2;
            }
            for (int j = 0; j < toS.length; j++) {
                s += toS[j];
            }
            System.out.print(s + " ");
            s = "";
        }
        System.out.println("");
    }
}
