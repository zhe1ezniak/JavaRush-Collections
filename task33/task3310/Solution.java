package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> ids = new HashSet<>();
        for (String s: strings){
            ids.add(shortener.getId(s));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for(Long l: keys){
            strings.add(shortener.getString(l));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testSetStrings = new HashSet<>();
        for(int i = 0; i < elementsNumber; i++){
            testSetStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        long startIdsMethod = new Date().getTime();
        Set<Long> idFromShortener = getIds(shortener, testSetStrings);
        long finishIdsMethod = new Date().getTime();
        Helper.printMessage(String.valueOf(finishIdsMethod - startIdsMethod));

        long startGetStringMethod = new Date().getTime();
        Set<String> stringsFromShortener = getStrings(shortener, idFromShortener);
        long finishGetStringMethod = new Date().getTime();
        Helper.printMessage(String.valueOf(finishGetStringMethod - startGetStringMethod));

        if(testSetStrings.containsAll(stringsFromShortener)) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
