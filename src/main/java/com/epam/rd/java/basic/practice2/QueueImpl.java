package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

    public static void main(String[] args){
        if(args.length < 3){
            return;
        }

        Queue que = new QueueImpl();

        que.enqueue(args[0]);
        que.enqueue(args[1]);
        que.enqueue(args[2]);
        que.enqueue(null);

        System.out.println(que.toString() + " Size: " + que.size());

        Iterator<Object> it = que.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if(o!=null) {
                System.out.print(o.toString() + ' ');
            }else{
                System.out.print("[null]" + ' ');
            }
        }
        System.out.println();

        it = que.iterator();
        it.next();
        it.remove();
         System.out.println(que.toString());

        System.out.println(que.top());
        que.dequeue();
        System.out.println(que.toString());

        que.enqueue(42);
        que.clear();
        System.out.println(que.toString());
    }


    private final ListImpl que = new ListImpl();

    @Override
    public void enqueue(Object element) {
        que.addFirst(element);
    }

    @Override
    public Object dequeue() {
        Object t = que.getLast();
        que.removeLast();
        return t;
    }

    @Override
    public Object top() {
        return que.getLast();
    }

    @Override
    public void clear(){
        que.clear();
    }

    @Override
    public int size() {
        return que.size();
    }

    @Override
    public String toString()
    {
        StringBuffer s = new StringBuffer("]");

        boolean flag = false;
        for (Object o: que) {
            flag = true;
            s.insert(0, ", " + o);
        }
        if (flag) {
            s.replace(0, 2, "[");
        }
        else{
            s.insert(0, "[null");
        }

        return s.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }
    private class IteratorImpl implements Iterator<Object> {

        @Override
        public boolean hasNext() {
            return size()!=0;
        }

        @Override
        public Object next() {
            return dequeue();
        }

        @Override
        public void remove() {
            dequeue();
        }
    }

}
