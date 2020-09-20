package ru.chuvahina.writer;

import ru.chuvahina.configuration.Configuration;
import ru.chuvahina.exception.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter implements Writer {
    private final PrintWriter writer;

    public FileWriter(Configuration configuration) {
        File outputFile = configuration.getOutputFile();
        try {
            this.writer = new PrintWriter(outputFile);
        } catch (FileNotFoundException e) {
            throw new WriterException(String.format("File to write on path %s not found",
                    outputFile.getName()));
        }
    }

    @Override
    public void write(Object data) {
        writer.println(data);
    }

    @Override
    public void close() {
        writer.close();
    }
}
