package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
            StringBuilder builder = new StringBuilder();
            while(reader.ready()){
                builder.append(reader.readLine().trim());
            }
            String text = builder.toString().toLowerCase();
            TreeSet<String> treeSet = new TreeSet<>();
            String[] letters = text.split("");
            for(int i = 0; i < letters.length; i++){
                if(letters[i].matches("\\w") && letters[i].matches("[^0-9]")) treeSet.add(letters[i]);
            }
            Iterator<String> iterator = treeSet.iterator();
            if(treeSet.size() < 5 ){
                while (iterator.hasNext()){
                    System.out.print(iterator.next());
                }
            } else {
                for (int i = 0; i < 5; i++) System.out.print(iterator.next());
            }
        }
    }
}
