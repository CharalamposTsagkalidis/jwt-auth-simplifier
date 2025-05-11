package com.example.inter.helper;

public class UserGenerationException extends RuntimeException {
    public UserGenerationException(String message) {
        super(message);
    }

    public UserGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
