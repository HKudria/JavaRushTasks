package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Алгоритмы-числа
*/

public class Solution {

    public static long[] getNumbers(long N) {

        ArrayList<Long> l = new ArrayList<>();
        for (int m = 0; m < N; m++) {
            String number = m + "";
            long bg = 0;
            for (int i = 0; i < number.length(); i++) {
                long sd = Long.parseLong(String.valueOf(number.charAt(i)));

                long s = 1;
                if (sd != 0) {
                    for (int j = 0; j < number.length(); j++) {
                        s *= sd;
                    }
                } else s = 0;
                bg +=s;
            }
           if (bg<N && bg == Long.parseLong(number)) l.add(bg);
        }
        long[] result = new long[l.size()];
        for (int i = 0; i < l.size(); i++) {
            result[i]=l.get(i);
        }
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
       //System.out.println(Arrays.toString(getNumbers(8208)));

    }
}
