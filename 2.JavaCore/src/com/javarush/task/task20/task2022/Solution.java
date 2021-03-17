package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    public String fileName;

    public Solution(){

    }
    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
     in.defaultReadObject();
     stream = new FileOutputStream(fileName,true);

    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution("test.txt");
        solution.writeObject("chuj");
        FileOutputStream fos = new FileOutputStream("test1.txt");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(solution);
        FileInputStream inf = new FileInputStream("test1.txt");
        ObjectInputStream in = new ObjectInputStream(inf);
        Solution sol = (Solution) in.readObject();
        sol.writeObject("pizda");
        System.out.println(sol);

    }
}
