package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models;

public class ResponseObject {
    private String fieldObjectName;
    private boolean status = false;
    private String message;

    public String getFieldObjectName() {
        return fieldObjectName;
    }

    public void setFieldObjectName(String fieldObjectName) {
        this.fieldObjectName = fieldObjectName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
