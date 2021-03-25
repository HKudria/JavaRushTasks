package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName, partOfContent;
    private int minSize, maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        boolean containsName = true;
        if(partOfName!=null && !file.getFileName().toString().contains(partOfName)){
            containsName = false;
        } // проверка содержит ли имя параметр строки

        boolean containsContent = true;
        if(partOfContent!=null && !new String(content).contains(partOfContent)){
            containsContent = false;
        } //проверка содержит ли файл нужный нам текст


        boolean fileSizeMax = true;
        if(maxSize!=0 && content.length > maxSize){
            fileSizeMax = false;
        } //проверка на размер файла

        boolean fileSizeMin = true;
        if(minSize!=0 && content.length < minSize){
            fileSizeMin = false;
        } //проверка на размер файла

        if(containsName && containsContent && fileSizeMax && fileSizeMin){
            foundFiles.add(file);
        }

        return super.visitFile(file, attrs); //Что ты делаешь?
        //return FileVisitResult.CONTINUE; //переход на следующий файл/директорию в списке

    }
}
