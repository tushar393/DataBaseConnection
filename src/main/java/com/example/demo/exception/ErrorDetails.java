package com.example.demo.exception;

import javax.xml.crypto.Data;

public class ErrorDetails {
    private Data timeStamp;
    private String message;
    private String details;

    public ErrorDetails(Data timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Data getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Data timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
