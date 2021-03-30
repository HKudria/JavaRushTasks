package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
      URL test = new URL(urlString); // записываем стрин в ссылку
        InputStream inputStream = test.openStream(); // открываем поток на чтения из ссылки
        Path tempFile = Files.createTempFile("temp-",".tmp"); //создаем временный файл
        Files.copy(inputStream, tempFile,StandardCopyOption.REPLACE_EXISTING); //копируем данные из ссылки во временный файл
     String[] name = urlString.split("/"); // парсим имя файла из ссылки

        //System.out.println(n);
        if(Files.notExists(downloadDirectory)){ //если деректория не существует создаем ее
            Files.createDirectory(downloadDirectory);
        }

        //Path finalFile = Files.createFile(Paths.get(n)); // создаем новый файл деректория + имя файла из ссылки
        Path finalFile = downloadDirectory.resolve(name[name.length-1]); // при переносе файла создавать новый файл не нужно resolve добавляем к деректории название файла
        if(Files.exists(finalFile)){ //если такой файл уже есть удаляем его
            Files.delete(finalFile);
        }
        Files.move(tempFile,finalFile); //переносим содержимое временного файла в файл постоянный
        //System.out.println(finalFile.getFileName());
        return finalFile;
    }
}
