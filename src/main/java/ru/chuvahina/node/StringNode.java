package ru.chuvahina.node;

import ru.chuvahina.exception.OrderException;
import ru.chuvahina.reader.FileReader;

import java.io.FileNotFoundException;
import java.util.Comparator;

public class StringNode extends Node<String> {
    public StringNode(FileReader fileReader, Comparator<String> comparator) {
        super(fileReader, comparator);
    }

    @Override
    public String readLastValue() throws OrderException{
        String next ;
        if (hasNextValue()) {
            next = reader.readNextValue();
            validateValueOrder(next);
            lastValue = next;
            return lastValue;
        } else {
            return null;
        }
    }
}

