package com.example.foodorder.exception;

public class PasswordNotMatchedException extends Exception {
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
