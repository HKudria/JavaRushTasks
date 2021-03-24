package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/* 
Проход по дереву файлов
*/

public class Solution {


    public static void main(String[] args) throws IOException {
        //args = new String[]{"D:/test/","D:/test/resultFileAbsolutePath.txt"};
        File folder = new File(args[0]);
        File file = new File(args[1]);
        File newFileName = new File(file.getParent() + "/" + "allFilesContent.txt");
        if (FileUtils.isExist(newFileName)){ //если такой файл существует удаляем его
            FileUtils.deleteFile(newFileName);
        }
        FileUtils.renameFile(file,newFileName); //переименовали файл п2
        TreeMap<String,File> allfileless50byte = new TreeMap<>(); // сюда заливаем файлы отсортированные по названию

        searchdir(folder, allfileless50byte); //ищем файлы и добавляем в дерево
        FileWriter out = new FileWriter(newFileName); //поток записи файла
        for (Map.Entry<String,File> a: allfileless50byte.entrySet()) {
            BufferedReader ff = new BufferedReader(new FileReader(a.getValue()));
            while(ff.ready()){
                out.write(ff.readLine()); //записываем содержимое файла
            }
            out.write('\n');
            ff.close();
        }
        out.close(); // закрываем поток записи файла

    }

    public static void searchdir(File folder,TreeMap<String,File> allfileless50byte){
        for (File file: folder.listFiles()) { //проходимся по всему списку
            if (file.isDirectory()){ // если это деректорию то с помощью рекурсии передаем новый файл из этой деректории
                searchdir(new File(file.getAbsolutePath()),allfileless50byte);
            } else {
                if(file.length()<=50){ //если это файл проверяем размер
                    allfileless50byte.put(file.getName(), new File(file.getAbsolutePath()));
                }
            }
        }
    }

}
