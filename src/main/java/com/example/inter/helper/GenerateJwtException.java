package com.example.inter.helper;

public class GenerateJwtException extends RuntimeException {
    public GenerateJwtException(String message) {
        super(message);
    }

    public GenerateJwtException(String message, Throwable cause) {
        super(message, cause);

    }
}
