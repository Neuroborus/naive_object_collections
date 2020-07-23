package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    public static void main(String[] args){
        if(args.length < 3){
            return;
        }

        Array arr = new ArrayImpl(200);

        arr.add(args[0]);
        arr.add(args[1]);
        arr.add(args[2]);
        arr.add(null);

        System.out.println(arr.indexOf(null));


        arr.remove(2);
        System.out.println(arr.get(0));
        System.out.println(arr.toString());

        //arr.set(3, 42);

        System.out.println(arr.toString() + " Size: " + arr.size());

        Iterator<Object> it = arr.iterator();
        Object o;
        while(it.hasNext()){
            o = it.next();
            if(o != null) {
                System.out.print(o.toString() + ' ');
            }else{
                System.out.println("null ");
            }
        }
        System.out.println();

        arr.remove(0);
        it = arr.iterator();
        it.next();
        it.remove();
        System.out.println(arr.toString());

        arr.clear();
        System.out.println(arr.toString());
    }


    private Object[] arr;
    private int size;

    public ArrayImpl(){
        arr = new Object[0];
        size = 0;
    }
    public ArrayImpl(int len){
        arr = new Object[len];
        size = 0;
    }

    @Override
    public void add(Object element) {
        Object[] tmp = new Object[arr.length+1];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        ++size;
        tmp[size-1] = element;
        arr = tmp;
    }

    @Override
    public void set(int index, Object element) {

        if(index == arr.length){
            this.add(element);
            return;
        }
        if(index >= 0 && index < arr.length) {
            arr[index] = element;
        }
    }

    @Override
    public Object get(int index) {
        if(index >= 0 && index < arr.length) {
            return arr[index];
        }
        throw new NoSuchElementException();
    }

    public void insert(int index, Object element) {

        if(index == size){
            this.add(element);
            return;
        }
        if(index >= 0 && index < arr.length) {
            Object[] tmp = new Object[arr.length + 1];
            System.arraycopy(arr, index, tmp, index + 1, arr.length - index);
            arr[index] = element;
            System.arraycopy(arr, 0, tmp, 0, index + 1);
            arr = tmp;
            ++size;
        }
    }


    @Override
    public int indexOf(Object element) {
        int f = -1;
        if (element != null) {
            for (int i = 0; i < arr.length; ++i) {
                if (element.equals(arr[i])) {
                    f = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < arr.length; ++i) {
                if (arr[i] == null) {
                    f = i;
                    break;
                }
            }
        }

        return f;
    }

    @Override
    public void remove(int index) {
        if(index >= 0 && index < arr.length) {
            Object[] tmp = new Object[arr.length - 1];
            System.arraycopy(arr, 0, tmp, 0, index);
            System.arraycopy(arr, index+1, tmp, index, arr.length-(index + 1));
            arr = tmp;
            --size;
        }
    }

    @Override
    public void clear() {
        arr = new Object[0];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        if(size > 0) {
            String s = "[";
            Object o;
            for (int i = 0; i < size - 1; ++i) {
                o = arr[i];
                if(o != null) {
                    s = s.concat(arr[i].toString() + ", ");
                }else{
                    s = s.concat("null, ");
                }

            }
            o = arr[size - 1];
            if(o != null) {
                s = s.concat(o.toString() + ']');
            }else{
                s = s.concat("null]");
            }

            return s;
        }
        return "[null]";
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object>{
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Object next() {
            if(hasNext()) {
                return arr[currentIndex++];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(currentIndex);
        }
    }
}
