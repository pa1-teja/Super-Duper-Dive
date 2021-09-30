package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers;

public class CredentialException extends RuntimeException{
    public CredentialException(String message) {
        super(message);
    }
    public CredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}
