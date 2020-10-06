package com.safety.car.exceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message) {
        super(message);
    }
}