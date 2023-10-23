package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {

    private final List<T> list;
    private int pointer;

    public BackwardIterator(Collection<T> collection) {
        this.pointer = collection.size() - 1;
        this.list = collection.stream().toList();
    }

    @Override
    public boolean hasNext() {
        return pointer >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            return null;
        }
        T res = this.list.get(pointer);
        pointer--;
        return res;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
