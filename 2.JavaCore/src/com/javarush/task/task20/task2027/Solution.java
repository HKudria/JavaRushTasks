package com.javarush.task.task20.task2027;

import java.util.*;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> ll = new LinkedList<>();
        int sy,sx,ey,ex;
        for (int i = 0; i < words.length; i++) {
            String w = words[i];

                char a = w.charAt(0);
                for (int k = 0; k < crossword[0].length; k++) {
                    for (int l = 0; l < crossword[1].length; l++) {
                        if (crossword[k][l] == a){
                            sx = k;
                            sy = l;
                            for (int j = 1; j < w.length(); j++) {
                                try {
                                    if (crossword[k][l + 1] == w.charAt(j)){
                                        System.out.println("Идем в лево");
                                        break;
                                    }
                                 } catch (IndexOutOfBoundsException e){
                                }
                                try {
                                    if (crossword[k][l - 1] == w.charAt(j)){
                                        System.out.println("Идем в право");
                                        break;
                                    }
                                } catch (IndexOutOfBoundsException e){
                                }
                                try {
                                    if (crossword[k+1][l] == w.charAt(j)){
                                        System.out.println("Идем в вверх");
                                        break;
                                    }
                                } catch (IndexOutOfBoundsException e){

                                }
                                try {
                                    if (crossword[k-1][l] == w.charAt(j)){
                                        System.out.println("Идем в вниз");
                                        break;
                                    }
                                } catch (IndexOutOfBoundsException e){

                                }
                                try {
                                    if (crossword[k-j][l - j] == w.charAt(j)){
                                        System.out.println("Идем в диагональ влево вверх");
                                        if (j==w.length()) {
                                            ex = k-j;
                                            ey = l-j;
                                            ll.add(new Word(sx,sy,ex,ey));
                                        }
                                    }
                                } catch (IndexOutOfBoundsException e){

                                }
                                try {
                                    if (crossword[k-1][l + 1] == w.charAt(j)){
                                        System.out.println("Идем в диагональ вправо вверх");
                                        break;
                                    }
                                } catch (IndexOutOfBoundsException e){

                                }
                                try {
                                    if (crossword[k+1][l - 1] == w.charAt(j)){
                                        System.out.println("Идем в диагональ влево вниз");
                                        break;
                                    }
                                } catch (IndexOutOfBoundsException e){

                                }
                                try {
                                    if (crossword[k+1][l + 1] == w.charAt(j)){
                                        System.out.println("Идем в диагональ вправо вниз");
                                        break;
                                    }
                                } catch (IndexOutOfBoundsException e){

                                }
                            }
                        }
                    }
                }
            }

        return null;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
