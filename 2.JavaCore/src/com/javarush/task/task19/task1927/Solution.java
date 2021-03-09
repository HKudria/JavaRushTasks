package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;
        ByteArrayOutputStream mass = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(mass);
        System.setOut(out);
        testString.printSomething();
        System.setOut(console);
        String[] str = mass.toString().split(System.lineSeparator());
        for (int i = 0; i < str.length; i++) {
            if(i!= 0 && i%2==0) System.out.println("JavaRush - курсы Java онлайн");
            System.out.println(str[i]);
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
