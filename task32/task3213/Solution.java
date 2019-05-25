package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.Buffer;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder decode = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(reader);
            String codeString = br.readLine();
            char[] buffer = codeString.toCharArray();
            for (int i = 0; i < buffer.length; i++) {
                int var = buffer[i];
                buffer[i] = (char) (var + key);
                decode.append(buffer[i]);
            }
        }catch (NullPointerException e){
            decode.append("");
        }
        return decode.toString();
    }
}
