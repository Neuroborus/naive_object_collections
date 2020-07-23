package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public interface Container extends Iterable<Object> {
    // Removes all of the elements.
    void clear();

    // Returns the number of elements.
    int size();

    // Returns a string representation of this container.
    String toString();

    // Returns an iterator over elements.
    Iterator<Object> iterator();

    // Iterator must implements the remove method.
}