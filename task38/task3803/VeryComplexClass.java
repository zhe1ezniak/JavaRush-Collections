package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = new Integer(0);
        System.out.println((String) o);
    }

    public void methodThrowsNullPointerException() {
        Integer i = null;
        System.out.println(i / new Integer(1));
    }

    public static void main(String[] args) {

    }
}
