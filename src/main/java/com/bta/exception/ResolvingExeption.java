package com.bta.exception;

import lombok.Getter;

import javax.persistence.GeneratedValue;

@Getter
public class ResolvingExeption extends RuntimeException{
    public ResolvingExeption(String message) {
        super(message);
    }
}
