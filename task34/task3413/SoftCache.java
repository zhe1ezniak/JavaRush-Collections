package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        //напишите тут ваш код
        if (cacheMap.containsKey(key)) return softReference.get();
        else return null;
    }

    public AnyObject put(Long key, AnyObject value) {
        AnyObject any = get(key);
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        //напишите тут ваш код
        if (any == null) return null;
        else {
            softReference.clear();
            return any;
        }
    }

    public AnyObject remove(Long key) {
        AnyObject any = get(key);
        SoftReference<AnyObject> softReference = cacheMap.remove(key);
        //напишите тут ваш код
        cacheMap.remove(key);
        if (any == null) return null;
        else {
            softReference.clear();
            return any;
        }
    }
}