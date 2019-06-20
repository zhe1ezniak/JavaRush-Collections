package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet(){
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection){
        int capacity = Math.max(16, (int)(collection.size()/.75f) + 1);
        map = new HashMap<>(capacity);
        addAll(collection);
    }

    @Override
    public Object clone() throws InternalError{
        try{
            AmigoSet<E> set = (AmigoSet<E>) super.clone();
            set.map = (HashMap<E, Object>) map.clone();
            return set;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    public boolean add(E e){
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    private void writeObject (ObjectOutputStream out) throws IOException {
        try {
            out.defaultWriteObject();
            out.writeObject(map.keySet().size());
            for (E e : map.keySet()) {
                out.writeObject(e);
            }
            out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        }catch (IOException e){}
    }

    private void readObject (ObjectInputStream in) throws IOException, ClassNotFoundException {
        try {
            in.defaultReadObject();
            int size = (int) in.readObject();
            Set<E> set = new HashSet();
            for (int i = 0; i < size; i++) {
                set.add((E) in.readObject());
            }
            int capacity = (int) in.readObject();
            float loadFactor = (float) in.readObject();
            map = new HashMap<>(capacity, loadFactor);
            for (E e : set) {
                map.put(e, PRESENT);
            }
        }catch (IOException e){}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AmigoSet<?> amigoSet = (AmigoSet<?>) o;
        return map.equals(amigoSet.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), map);
    }
}
