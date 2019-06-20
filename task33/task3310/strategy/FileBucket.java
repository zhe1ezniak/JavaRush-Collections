package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("tmp", null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return path.toFile().length();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
        return 0L;
    }

    public void putEntry(Entry entry) {
        try {
            FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(entry);
            fos.close();
            outputStream.close();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        Entry entry = null;
        if(path.toFile().length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(path.toFile());
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                Object object = inputStream.readObject();
                fis.close();
                inputStream.close();
                entry = (Entry)object;
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
