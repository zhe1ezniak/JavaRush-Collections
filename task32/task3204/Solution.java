package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        char[] buffer = new char[62];
        int j = 0;
        for(int i = 48; i < 123; i ++){
            if((i > 57 && i < 65) || (i > 90 && i < 97)) continue;
            else{
                buffer[j] = (char) i;
                j++;
            }
        }
        char[] password = new char[8];
        Random random = new Random();
        for(int i = 0; i < password.length; i++){
            password[i] = buffer[random.nextInt(62)];
        }
        password[5] = '7';
        password[6] = 'B';
        password[7] = 'v';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length; i++){
            sb.append(password[i]);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos;
    }
}