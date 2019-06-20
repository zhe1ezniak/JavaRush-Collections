package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int result = 0;
        for (List<V> list : map.values()) result += list.size();
        return result;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        V result = null;
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<>());
            map.get(key).add(value);
            return null;
        } else if (map.get(key).isEmpty()){
            map.get(key).add(value);
            return null;
        } else if (map.get(key).size() < repeatCount) {
            result = map.get(key).get(map.get(key).size() - 1);
            map.get(key).add(value);
            return result;
        } else if (map.get(key).size() == repeatCount){
            result = map.get(key).get(map.get(key).size() - 1);
            map.get(key).remove(0);
            map.get(key).add(value);
            return result;
        }
        return result;
     }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
      if(map.containsKey(key)){
          List<V> list = map.get(key);
          if(list.size() == 1) {
              map.remove(key);
              return list.get(0);
          } else return list.remove(0);
      } else return null;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> result = new ArrayList<>();
        for(List<V> v : map.values()){
            result.addAll(v);
        }
        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for(List<V> list : map.values()){
            if(list.contains(value)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}