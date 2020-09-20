package ru.chuvahina.exception;

import java.io.IOException;

public class FileReaderException extends IOException {
    public FileReaderException(String message){
        super(message);
    }
}
