package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class Demo {
    public static void main(String[] arg) {
        String[] args = {"A", "B", "C"};

        System.out.println("__ArrayImpl__");

        ArrayImpl.main(args);

        /////////////////////////////////////

        System.out.println("__ListImpl__");

        ListImpl.main(args);

        /////////////////////////////////////

        System.out.println("__QueueImpl__");

        QueueImpl.main(args);

        /////////////////////////////////////

        System.out.println("__StackImpl__");

        StackImpl.main(args);

    }
}
