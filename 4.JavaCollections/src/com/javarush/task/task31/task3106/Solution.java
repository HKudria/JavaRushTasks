package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //args = new String[]{"D:/test.jpg","D:/DSC_0011.zip.002","D:/DSC_0011.zip.001"};
        //args = new String[]{"D:/test.txt","1.zip.001","1.zip.002","1.zip.003"};
        ZipEntry entry;
        File result = new File(args[0]);
        TreeMap<Integer,String> fromZip = new TreeMap<>(); //сортируем ввходящие части архива
        for (int i = 1; i < args.length; i++) {
            String[] sortZip = args[i].split("\\.");
            fromZip.put(Integer.parseInt(sortZip[sortZip.length-1]),args[i]);
        }
        File bigZip = new File("big.zip"); //создаем один общий архив
        FileOutputStream fos = new FileOutputStream(bigZip); //поток записи архива
        ZipInputStream zip = new ZipInputStream(new FileInputStream(bigZip));
        for (Map.Entry<Integer,String> s: fromZip.entrySet()) { //проходимся по всем подчастям архива и пишем их в большой архив
            FileInputStream part = new FileInputStream(s.getValue());
            while(part.available()>0){ //пока есть доступные байти
                int n = 100000;
                if(n > part.available()) n = part.available(); //считывавем по 10000 тисяч байтов если размер меньше присваиваем значения размера файла которые доступен для чтения
                byte[] b = new byte[n];
                part.read(b); //считываем масив байтов
                fos.write(b); //пишем в bigZip
            }
            //закрываем потоки
            fos.flush();
            part.close();
        }
        fos.close();


        FileOutputStream fos1 = new FileOutputStream(result); // поток записи в файл конечный
            while ((entry = zip.getNextEntry()) != null) { //запись из архива в файл
                for (int c = zip.read(); c != -1; c = zip.read()) {
                    fos1.write(c);
                }
                fos1.flush();
            }
            zip.close();
       /* while ((entry = zip.getNextEntry()) != null) {

            while (zip.available()>0){
                int n = 1000;
                if(n< zip.available()) n = zip.available();
                byte[] test = new byte[n];
                zip.read(test);
                System.out.println(new String(test));
                fos1.write(test);
                fos1.flush();
            }

        }
            fos1.close();*/
    }
}
