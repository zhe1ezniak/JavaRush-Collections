package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    List<Entry<String>> elements;
    private int size = 0;

    public CustomTree() {
        elements = new ArrayList<>();
        root = new Entry<>("0");
        elements.add(root);
    }

    @Override
    public boolean add(String s) {
        for(Entry<String> currentEntry : elements){
            if(currentEntry.isAvailableToAddChildren()){
                if(currentEntry.availableToAddLeftChildren) {
                    currentEntry.leftChild = new Entry<>(s);
                    currentEntry.leftChild.parent = currentEntry;
                    currentEntry.availableToAddLeftChildren = false;
                    elements.add(currentEntry.leftChild);
                    size++;
                    return true;
                } else if (currentEntry.availableToAddRightChildren){
                    currentEntry.rightChild = new Entry<>(s);
                    currentEntry.rightChild.parent = currentEntry;
                    currentEntry.availableToAddRightChildren = false;
                    elements.add(currentEntry.rightChild);
                    size++;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {

        return size;
    }

    public String getParent(String elementName){
        String parent = null;
        for(Entry<String> currentElement : elements){
            if(currentElement.elementName.equals(elementName))
                try{
                    parent = currentElement.parent.elementName;
                } catch (NullPointerException e){
                    return null;
                }
        }
        return parent;
    }

    public boolean remove(Object o){
        if(o instanceof String){
            try {
                for (int i = 0; i < elements.size(); i++) {
                    Entry<String> currentElement = elements.get(i);
                    if (currentElement.elementName.equals((String) o)) {
                        if(i == (elements.size()-1)) size = elements.size() - 2;
                        else size = elements.size() / 2 + 1;
                        if (currentElement.parent.leftChild == currentElement) {
                            currentElement.parent.availableToAddLeftChildren = true;
                            deleteChild(currentElement);
                            size = count(currentElement);
                            return true;
                        }
                        if (currentElement.parent.rightChild == currentElement) {
                            currentElement.parent.availableToAddRightChildren = true;
                            deleteChild(currentElement);
                            size = count(currentElement);
                            return true;
                        }
                    }
                }
            } catch (NullPointerException e){}
            return false;
        } else throw new UnsupportedOperationException();
    }

    public void deleteChild(Entry <String> element){
        for(Entry e : elements){
            if(e.equals(element)){
                if(e.parent.leftChild == e){
                    e.parent.leftChild = null;
                    e.leftChild.parent = null;
                    e.rightChild.parent = null;
                    deleteChild(e.leftChild);
                    deleteChild(e.rightChild);
                }
                if (e.parent.rightChild == e){
                    e.parent.rightChild = null;
                    e.leftChild.parent = null;
                    e.rightChild.parent = null;
                    deleteChild(e.leftChild);
                    deleteChild(e.rightChild);
                }
            }
        }
    }

    public int count(Entry element){
        int count = 0;
        if(element.leftChild != null){
            count++;
            count(element.leftChild);
        }
        if(element.rightChild != null){
            count++;
            count(element.rightChild);
        }
        return count;
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;

        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }
        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element){
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index){
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }
}
