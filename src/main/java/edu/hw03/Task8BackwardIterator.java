package edu.hw03;

import java.util.Iterator;
import java.util.SequencedCollection;

public class Task8BackwardIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;

    public Task8BackwardIterator(SequencedCollection<T> collection) {
        this.iterator = collection.reversed().iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}
