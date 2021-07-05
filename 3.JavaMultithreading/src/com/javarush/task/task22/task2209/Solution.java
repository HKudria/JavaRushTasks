package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;


public class Solution {
    public static void main(String[] args) {
        String[] s = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedReader freader = new BufferedReader(new FileReader(reader.readLine()))) {
            s = freader.readLine().split(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder result = getLine(s);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words == null) return new StringBuilder(); //проверка пустой ли список слов
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));

        int ListQuen = 0;

        Map<Integer, Integer> map = blackSort(list); //записываем каждый вариант с количеством его шагов

        int size = 10000;
        for (Map.Entry<Integer, Integer> s : map.entrySet()) {
            System.out.println(map);
            if (s.getValue() < size) {
                size = s.getValue();
                ListQuen = s.getKey();
            }
        }

      //  System.out.println(ListQuen + " " + size);

        return whiteSort(list,ListQuen,size);


    }

    public static Map<Integer, Integer> blackSort(ArrayList<String> list) {
        int ListQuen = 3;
        Map<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        boolean isActive = false;
        while (!isActive) {
            if (map.size() == list.size()) break;
            ArrayList<String> copyList = new ArrayList<>(list);
            sb.setLength(0);
            sb.append(copyList.get(ListQuen));
            copyList.remove(ListQuen);
            int count = 0;
            while (!copyList.isEmpty()) {
                char a = sb.charAt(sb.length() - 1); //берем последний символ посднего добавленного слова
                if (count > copyList.size()) {
                    map.put(ListQuen, copyList.size());
                    ListQuen++;
                    break;
                }
                for (int j = 0; j < copyList.size(); j++) { //сраниваем последнюю и первую букву у всех слов в списке
                    String b = copyList.get(j).toLowerCase();
                    char bb = b.charAt(0); //первая буква каждого слова в списке
                    count++;
                    if (a == bb) {
                        count = 0;
                        if (copyList.size() == 1) { //если это последний элемент добавляем его сразу в список
                            sb.append(" ").append(copyList.get(0));
                            copyList.remove(0);
                            isActive = true;
                        } else if (copyList.size()>1) {
                            char thW = b.charAt(b.length() - 1);
                            for (int i = 0; i < copyList.size(); i++) {
                                String bbb = copyList.get(i).toLowerCase();
                                char bbbb = b.charAt(0);
                                if (bbbb == bb) {
                                    sb.append(" ").append(copyList.get(j));
                                    copyList.remove(j);
                                }
                            }
                        } else {
                            sb.append(" ").append(copyList.get(j));
                            copyList.remove(j);
                        }
                        if (count > 10) isActive = true;
                    }
                }
            }

        }

        return map;
    }

    public static StringBuilder whiteSort(ArrayList<String> copyList,int ListQuen, int size) {

        StringBuilder sb = new StringBuilder();
        int newsize = copyList.size()-size;


            int count = 1;
            sb.append(copyList.get(ListQuen));
            copyList.remove(ListQuen);
            while (copyList.size()!=size) {
                char a = sb.charAt(sb.length() - 1); //берем последний символ посднего добавленного слова
                for (int j = 0; j < copyList.size(); j++) { //сраниваем последнюю и первую букву у всех слов в списке
                    String b = copyList.get(j).toLowerCase();
                    char bb = b.charAt(0); //первая буква каждого слова в списке
                    if (a == bb) {
                        count++;
                        if (copyList.size() == 1) { //если это последний элемент добавляем его сразу в список
                            sb.append(" ").append(copyList.get(0));
                            copyList.remove(0);
                        } else if (copyList.size()>1) {
                            char thW = b.charAt(b.length() - 1);
                            for (int i = 0; i < copyList.size(); i++) {
                                String bbb = copyList.get(j).toLowerCase();
                                char bbbb = b.charAt(0);
                                if (bbbb == bb) {
                                    sb.append(" ").append(copyList.get(j));
                                    copyList.remove(j);
                                }
                            }
                        } else {
                            sb.append(" ").append(copyList.get(j));
                            copyList.remove(j);
                        }

                    }


                }
                if(copyList.isEmpty()) break;
            }



        for (int i = 0; i < copyList.size(); i++) { //Если елементы не совпадают то добавляем все оставшиеся
            sb.append(" ").append(copyList.get(i));
        }
        return sb;
    }

}