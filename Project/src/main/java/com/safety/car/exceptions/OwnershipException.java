package com.safety.car.exceptions;

public class OwnershipException extends RuntimeException{
    public OwnershipException(String message) {
        super(message);
    }
}