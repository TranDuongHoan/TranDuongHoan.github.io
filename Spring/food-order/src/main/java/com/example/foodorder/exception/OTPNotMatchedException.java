package com.example.foodorder.exception;

public class OTPNotMatchedException extends Exception {
    public OTPNotMatchedException(String message) {
        super(message);
    }
}
