package ru.chuvahina.reader;

import ru.chuvahina.exception.FileReaderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader implements Reader {
    protected File file;
    protected Scanner scanner;

    public FileReader(File file) throws FileReaderException {
        this.file = file;
        try {
            scanner = new Scanner(file);
        }catch (FileNotFoundException e){
            throw new FileReaderException(String.format("File %s not found", file.getName()));
        }
    }

    public File getFile() {
        return file;
    }

    @Override
    public String readNextValue() {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextValue() {
        return scanner.hasNextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
