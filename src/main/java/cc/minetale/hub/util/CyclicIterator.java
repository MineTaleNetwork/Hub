package cc.minetale.hub.util;

import java.util.Iterator;

public class CyclicIterator<T> implements Iterator<T> {

    private final T[] values;
    private int current;

    public CyclicIterator(T[] values) {
        this.values = values;
        this.current = 0;
    }

    public CyclicIterator(T[] values, int current) {
        this.values = values;
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        this.current = (this.current + 1) % this.values.length;
        return this.values[current];
    }

}