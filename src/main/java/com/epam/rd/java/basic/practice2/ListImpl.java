package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List{

    public static void main(String[] args){
        if(args.length < 3){
            return;
        }

        List list = new ListImpl();

        list.addLast(args[1]);
        list.addFirst(args[0]);
        list.addLast(args[2]);
        list.addLast(null);

        list.remove(null);

        System.out.println(list.toString());

        list.remove("C");
        System.out.println(list.toString());

        if(list.search(3) == null){
            System.out.println("It's ok!");
        }

        list.addFirst("FIRST");
        list.addLast("LAST");
        System.out.println(list.toString() + " Size: " + list.size());

        list.removeFirst();
        list.removeLast();

        System.out.println(list.toString());
        if(((String)list.getLast()).equals("C") && ((String)list.getFirst()).equals("A")) {
            Iterator<Object> it = list.iterator();
            while (it.hasNext()) {
                System.out.print(it.next().toString() + ' ');
            }
            System.out.println();

            it = list.iterator();
            it.next();
            it.remove();

            System.out.println(list.toString());
        }
        list.clear();

    }



    private static class Node{
        Node next;
        Node prev;
        Object data;
    }

    private Node first;
    private Node last;
    private int length = 0;

    @Override
    public void addFirst(Object element) {
        if(first == null) {
            first = new Node();
            first.data = element;
        }else {
            Node tmp = new Node();
            tmp.data = element;
            tmp.next = first;
            first.prev = tmp;
            first = tmp;

            if(last == null){
                last = first.next;
            }
        }
        ++length;
    }

    @Override
    public void addLast(Object element) {
        if(first == null){
            first = new Node();
            first.data = element;
        }else {
            if (last == null) {
                last = new Node();
                last.data = element;
                first.next = last;
                last.prev = first;
            }else {
                Node tmp = new Node();
                tmp.data = element;
                tmp.prev = last;
                last.next = tmp;
                last = tmp;
            }
        }
        ++length;
    }

    @Override
    public void removeFirst() {
        if(first != null) {
            if(first.next != null) {
                first = first.next;
                first.prev = null;
            }
            else{
                first = null;
            }
            --length;
        }
    }

    @Override
    public void removeLast() {
        if(last == null){
            if(first != null){
                /*first = first.next;
                first.prev = null;*/
                first = null;
                --length;
            }
        }else {
                last = last.prev;
                last.next = null;
                --length;
        }
        if(length == 1){
            last = null;
        }

    }

    @Override
    public Object getFirst() {
        if(first != null) {
            return first.data;
        }else{
            return null;
        }
    }

    @Override
    public Object getLast() {
        if(last == null){
            if(first == null){
                return null;
            }
            return first.data;
        }
        return last.data;
    }

    @Override
    public Object search(Object element) {
        Node tmp = first;

        while(tmp!=null && tmp.next!=null){
            if(element.equals(tmp.data)){
                return tmp.data;
            }
            tmp = tmp.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if(first == null){
            return false;
        }
        Node tmp = first;
        if(last == null){
            if (element.equals(tmp.data)) {
                first = null;
                return true;
            }else{
                return false;
            }
        }

        if(element == null){
            while(tmp.next!=null) {
                if (tmp.data == null) {
                    if (tmp.prev == null) {
                        tmp.next.prev = null;
                        first = tmp.next;
                        tmp = first;
                    } else {
                        Node pr = tmp.prev;
                        tmp = tmp.next;
                        tmp.prev = pr;
                        pr.next = tmp;
                    }
                    --length;
                    return true;
                }
                tmp = tmp.next;
            }

            if (tmp.data == null) {
                tmp.prev.next = null;
                --length;
                return true;
            }
        }else {

            while (tmp.next != null) {
                if (element.equals(tmp.data)) {
                    if (tmp.prev == null) {
                        tmp.next.prev = null;
                        first = tmp.next;
                        tmp = first;
                    } else {
                        Node pr = tmp.prev;
                        tmp = tmp.next;
                        tmp.prev = pr;
                        pr.next = tmp;
                    }
                    --length;
                    return true;
                }
                tmp = tmp.next;
            }

            if (element.equals(tmp.data)) {
                tmp.prev.next = null;
                --length;
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public String toString() {
        String s = "[";

        if(last == null){
            if(first == null) {
                s = s.concat("null]");
            }else {
                s = s.concat(String.valueOf(first.data) + ']');
            }
        }else {
                Node tmp = first;
                for (int i = 0; i < this.length - 1; ++i) {
                    s = s.concat(tmp.data + ", ");
                    if (tmp.next != null) {
                        tmp = tmp.next;
                    }
                }
                s = s.concat(tmp.data + "]");
            }
        return s;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }
    private class IteratorImpl implements Iterator<Object> {
        private Node currentNode = first;
        private boolean isLast = false;

        @Override
        public boolean hasNext() {
            if(currentNode == null){
                return false;
            }
            return (currentNode.next!=null || isLast);
        }

        @Override
        public Object next() {
            if(hasNext()) {
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                    if(currentNode.next == null){
                        isLast = true;
                    }
                    return currentNode.prev.data;
                }
                if (isLast) {
                    isLast = false;
                    return currentNode.data;
                }
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (currentNode.prev == null) {
                if (currentNode.next != null) {
                    currentNode.next.prev = null;
                    first = currentNode.next;
                    currentNode = first;
                } else {
                    first = null;
                    last = null;
                    length = 0;
                    return;
                }
            } else {
                if (currentNode.next == null) {
                    currentNode.prev.next = null;
                    last = currentNode.prev;
                    currentNode = last;
                } else {
                    Node pr = currentNode.prev;
                    currentNode = currentNode.next;
                    currentNode.prev = pr;
                    pr.next = currentNode;
                }
            }
            --length;
        }
    }
}
