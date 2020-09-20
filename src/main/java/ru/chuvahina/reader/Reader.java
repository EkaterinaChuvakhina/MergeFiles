package ru.chuvahina.reader;

import java.io.Closeable;

public interface Reader extends Closeable {
    String readNextValue();

    boolean hasNextValue();
}
