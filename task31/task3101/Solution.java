package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;

/*
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        File fileResult = new File(args[1]);
        File allFileResult = new File(fileResult.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(fileResult, allFileResult);

        ArrayList<File> list = new ArrayList<>();

        try (FileOutputStream writer = new FileOutputStream(allFileResult)) {
            Files.walkFileTree(file.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isRegularFile(file)) {
                        if (attrs.size() <= 50) {
                            list.add(new File(file.toString()));
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
            Collections.sort(list);
            for(File files: list){
                FileInputStream reader = new FileInputStream(files);
                while(reader.available() > 0){
                    writer.write(reader.read());
                }
                writer.write(System.lineSeparator().getBytes());
                reader.close();
            }
        } catch(IOException e){
        }
    }
}
