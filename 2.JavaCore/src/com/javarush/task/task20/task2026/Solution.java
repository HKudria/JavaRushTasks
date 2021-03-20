package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        byte[][] ne = new byte[a.length+1][a.length+1]; //Создаем масив с увелением на 1
        for (int i = 0; i < a.length; i++) { // перегоняем старый в новый масив
            for (int j = 0; j < a.length; j++) {
              ne[i][j] = a[i][j];
            }
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if(ne[i][j] == 1 && ne[i][j+1] ==0 && ne[i+1][j]==0) count++; // проверяем если елемент равен 1 и елемент с права от него равен 0 и елемент с низу от него равен 0 то увеличиваем счетчик
            }
        }
        //System.out.println(count);
        return count;
    }
}
