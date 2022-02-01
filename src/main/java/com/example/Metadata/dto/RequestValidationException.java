package com.example.Metadata.dto;

public class RequestValidationException extends RuntimeException {
    public RequestValidationException(String message) {
        super(message);
    }

    public RequestValidationException(Throwable cause) {
        super(cause);
    }

    public RequestValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
