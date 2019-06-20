package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        int x = 2;
        while (x <= n){
            if ((n % x) == 0){
                if(x != n){
                    System.out.println(x + " ");
                    recurse(n / x);
                } else System.out.println(x);
                return;
            }
            x++;
        }
    }
}
