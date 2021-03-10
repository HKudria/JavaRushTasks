package com.javarush.task.task20.task2002;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            //File yourFile = new File("your_file_name.txt");
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            //javaRush.ss();
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //javaRush.equals(loadedObject);
            //System.out.println(javaRush.equals(loadedObject));
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны


            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        /* public void ss() throws ParseException {
            User us = new User();
            Date date = new Date(1212121212121L);
            us.setBirthDate(date);
            us.setCountry(User.Country.RUSSIA);
            us.setFirstName("Herman");
            us.setLastName("Kudria");
            us.setMale(true);
            this.users.add(us);
        }*/

        public void save(OutputStream outputStream) throws Exception {
           PrintWriter file = new PrintWriter(outputStream);
            for (User s: users) {
                file.write(s.getFirstName() + "\r\n");
                file.write(s.getLastName()+ "\r\n");
                file.write(s.getBirthDate().getTime()+ "\r\n");
                file.write(s.getCountry().getDisplayName()+ "\r\n");
                String ss = "" + s.isMale();
                file.write(ss+ "\r\n");
            }
            file.flush();
        }

        public void load(InputStream inputStream) throws Exception {
           BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
           while (reader.ready()){
               User us = new User();
               String name = reader.readLine();
               String lastname = reader.readLine();
               us.setFirstName(name);
               us.setLastName(lastname);


               long std= Long.parseLong(reader.readLine());
               Date date = new Date(std);
               us.setBirthDate(date);
               String country = reader.readLine();
               switch (country){
                   case "Russia":
                       us.setCountry(User.Country.RUSSIA);
                       break;
                   case "Ukraine":
                       us.setCountry(User.Country.UKRAINE);
                       break;
                   default:
                       us.setCountry(User.Country.OTHER);
               }
               String male = reader.readLine();
               switch (male){
                   case "true":
                       us.setMale(true);
                       break;
                   case "false":
                       us.setMale(false);
                       break;
               }
               this.users.add(us);
           }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
