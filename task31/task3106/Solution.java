package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if(args.length < 2) return;

        String[] fileNamePart = new String[args.length - 1];
        for (int i = 0; i < fileNamePart.length; i++){
            fileNamePart[i] = args[i+1];
        }
        Arrays.sort(fileNamePart);

        List<FileInputStream> readers = new ArrayList<>();
        for(int i = 0; i < fileNamePart.length; i++){
            readers.add(new FileInputStream(fileNamePart[i]));
        }

        SequenceInputStream seqStream = new SequenceInputStream(Collections.enumeration(readers));
        ZipInputStream zipReader = new ZipInputStream(seqStream);
        FileOutputStream writer = new FileOutputStream(args[0]);
        byte[] buf = new byte[1024];
        while (zipReader.getNextEntry() != null){
            int x;
            while((x = zipReader.read(buf)) != -1){
                writer.write(buf, 0, x);
            }
        }
        seqStream.close();
        zipReader.close();
        writer.close();
    }
}
