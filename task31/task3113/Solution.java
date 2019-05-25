package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/

public class Solution {
    static long direct = 0;
    static long files = 0;
    static long memory = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Path path = Paths.get(reader.readLine());
            if (!Files.isDirectory(path)) {
                System.out.println(path.toAbsolutePath().toString() + " - не папка");
                return;
            }
                Files.walkFileTree(path, new MyVisitor());
                System.out.println("Всего папок - " + (direct -1));
                System.out.println("Всего файлов - " + files);
                System.out.println("Общий размер - " + memory);
        } catch (IOException e) {

        }
    }

    private static class MyVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            files += 1;
            memory = memory + attrs.size();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            direct += 1;
            return FileVisitResult.CONTINUE;
        }
    }
}