package ru.chuvahina.node;

import ru.chuvahina.exception.OrderException;
import ru.chuvahina.reader.FileReader;
import ru.chuvahina.reader.Reader;

import java.util.Comparator;

public class IntegerNode extends Node<Integer> {

    public IntegerNode(Reader fileReader, Comparator<Integer> comparator) {
        super(fileReader, comparator);
    }

    @Override
    public Integer readLastValue() throws OrderException {
        Integer next = null;
        String readValue = null;
        if (hasNextValue()) {
            boolean isNotCorrectNumber = true;
            while (isNotCorrectNumber) {
                try {
                    if (hasNextValue()) {
                        readValue = reader.readNextValue();
                        next = Integer.parseInt(readValue);
                        validateValueOrder(next);
                    }
                    isNotCorrectNumber = false;
                } catch (NumberFormatException e) {
                    if (reader instanceof FileReader) {
                        System.out.printf("Warning! An invalid value \"%s\" was found in the file %s. This value cannot be processed and will be skipped.\n",
                                readValue, ((FileReader) reader).getFile().getName());
                    }
                }
                lastValue = next;
            }
            return lastValue;
        } else {
            return null;
        }
    }
}
