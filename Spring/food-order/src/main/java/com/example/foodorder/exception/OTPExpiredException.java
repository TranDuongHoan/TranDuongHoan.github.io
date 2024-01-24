package com.example.foodorder.exception;

public class OTPExpiredException extends Exception{
    public OTPExpiredException(String message) {
        super(message);
    }
}
