package ru.chuvahina.writer;

import java.io.Closeable;

public interface Writer extends Closeable {
    void write(Object data);

    @Override
    void close();
}
