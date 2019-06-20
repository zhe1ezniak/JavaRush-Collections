package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if(!cache.containsKey(key)){
            Constructor constructor = clazz.getConstructor(key.getClass());
            V obj = (V) constructor.newInstance(key);
            cache.put(key, obj);
            return obj;
        }
        return cache.get(key);
    }

    public boolean put(V obj) {
        //TODO add your code here
        try{
            int size = size();
            Class clazz = obj.getClass();
            Method method = null;
            method = clazz.getDeclaredMethod("getKey", null);
            method.setAccessible(true);
            K k = (K) method.invoke(obj, null);
            cache.put(k, obj);
            if (size() <= size){
                return false;
            }
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
