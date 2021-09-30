package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers;

public class NoteException extends RuntimeException{
    public NoteException(String message) {
        super(message);
    }
    public NoteException(String message, Throwable cause) {
        super(message, cause);
    }
}

