package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers;

public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
