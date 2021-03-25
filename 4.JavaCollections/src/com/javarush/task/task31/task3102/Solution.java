package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File file = new File(root);
        ArrayDeque<File> tree= new ArrayDeque<>(); //cоздаем очередь
        tree.push(file); //добавляем первый елемент в очередь
        List<String> list = new ArrayList<>(); //список всех файлов
        while (tree.peek()!=null){ // пока в очереди есть елементы работаем
            for (File f: tree.pollFirst().listFiles()) { // получаем список всех елементов в папке
                if(f.isDirectory()){ //если это папка добавляем ее в очередь
                    tree.push(new File(f.getAbsolutePath()));
                } else { //иначе добавляем файл в список всех файлов
                    list.add(f.getPath());
                }
            }
        }

        return list;

    }

    public static void main(String[] args) throws IOException {
        String root = "D:/test/";
        List<String> list = getFileTree(root);
        for (String a: list) {
            System.out.println(a);
        }
    }
}
