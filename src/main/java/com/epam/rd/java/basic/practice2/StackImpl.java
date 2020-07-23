package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    public static void main(String[] args){
        if(args.length < 3){
            return;
        }

        Stack st = new StackImpl();

        st.push(args[0]);
        st.push(args[1]);
        st.push(args[2]);
        st.push(null);

        System.out.println(st.toString() + " Size: " + st.size());

        Iterator<Object> it = st.iterator();
        Object o;
        while (it.hasNext()) {
            o = it.next();
            if(o!=null) {
                System.out.print(o.toString() + ' ');
            }else{
                System.out.println("null ");
            }
        }
        System.out.println();

        st.push(args[0]);
        st.push(args[1]);
        st.push(args[2]);
        it = st.iterator();
        it.next();
        it.remove();
        System.out.println(st.toString());

        System.out.println(st.top());
        st.push(args[1]);
        st.push(args[2]);
        st.pop();
        System.out.println(st.toString());

        st.clear();
    }

    private final ListImpl stack = new ListImpl();

    @Override
    public void push(Object element) {
        stack.addLast(element);
    }

    @Override
    public Object pop() {
        Object t = stack.getLast();
        stack.removeLast();
        return t;
    }

    @Override
    public Object top() {
        return stack.getLast();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return stack.toString();
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
            return pop();
        }

        @Override
        public void remove() {
            pop();
        }
    }
}
