package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        HashMap<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();
        Path newFileName = Paths.get("new\\" + Paths.get(args[0]).getFileName().toString());
        try(ZipInputStream inputStream = new ZipInputStream(new FileInputStream(args[1]));
            ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(args[1]))){
            ZipEntry zipEntry;
            while((zipEntry = inputStream.getNextEntry()) != null){
                if(!zipEntry.getName().equals(newFileName.toString())) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] array = new byte[1024];
                    int count = 0;
                    while ((count = inputStream.read(array)) != -1) {
                        byteArrayOutputStream.write(array, 0, count);
                    }
                    byteArrayOutputStream.close();
                    map.put(zipEntry, byteArrayOutputStream);
                }
            }
            for(Map.Entry<ZipEntry, ByteArrayOutputStream> m: map.entrySet()){
                outputStream.putNextEntry(new ZipEntry(m.getKey().getName()));
                outputStream.write(m.getValue().toByteArray());
            }
            ZipEntry zipEntry1 = new ZipEntry(newFileName.toString());
            outputStream.putNextEntry(zipEntry1);
            Files.copy(Paths.get(args[0]), outputStream);
        }
    }
}
