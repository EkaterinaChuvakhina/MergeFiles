package ru.chuvahina.node;

import ru.chuvahina.exception.OrderException;
import ru.chuvahina.reader.FileReader;
import ru.chuvahina.reader.Reader;

import java.io.FileNotFoundException;
import java.util.Comparator;

public abstract class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    protected T lastValue;
    protected T prevValue;
    protected Reader reader;
    protected Comparator<T> comparator;

    public Node(Reader reader, Comparator<T> comparator) {
        this.reader = reader;
        this.comparator = comparator;
    }

    public boolean hasNextValue() {

        return reader.hasNextValue();
    }

    public abstract T readLastValue();

    public T getLastValue() {
        return lastValue;
    }

    public T getPrevValue() {
        return prevValue;
    }

    public void setPrevValue() {
        if (lastValue != null) {
            this.prevValue = lastValue;
        }
    }

    public Reader getReader() {
        return reader;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.getLastValue().compareTo(o.getLastValue());
    }

    public void validateValueOrder(T next) throws OrderException {
        boolean compare = true;
        if (prevValue != null) {
            compare = comparator.compare(prevValue, next) <= 0;
        }
        if (!compare) {
            if (reader instanceof FileReader) {
                throw new OrderException(String.format("Error! File sorting is broken in file %s.",
                        ((FileReader) reader).getFile().getName()));
            }
        }
    }
}
