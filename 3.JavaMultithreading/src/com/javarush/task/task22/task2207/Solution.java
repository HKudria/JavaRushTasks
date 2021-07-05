package com.javarush.task.task22.task2207;

import javax.xml.xpath.XPath;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        String s = "";
        BufferedReader readerLine = new BufferedReader(new InputStreamReader(System.in));

        try (BufferedReader reader = new BufferedReader(new FileReader(readerLine.readLine()))){
            while (reader.ready()){
                s += reader.readLine() + " ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] array = s.split(" ");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(i).equals(new StringBuilder(list.get(j)).reverse().toString())){
                    result.add(new Pair(list.get(i),list.get(j)));
                    list.remove(j); //елмент который совпал мы удаляем что бы небыло повторной пары
                    break;
                }
            }
        }
        for (Pair pair: result
             ) {
            System.out.println(pair.toString());
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair(){

        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
