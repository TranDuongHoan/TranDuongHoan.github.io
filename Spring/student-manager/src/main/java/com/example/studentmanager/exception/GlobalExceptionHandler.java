package com.example.studentmanager.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({com.example.studentmanager.exception.StudentNotFoundException.class})
    public void handBookFoundException(com.example.studentmanager.exception.StudentNotFoundException exception) {
        log.error(exception.getMessage(), exception);
    }

}
