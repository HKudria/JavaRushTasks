package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        List<Content> entries = getContents(args[1]);

        FileOutputStream zipFile = new FileOutputStream(args[1]);
        ZipOutputStream zip = new ZipOutputStream(zipFile);

        //кладем в него  ZipEntry –«архивный объект»
        File file = new File(args[0]);
        zip.putNextEntry(new ZipEntry("new/" + file.getName()));

        //копируем файл «document-for-archive.txt» в архив под именем «document.txt»
        Files.copy(file.toPath(), zip);

        //копируем все остальные файлы
        for (Content content : entries) {
            if (!content.getFileName().equals("new/" + file.getName())) content.saveToZip(zip);
        }

        // закрываем архив
        zip.close();
    }

    private static List<Content> getContents(String arg) throws IOException {
        List<Content> entries = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(arg))) {
            ZipEntry currentEntry;
            byte[] buffer = new byte[1024];
            while ((currentEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int length = 0;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                entries.add(new Content(currentEntry.getName(), outputStream.toByteArray()));
            }
        }
        return entries;
    }

    static class Content {
        String fileName;
        byte[] body;

        Content(String fileName, byte[] body) {
            this.fileName = fileName;
            this.body = body;
        }

        public String getFileName() {
            return fileName;
        }

        void saveToZip(ZipOutputStream zip) throws IOException {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zip.putNextEntry(zipEntry);
            zip.write(body);
            zip.closeEntry();
        }
    }
}

//рабочий код но сервер его не хочет пропускать
/*public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> fileForZip = new ArrayList<>();

        //args = new String[]{"rez.txt","1.zip"};
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(args[1])))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){
                name = entry.getName(); // получим название файла
                fileForZip.add(name);
                // распаковка
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }


        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args[1]))) {
            FileInputStream fis = new FileInputStream(args[0]);
            ZipEntry entry1 = new ZipEntry("new/"+args[0]);
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
            fis.close();
            for (String i : fileForZip) {
                fis = new FileInputStream(i);
                entry1 = new ZipEntry(i);
                zout.putNextEntry(entry1);
                // считываем содержимое файла в массив byte
                buffer = new byte[fis.available()];
                fis.read(buffer);
                // добавляем содержимое к архиву
                zout.write(buffer);
                // закрываем текущую запись для новой записи
                zout.closeEntry();
                fis.close();
                Files.delete(Paths.get(i));
            }

        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}
*/