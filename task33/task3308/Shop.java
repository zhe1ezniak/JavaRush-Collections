package com.javarush.task.task33.task3308;

import java.util.List;

public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    public static class Goods{
        public List<String> names;

    }
}
