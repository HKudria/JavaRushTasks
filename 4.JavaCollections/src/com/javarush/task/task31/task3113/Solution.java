package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution extends SimpleFileVisitor<Path>{
    private static int fold = -1, fil = 0, size = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path folder = Paths.get(reader.readLine());
        if(Files.isDirectory(folder)){
            Files.walkFileTree(folder, new Solution());
            System.out.println("Всего папок - " + fold);
            System.out.println("Всего файлов - " + fil);
            System.out.println("Общий размер - " + size);
        } else System.out.println(folder.toString() + " - не папка");

    }

    //тут мы проходимся по всем папкам. Корневая папка тоже считается для этого у нас установлено изначальное значение -1
    public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {
        if(Files.isDirectory(file)) fold++;
        return FileVisitResult.CONTINUE;
    }

    //тут проходимся по всем файлам (к файлу можно обращатся через attrs)
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(Files.isRegularFile(file)) {
            fil++;
            size += Files.size(file);
        }
        return super.visitFile(file, attrs);
    }
}
