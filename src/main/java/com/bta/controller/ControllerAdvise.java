package com.bta.controller;

import com.bta.exception.ResolvingExeption;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            ResolvingExeption.class,
            RuntimeException.class
    })
    public ResponseEntity<Object> handleStatus400(HttpRequest request, final ConstraintViolationException exception){
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
